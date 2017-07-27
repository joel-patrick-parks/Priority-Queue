package main.java;

import java.util.Scanner;

public class PriorityQueueMain {
	private String[][] queueArray;
	
	public PriorityQueueMain(int length){
		this.queueArray = new String[length][2];
	}
	
	/***********************************
	 * Method: queueArrayLength        *
	 * Returns the length of the array *
	 **********************************/
	public int queueArrayLength(){
		return queueArray.length;
	}
	
	/***********************************
	 * Method: queueArraySetKeyValue   *
	 * Sets queueArray[i][0] to 'key'  *
	 * Sets queueArray[i][1] to 'value *
	 **********************************/
	public void queueArraySetKeyValue(int i, String key, String value){
		queueArray[i][0] = key;
		queueArray[i][1] = value;
	}
	
	/*****************************************************
	 * Method: queueArrayGetValue                        *
	 * Returns the value of the ith element of the array *
	 ****************************************************/
	public String queueArrayGetValue(int i){
		return queueArray[i][1];
	}
	
	/***************************************************
	 * Method: queueArrayGetKey                        *
	 * Returns the key of the ith element of the array *
	 **************************************************/
	public String queueArrayGetKey(int i){
		return queueArray[i][0];
	}	
	
	/****************************************************
	 * Method: lessThan                                 *
	 * If leaf is greater than parent return true       *
	 * Else return false                                *
	 ***************************************************/
	public boolean lessThan(int y, int z){
		return queueArray[y][0].compareTo(queueArray[z][0]) < 0;
	}
	
	/***************************************************
	 * Method: compareLeaf                             *
	 * If leaf is greater than parent, exchange values *
	 ***************************************************/
	public void compareLeaf(int k){
		while(k > 1 && lessThan(k, k/2)){
			String tempKey = queueArray[k/2][0];
			String tempVal = queueArray[k/2][1];
			queueArray[k/2][0] = queueArray[k][0];
			queueArray[k/2][1] = queueArray[k][1];
			queueArray[k][0] = tempKey;
			queueArray[k][1] = tempVal;
			k = k/2;
		}
	}
	
	/******************************************
	 * Method: insert                         *
	 * Inserts 'key' and 'val' into the array *
	 *****************************************/
	public void insert(String key, String val){
		outerloop:
		for(int i = 1; i < queueArray.length; i++){
			if(queueArray[i][0] == null){
				queueArray[i][0] = key;
				queueArray[i][1] = val;
				if(i != 1){
					compareLeaf(i);
				}
				break outerloop;
			}
		}
	}
	
	/*********************************************************
	 * Method: maximum                                       *
	 * Returns the element of the queue with the largest key *
	 ********************************************************/
	public String maximum(){
		return queueArray[1][1];
	}
	
	/*************************************************************************
	 * Method: remove                                                        *
	 * Removes the first element from the Priority Queue and sets it to null *
	 ************************************************************************/
	public void remove(){
		queueArray[1][0] = null;
		queueArray[1][1] = null;
	}
	
	/****************************************************************************************
	 * Method: move                                                                         *
	 * Finds a key set to null, compares children, and moves the larger key to the null key *
	 ***************************************************************************************/
	public void move(){
		int y, z;
		
		if(queueArrayGetValue(1) == null && (queueArrayGetValue(2) == null || queueArrayGetValue(3) == null)){
			if(queueArrayGetValue(2) == null && queueArrayGetValue(3) != null){
				queueArraySetKeyValue(1, queueArrayGetKey(3), queueArrayGetValue(3));
				queueArraySetKeyValue(3, null, null);
			}else if(queueArrayGetValue(2) != null && queueArrayGetValue(3) == null){
				queueArraySetKeyValue(1, queueArrayGetKey(2), queueArrayGetValue(2));
				queueArraySetKeyValue(2, null, null);
			}
		}else{
			outerloop:
			for(int i = 1; i < queueArrayLength(); i++){
				if(queueArrayGetValue(i) == null){
					y = i * 2;
					z = i * 2 + 1;
					if(y >= queueArrayLength()){
						break outerloop;
					}else if(z >= queueArrayLength()){
						queueArraySetKeyValue(i, queueArrayGetKey(y), queueArrayGetValue(y));
						queueArraySetKeyValue(y, null, null);
						break outerloop;
					}else{
						if(lessThan(y,z)){
							queueArraySetKeyValue(i, queueArrayGetKey(y), queueArrayGetValue(y));
							queueArraySetKeyValue(y, null, null);
						}else{
							queueArraySetKeyValue(i, queueArrayGetKey(z), queueArrayGetValue(z));
							queueArraySetKeyValue(z, null, null);
						}
					}
				}
			}
		}
	}
	
	/************************************************************************************************
	 * Method: extractMax                                                                           *
	 * Returns the element of the queue with the largest key and removes it from the Priority Queue *
	 ***********************************************************************************************/
	public String extractMax(){
		String maxValue = queueArray[1][1];
		
		remove();
		move();
		
		return maxValue;
	}
	
	public boolean mainMenu(String command, PriorityQueueMain pq, Scanner scan){
		String element, key, maxKey, queue = "| ";
		
		if(command.toLowerCase().equals("maximum")){
			maxKey = pq.maximum();
			System.out.println("Max Key: " + maxKey);
		}else if(command.toLowerCase().equals("extractmax")){
			maxKey = pq.extractMax();
			System.out.println("Max Key: " + maxKey);
			for(int i = 1; i < pq.queueArrayLength(); i++){
				if(pq.queueArrayGetValue(i) == null){
					queue = queue.concat("null");
					queue = queue.concat(" | ");
				}else{
					queue = queue.concat(pq.queueArrayGetValue(i));
					queue = queue.concat(" | ");
				}
			}
			System.out.println(queue);
		}else if(command.toLowerCase().equals("insert")){
			System.out.println("Enter an element to insert: ");
			element = scan.nextLine();
			System.out.println("Enter the key of the element: ");
			key = scan.nextLine();
			pq.insert(key, element);
			for(int i = 1; i < pq.queueArrayLength(); i++){
				if(pq.queueArrayGetValue(i) == null){
					queue = queue.concat("null");
					queue = queue.concat(" | ");
				}else{
					queue = queue.concat(pq.queueArrayGetValue(i));
					queue = queue.concat(" | ");
				}
			}
			System.out.println(queue);
		}else if(command.toLowerCase().equals("queue")){
			for(int i = 1; i < pq.queueArrayLength(); i++){
				if(pq.queueArrayGetValue(i) == null){
					queue = queue.concat("null");
					queue = queue.concat(" | ");
				}else{
					queue = queue.concat(pq.queueArrayGetValue(i));
					queue = queue.concat(" | ");
				}
			}
			System.out.println(queue);
		}else if(command.toLowerCase().equals("quit")){
			System.out.println("Closing Priority Queue...");
			return false;
		}else{
			System.out.println("Invalid Command...");
		}
		queue = "| ";
		
		return true;
	}
	
	/********************
	 * Method: main     *
	 * Runs the program *
	 *******************/
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		int priorityQueueLength = 0;
		PriorityQueueMain pq = null;
		String buffer, command;
		boolean results = true;
		
		System.out.println("Enter the length of the Priority Queue (must be greater than 1): ");
		
		while(!scan.hasNextInt()){
			System.out.println("Invalid input...");
			scan.next();
			System.out.println("Enter the length of the Priority Queue (must be greater than 1): ");
		}
		
		priorityQueueLength = scan.nextInt();
		
		buffer = scan.nextLine();
		pq = new PriorityQueueMain(priorityQueueLength);
		
		while(results){
			System.out.println("Main Menu: ");
			System.out.println("maximum");
			System.out.println("extractmax");
			System.out.println("insert");
			System.out.println("queue");
			System.out.println("quit");
			System.out.println("Enter a command: ");
			command = scan.nextLine();
			
			results = pq.mainMenu(command, pq, scan);
		}
		scan.close();
	}
}
