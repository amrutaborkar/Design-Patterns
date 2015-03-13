package minheaptest;

import static org.junit.Assert.assertTrue;
import minheap.MinHeap;

import org.junit.Test;

/**
 * Tests MinHeap {@link MinHeap} class for all its functionalities
 * 
 * @author Amruta
 * 
 */
public class MinHeapTest {

	@Test
	public void testPreOrder() {
		MinHeap minHeap = new MinHeap();
		int arr[] = new int[] { 45, 65, 72, 90, 81, 82, 96, 99, 95 };
		for (int i = 0; i < arr.length; i++) {
			minHeap.add(Integer.toString(arr[i]));
		}
		assertTrue(minHeap.getPreorder().toString().equals("[45, 65, 90, 96, 82, 72, 81, 95, 99]"));
	}

	@Test
	public void testPoll() {
		MinHeap minHeap = new MinHeap();
		int arr[] = new int[] { 45, 65, 72, 90, 81, 82, 96, 99, 95 };
		for (int i = 0; i < arr.length; i++) {
			minHeap.add(Integer.toString(arr[i]));
		}
		assertTrue(minHeap.getPreorder().toString().equals("[45, 65, 90, 96, 82, 72, 81, 95, 99]"));
		assertTrue(minHeap.poll().equals("45"));
		assertTrue(minHeap.getPreorder().toString().equals("[65, 82, 90, 96, 95, 72, 81, 99]"));
	}

	
	@Test
	public void testInsertNode() {
		MinHeap minHeap = new MinHeap();
		int arr[] = new int[] { 45, 65, 72, 90, 81, 82, 96, 99, 95 };
		for (int i = 0; i < arr.length; i++) {
			minHeap.add(Integer.toString(arr[i]));
		}
		assertTrue(minHeap.size()==9);
		assertTrue(minHeap.getPreorder().toString().equals("[45, 65, 90, 96, 82, 72, 81, 95, 99]"));
		boolean result=minHeap.offer(Integer.toString(50));
		System.out.println(result);
		assertTrue(minHeap.getPreorder().toString().equals("[45, 50, 90, 96, 65, 82, 72, 81, 95, 99]"));
	}

	@Test
	public void testSelectThatEndsWith() {
		MinHeap minHeap = new MinHeap();
		assertTrue(minHeap.peek()==null);
		String arr[] = new String[] { "eating", "fruit", "writing", "program", "testing", "code", "using", "junit" };
		for (int i = 0; i < arr.length; i++) {
			minHeap.add(arr[i]);
		}
		assertTrue(minHeap.size()==8);
		assertTrue(minHeap.getPreorder().toString().equals("[code, eating, program, using, fruit, junit, writing, testing]"));
		assertTrue(minHeap.getIngWords().toString().equals("[eating, using, writing, testing]"));
		assertTrue(minHeap.peek().equals("code"));
	}
	
	
}
