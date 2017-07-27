package test.java;
import java.util.Scanner;

import main.java.PriorityQueueMain;
import junit.framework.TestCase;


public class PriorityQueueMainTest extends TestCase {

	/***************************************************************
	 * Method: testQueueArrayLength                                *
	 * Tests if array length is the same as the length it is given *
	 **************************************************************/
	public void testQueueArrayLength() {
		int i = 5;
		PriorityQueueMain pq = new PriorityQueueMain(i);
		
		assertEquals(i, pq.queueArrayLength());
	}
	
	/*********************************************************************
	 * Method: testQueueArrayGetValue                                    *
	 * Tests if array value is what is expected after initializing array *
	 ********************************************************************/
	public void testQueueArrayGetValue() {
		int i = 5;
		PriorityQueueMain pq = new PriorityQueueMain(i);
		
		assertEquals(null, pq.queueArrayGetValue(2));
	}
	
	/*********************************************************************
	 * Method: testQueueArrayGetKey                                      *
	 * Tests if array value is what is expected after initializing array *
	 ********************************************************************/
	public void testQueueArrayGetKey() {
		int i = 5;
		PriorityQueueMain pq = new PriorityQueueMain(i);
		
		assertEquals(null, pq.queueArrayGetKey(2));
	}
	
	/**************************************************************
	 * Method: testQueueArraySetKeyValue                          *
	 * Tests if value was stored in the correct cell in the array *
	 *************************************************************/
	public void testQueueArraySetKeyValue(){
		int i = 5;
		String value = "value";
		String key = "1";
		PriorityQueueMain pq = new PriorityQueueMain(i);
		
		pq.queueArraySetKeyValue(3, key, value);
		
		assertEquals(value, pq.queueArrayGetValue(3));
	}
	
	/******************************************************
	 * Method: testLessThan                               *
	 * Tests if the second element is less than the first *
	 *****************************************************/
	public void testLessThan() {
		int i = 3;
		String a = "value one"; 
		String b = "value two";
		String k1 = "1";
		String k2 = "2";
		PriorityQueueMain pq0 = new PriorityQueueMain(i);
		PriorityQueueMain pq1 = new PriorityQueueMain(i);
		PriorityQueueMain pq2 = new PriorityQueueMain(i);
		
		pq0.queueArraySetKeyValue(1, k1, a);
		pq0.queueArraySetKeyValue(2, k2, b);
		pq1.queueArraySetKeyValue(2, k1, a);
		pq1.queueArraySetKeyValue(1, k2, b);
		pq2.queueArraySetKeyValue(1, k1, a);
		pq2.queueArraySetKeyValue(2, k1, b);
		
		assertFalse(pq0.lessThan(2, 1));
		assertTrue(pq1.lessThan(2, 1));
		assertFalse(pq2.lessThan(2, 1));
	}
	
	/**********************************************************
	 * Method: testCompareLeaf                                *
	 * Tests if the k element is greater than the k/2 element *
	 * If it is, swap the elements
	 *********************************************************/
	public void testCompareLeaf() {
		int i = 3;
		String a = "value one"; 
		String b = "value two";
		String k1 = "1";
		String k2 = "2";
		PriorityQueueMain pq = new PriorityQueueMain(i);
		
		pq.queueArraySetKeyValue(2, k1, a);
		pq.queueArraySetKeyValue(1, k2, b);
		
		pq.compareLeaf(2);
		
		assertEquals(a, pq.queueArrayGetValue(1));
		assertEquals(b, pq.queueArrayGetValue(2));
	}
	
	/******************************************************************************************
	 * Method: testInsert                                                                     *
	 * Tests inserting an element into the array and checks that the cell holds the right key *
	 *****************************************************************************************/
	public void testInsert() {
		int i = 5;
		String a = "test value", b = "some value", c = "another value", d = "final value";
		String k1 = "1", k2 = "2", k3 = "3", k4 = "4";
		PriorityQueueMain pq = new PriorityQueueMain(i);
		
		pq.insert(k3, a);
		pq.insert(k1, b);
		pq.insert(k4, c);
		pq.insert(k2, d);
		
		assertEquals(null, pq.queueArrayGetValue(0));
		assertEquals(b, pq.queueArrayGetValue(1));
		assertEquals(d, pq.queueArrayGetValue(2));
		assertEquals(c, pq.queueArrayGetValue(3));
		assertEquals(a, pq.queueArrayGetValue(4));
	}
	
	/*************************************************************************************
	 * Method: testMaximum                                                               *
	 * Tests returning the element in the queue with the largest key (the first element) *
	 ************************************************************************************/
	public void testMaximum(){
		int i = 5;
		String a = "test value", b = "some value", c = "another value", d = "final value";
		String k1 = "1", k2 = "2", k3 = "3", k4 = "4";
		PriorityQueueMain pq = new PriorityQueueMain(i);
		
		pq.insert(k3, a);
		pq.insert(k1, b);
		pq.insert(k4, c);
		pq.insert(k2, d);
		
		assertEquals(pq.queueArrayGetValue(1), pq.maximum());
	}
	
	/**************************************************************************
	 * Method: testRemove                                                     *
	 * Tests removing the first element from the array and setting it to null *
	 *************************************************************************/
	public void testRemove(){
		int i = 5;
		String a = "test value", b = "some value", c = "another value", d = "final value";
		String k1 = "1", k2 = "2", k3 = "3", k4 = "4";
		PriorityQueueMain pq = new PriorityQueueMain(i);
		
		pq.insert(k3, a);
		pq.insert(k1, b);
		pq.insert(k4, c);
		pq.insert(k2, d);
		pq.remove();
		
		assertEquals(null, pq.queueArrayGetValue(1));
	}
	
	/***************************************************************
	 * Method: testMove                                            *
	 * Tests moving the higher value child to the null parent cell *
	 **************************************************************/
	public void testMove(){
		int i = 9, k = 4;
		String a = "test value", b = "some value", c = "another value", d = "random value", e = "simple value", f = "complex value", g = "crooked value", h = "linear value";
		String k1 = "1", k2 = "2", k3 = "3", k4 = "4", k5 = "5", k6 = "6", k7 = "7", k8 = "8";
		
		PriorityQueueMain pq = new PriorityQueueMain(i);
		PriorityQueueMain pq1 = new PriorityQueueMain(k);
		PriorityQueueMain pq2 = new PriorityQueueMain(k);
		
		pq1.insert(k1, a);
		
		pq1.remove();
		pq1.move();
		
		assertEquals(null, pq1.queueArrayGetKey(1));
		
		pq1.insert(k1, a);
		pq1.insert(k2, b);
		
		pq1.remove();
		pq1.move();
		
		assertEquals(k2, pq1.queueArrayGetKey(1));
		assertEquals(null, pq1.queueArrayGetKey(2));
		
		pq2.queueArraySetKeyValue(1, k1, a);
		pq2.queueArraySetKeyValue(3, k2, b);
		
		pq2.remove();
		pq2.move();
		
		assertEquals(k2, pq2.queueArrayGetKey(1));
		assertEquals(null, pq2.queueArrayGetKey(3));
		
		pq.insert(k5, d);
		pq.insert(k2, a);
		pq.insert(k8, b);
		pq.insert(k1, c);
		pq.insert(k3, g);
		pq.insert(k7, e);
		pq.insert(k6, h);
		pq.insert(k4, f);
		
		pq.remove();
		pq.move();
		
		assertEquals(k2, pq.queueArrayGetKey(1));
		assertEquals(k3, pq.queueArrayGetKey(2));
		assertEquals(k6, pq.queueArrayGetKey(3));
		assertEquals(k4, pq.queueArrayGetKey(4));
		assertEquals(null, pq.queueArrayGetKey(5));
		assertEquals(k8, pq.queueArrayGetKey(6));
		assertEquals(k7, pq.queueArrayGetKey(7));
		assertEquals(k5, pq.queueArrayGetKey(8));
	}
	
	/*******************************************************************************************************
	 * Method: testExtractMax                                                                              *
	 * Tests returning the highest key, removing the highest key, and then reorganizing the Priority Queue *
	 *******************************************************************************************************/
	public void testExtractMax(){
		int i = 9;
		String highestKey, removedHighestKey;
		String a = "test value", b = "some value", c = "another value", d = "random value", e = "simple value", f = "complex value", g = "crooked value", h = "linear value";
		String k1 = "1", k2 = "2", k3 = "3", k4 = "4", k5 = "5", k6 = "6", k7 = "7", k8 = "8";
		
		PriorityQueueMain pq = new PriorityQueueMain(i);
		
		pq.insert(k5, d);
		pq.insert(k2, a);
		pq.insert(k8, b);
		pq.insert(k1, c);
		pq.insert(k3, g);
		pq.insert(k7, e);
		pq.insert(k6, h);
		pq.insert(k4, f);
		
		highestKey = pq.queueArrayGetValue(1);
		removedHighestKey = pq.extractMax();
		
		assertEquals(highestKey, removedHighestKey);
		
		assertEquals(k2, pq.queueArrayGetKey(1));
		assertEquals(k3, pq.queueArrayGetKey(2));
		assertEquals(k6, pq.queueArrayGetKey(3));
		assertEquals(k4, pq.queueArrayGetKey(4));
		assertEquals(null, pq.queueArrayGetKey(5));
		assertEquals(k8, pq.queueArrayGetKey(6));
		assertEquals(k7, pq.queueArrayGetKey(7));
		assertEquals(k5, pq.queueArrayGetKey(8));
	}
	
	/*******************************************************************************
	 * Method: testMainMenu                                                        *
	 * Tests returning the correct boolean opperand based on the input of the user *
	 ******************************************************************************/
	public void testMainMenu(){
		int i = 5;
		Scanner scan = new Scanner(System.in);
		String a = "test value", b = "some value", c = "another value", d = "final value";
		String k1 = "1", k2 = "2", k3 = "3", k4 = "4";
		String emax = "extractmax", max = "maximum", insert = "insert", queue = "queue", quit = "quit", rand = "kjdksjd";
		PriorityQueueMain pq = new PriorityQueueMain(i);
		
		pq.insert(k3, a);
		pq.insert(k1, b);
		pq.insert(k4, c);
		pq.insert(k2, d);
		
		assertTrue(pq.mainMenu(emax, pq, scan));
		assertTrue(pq.mainMenu(max, pq, scan));
		assertTrue(pq.mainMenu(insert, pq, scan));
		assertTrue(pq.mainMenu(queue, pq, scan));
		assertFalse(pq.mainMenu(quit, pq, scan));
		assertTrue(pq.mainMenu(rand, pq, scan));
	}
}
