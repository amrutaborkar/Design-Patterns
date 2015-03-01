package heaptest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import heap.Heap;
import heap.MaxHeapStrategy;
import heap.MinHeapStrategy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

/**
 * Tests Heap {@link Heap} class for all its functionalities
 */
public class HeapTest {

	@Test
	public void testAddForMinHeap() {
		Heap minHeap = new Heap(new MinHeapStrategy());
		int arr[] = new int[] { 45, 65, 72, 90, 81, 82, 96, 99, 95 };
		for (int i = 0; i < arr.length; i++) {
			minHeap.add(Integer.toString(arr[i]));
		}
		//test for size
		assertTrue(minHeap.size() == 9);
		assertTrue(minHeap.toString().equals("[45, 65, 90, 96, 82, 72, 81, 95, 99]"));
		//test for offer
		boolean isAdded=minHeap.offer(Integer.toString(50));
		assertTrue(minHeap.toString().equals("[45, 50, 90, 96, 65, 82, 72, 81, 95, 99]"));
		assertTrue(isAdded);
	}

	@Test
	public void testAddForMaxHeap() {
		Heap maxHeap = new Heap(new MaxHeapStrategy());
		int arr[] = new int[] { 45, 65, 72, 90, 81, 82, 96, 99, 95 };
		for (int i = 0; i < arr.length; i++) {
			maxHeap.add(Integer.toString(arr[i]));
		}
		assertTrue(maxHeap.size() == 9);
		maxHeap.add(Integer.toString(50));
		assertTrue(maxHeap.toString().equals("[99, 90, 82, 45, 72, 50, 96, 95, 65, 81]"));
		//test for peek
		assertTrue(maxHeap.peek().equals("99"));		
	}

	@Test
	public void testIterator() {
		Heap maxHeap = new Heap(new MinHeapStrategy());
		int arr[] = new int[] { 45, 65, 72, 90, 81, 82, 96, 99, 95 };
		for (int i = 0; i < arr.length; i++) {
			maxHeap.add(Integer.toString(arr[i]));
		}
		maxHeap.add(Integer.toString(50));
		Iterator<String> itr = maxHeap.iterator();
		List<String> resultList = new ArrayList<String>();
		while (itr.hasNext()) {
			resultList.add(itr.next());
		}
		assertTrue(resultList.toString().equals("[96, 90, 50, 82, 65, 45, 95, 81, 72, 99]"));
	}
	@Test
	public void testToArray(){
		Heap minHeap = new Heap(new MinHeapStrategy());
		int arr[] = new int[] { 45, 65, 72, 90, 81, 82, 96, 99, 95 };
		for (int i = 0; i < arr.length; i++) {
			minHeap.add(Integer.toString(arr[i]));
		}
		System.out.println(arr.toString());
		List<String> list= new ArrayList<String>();
		for(Object object:minHeap.toArray()){
			String string=(String)object;
			list.add(string);
		}
		
	}
	
	@Test
	public void testToString(){
		Heap minHeap = new Heap(new MinHeapStrategy());
		int arr[] = new int[] { 45, 65, 72, 90, 81, 82, 96, 99, 95 };
		for (int i = 0; i < arr.length; i++) {
			minHeap.add(Integer.toString(arr[i]));
		}
		System.out.println(minHeap.toString());
		assertTrue(minHeap.toString().equals("[45, 65, 90, 96, 82, 72, 81, 95, 99]"));
	}
	
	@Test
	public void testPeek(){
		Heap minHeap = new Heap(new MinHeapStrategy());
		assertNull(minHeap.peek());
		int arr[] = new int[] { 45, 65, 72, 90, 81, 82, 96, 99, 95 };
		for (int i = 0; i < arr.length; i++) {
			minHeap.add(Integer.toString(arr[i]));
		}
		assertTrue(minHeap.peek().equals("45"));		
	}
	
	@Test
	public void testPoll() {
		Heap minHeap = new Heap(new MinHeapStrategy());
		assertNull(minHeap.poll());
		int arr[] = new int[] { 45, 65, 72, 90, 81, 82, 96, 99, 95 };
		for (int i = 0; i < arr.length; i++) {
			minHeap.add(Integer.toString(arr[i]));
		}
		assertTrue(minHeap.toString().equals("[45, 65, 90, 96, 82, 72, 81, 95, 99]"));
		assertTrue(minHeap.poll().equals("45"));
		assertTrue(minHeap.toString().equals("[65, 82, 90, 96, 95, 72, 81, 99]"));
	}
}
