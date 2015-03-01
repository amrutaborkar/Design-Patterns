package heaptest;

import static org.junit.Assert.assertTrue;
import heap.Heap;
import heap.IngFilter;
import heap.MinHeapStrategy;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class IngFilterTest {

	@Test
	public void testIngFilter() {
		Heap minHeap = new Heap(new MinHeapStrategy());
		String arr[] = new String[] { "eating", "fruit", "writing", "program", "testing", "code", "using", "junit" };
		for (int i = 0; i < arr.length; i++) {
			minHeap.add(arr[i]);
		}
		assertTrue(minHeap.size() == 8);
		assertTrue(minHeap.toString()
				.equals("[code, eating, program, using, fruit, junit, writing, testing]"));
		IngFilter ingFilter = new IngFilter(minHeap.iterator());
		List<String> ingWords = new ArrayList<String>();
		while (ingFilter.hasNext()) {
			ingWords.add(ingFilter.next());
		}
		assertTrue(ingWords.toString().equals("[using, eating, writing, testing]"));
	}
}
