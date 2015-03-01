package heap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class Heap extends AbstractHeap {

	private Node root;
	private int size = 0;
	private Node lastNode;
	private IHeapStrategy heapStrategy;

	public Heap(IHeapStrategy heapStrategy) {
		this.heapStrategy = heapStrategy;
	}

	/**
	 * This function adds a value into the heap
	 */
	public boolean add(String newNodeValue) {
		size++;
		if (root == null) {
			root = lastNode = new Node(newNodeValue);
			return true;
		} else {
			lastNode = root.add(heapStrategy, newNodeValue);
			if(lastNode==null){
				return false;
			}else{
				return true;
			}
		}
		
	}
	

	/**
	 * Returns the number of elements in the heap
	 */
	@Override
	public int size() {
		return size;
	}

	private class HeapIterator implements Iterator<String> {
		public String next() {
			if (cursor == 1) {
				if (root == null)
					return null;
				stack.push(root);
				if (!root.left.isNull())
					travelLeft(root.left);
				cursor++;
				return stack.pop().data;
			}
			Node nextInoderNode = stack.pop();
			cursor++;
			if (!nextInoderNode.right.isNull())
				travelLeft(nextInoderNode.right);
			return nextInoderNode.data;
		}

		private void travelLeft(Node node) {
			while (!node.left.isNull()) {
				stack.push(node);
				node = node.left;
			}
			stack.push(node);
		}

		public boolean hasNext() {
			return cursor <= size;
		}

		public void remove() {
			throw new UnsupportedOperationException("remove not implemented");
		}

		private int cursor = 1;
		Stack<Node> stack = new Stack<Node>();
	}

	/**
	 * This function returns a n iterator which iterates over the heap in
	 * in-order fashion
	 */
	@Override
	public Iterator<String> iterator() {
		return new HeapIterator();
	}

	/**
	 * Returns the in-order representation of the heap in array format
	 */
	/*public Object[] toArray() {
		if (root == null) {
			return null;
		}
		ArrayList<String> heapArrayList = new ArrayList<String>();
		Iterator<String> itr = this.iterator();
		while (itr.hasNext()) {
			heapArrayList.add(itr.next());
		}
		return heapArrayList.toArray();
	}
*/
	/**
	 * Returns the String representation of the heap
	 */
	/*public String toString() {
		if (root == null) {
			return "[]";
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("[" + root.toString());
		stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
		stringBuilder.append("]");
		return stringBuilder.toString();
	}

*/	/**
	 * Retrieves and removes the head of this queue, or returns null if this
	 * queue is empty.
	 */
	@Override
	public String poll() {
		if (root == null) {
			return null;
		}
		String tobeRemoved = root.data;
		root.swapValueWith(lastNode);
		Node nodeToDelete = lastNode;
		lastNode = root;
		nodeToDelete.removeNode();
		nodeToDelete = NullNode.getInstance();
		size--;
		if (size > 1) {
			root.heapifyDown();
		}
		return tobeRemoved;
	}

	/**
	 * Adds Node to the heap, returns true if node is added, else returns false
	 */
	@Override
	public boolean offer(Object newNodeValue) {
		return add((String) newNodeValue);
		/*if (add((String) newNodeValue) == null)
			return false;
		return true;*/
	}

	/**
	 * Retrieves, but does not remove, the head of this heap, or returns null if
	 * this heap is empty.
	 */
	@Override
	public String peek() {
		if (root != null)
			return root.data;
		return null;
	}
}
