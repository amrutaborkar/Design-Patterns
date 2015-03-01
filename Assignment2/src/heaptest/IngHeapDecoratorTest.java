package heaptest;

import static org.junit.Assert.assertTrue;
import heap.Heap;
import heap.IngHeapDecorator;
import heap.MinHeapStrategy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class IngHeapDecoratorTest {
	@Test
	public void testIterator() {
		Heap minHeap = new Heap(new MinHeapStrategy());
		String arr[] = new String[] { "eating", "fruit", "writing", "program", "testing", "code", "using", "junit" };
		for (int i = 0; i < arr.length; i++) {
			minHeap.add(arr[i]);
		}
		IngHeapDecorator minIngHeap = new IngHeapDecorator(minHeap);
		Iterator<String> itr = minIngHeap.iterator();
		List<String> resultList = new ArrayList<String>();
		while (itr.hasNext()) {
			resultList.add(itr.next());
		}
		assertTrue(resultList.toString().equals("[using, eating, writing, testing]"));
	}

	@Test
	public void testToArray() {
		Heap minHeap = new Heap(new MinHeapStrategy());
		String arr[] = new String[] { "eating", "fruit", "writing", "program", "testing", "code", "using", "junit" };
		for (int i = 0; i < arr.length; i++) {
			minHeap.add(arr[i]);
		}
		IngHeapDecorator minIngHeap = new IngHeapDecorator(minHeap);

		assertTrue(minHeap.size() == 8);
		List<String> list = new ArrayList<String>();
		for (Object object : minIngHeap.toArray()) {
			String string = (String) object;
			list.add(string);
		}
		assertTrue(list.toString().equals("[using, eating, writing, testing]"));
	}

	@Test
	public void testToString() {
		Heap minHeap = new Heap(new MinHeapStrategy());
		String arr[] = new String[] { "eating", "fruit", "writing", "program", "testing", "code", "using", "junit" };
		for (int i = 0; i < arr.length; i++) {
			minHeap.add(arr[i]);
		}
		IngHeapDecorator minIngHeap = new IngHeapDecorator(minHeap);
		assertTrue(minIngHeap.toString().equals("[using, eating, writing, testing]"));
	}

	@Test
	public void testAdd() {
		Heap minHeap = new Heap(new MinHeapStrategy());
		String arr[] = new String[] { "eating", "fruit", "writing", "program", "testing", "code", "using", "junit" };
		for (int i = 0; i < arr.length; i++) {
			minHeap.add(arr[i]);
		}
		IngHeapDecorator minIngHeap = new IngHeapDecorator(minHeap);
		minIngHeap.add("checking");
		assertTrue(minIngHeap.toString().equals("[using, eating, checking, writing, testing]"));
	}

	@Test
	public void testSize() {
		Heap minHeap = new Heap(new MinHeapStrategy());
		String arr[] = new String[] { "eating", "fruit", "writing", "program", "testing", "code", "using", "junit" };
		for (int i = 0; i < arr.length; i++) {
			minHeap.add(arr[i]);
		}
		IngHeapDecorator minIngHeap = new IngHeapDecorator(minHeap);
		assertTrue(minIngHeap.size() == 4);
	}
}
