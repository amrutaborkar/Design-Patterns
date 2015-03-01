package heap;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class acts as a decorator for the {@link Heap} class, returns values
 * from Heap which endWith 'Ing'
 */
public class IngHeapDecorator extends AbstractHeapDecorator {

	AbstractHeap heap;

	public IngHeapDecorator(Heap heap) {
		this.heap = heap;
	}
	@Override
	public Iterator<String> iterator() {
		if (isHeapNull()) {
			return null;
		}
		return new IngFilter(heap.iterator());
	}
	@Override
	public Object[] toArray() {
		if (isHeapNull()) {
			return null;
		}
		ArrayList<String> ingHeapArrayList = new ArrayList<String>();
		Iterator<String> itr = this.iterator();
		while (itr.hasNext()) {
			ingHeapArrayList.add(itr.next());
		}
		return ingHeapArrayList.toArray();
	}
	@Override
	public String toString() {
		StringBuilder ingHeapString = new StringBuilder();
		ingHeapString.append("[");
		for (Object data : this.toArray()) {
			ingHeapString.append(data);
			ingHeapString.append(", ");
		}
		ingHeapString.delete(ingHeapString.length()-2, ingHeapString.length());
		ingHeapString.append("]");
		return ingHeapString.toString();
	}

	@Override
	public boolean offer(Object e) {
		if (isHeapNull()) {
			return false;
		}
		return heap.offer(e);
	}

	@Override
	public Object peek() {
		if (isHeapNull()) {
			return null;
		}
		return heap.peek();
	}

	@Override
	public Object poll() {
		if (isHeapNull()) {
			return null;
		}
		return heap.poll();
	}

	@Override
	public int size() {
		if (isHeapNull()) {
			return 0;
		}
		return this.toArray().length;
	}

	@Override
	public boolean add(String data) {
		if (isHeapNull()) {
			return false;
		}
		return heap.add(data);
	}

	private boolean isHeapNull() {
		return (heap == null);
	}
}
