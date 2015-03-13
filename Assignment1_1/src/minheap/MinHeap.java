package minheap;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a Min Heap.
 * 
 * @author Amruta
 * 
 */
public class MinHeap {

	HeapNode root;
	int size = 0;
	HeapNode lastNode; 
	/**
	 * This functions insert a node into min heap
	 * 
	 * @param newNode
	 *            {@link HeapNode}
	 */
	//moved
	public HeapNode add(String newNodeValue) {
		size++;
		HeapNode newNode=new HeapNode(newNodeValue);
		if (root == null) {
			root = lastNode=newNode;
			return root;
		} else {
			lastNode=root.heapify(newNode);
			return lastNode;
		}
	}

	/**
	 * This function prints all nodes in pre-order fashion
	 * 
	 * @return aString : which a preorder formation of all the nodes in the heap
	 */
	//moved
	public List<String> getPreorder() {
		List<String> heapPreorderList = new ArrayList<String>();
		heapPreorderList.add(root.data);
		if (root.left != null)
			heapPreorderList.addAll(root.left.getPreorder());
		if (root.right != null)
			heapPreorderList.addAll(root.right.getPreorder());

		return heapPreorderList;
	}

	/**
	 * This function returns a string of all the nodes in pre order fashion
	 * which end with the given suffix string
	 * 
	 * @param suffix
	 * @return aString : which a preorder formation of all the nodes in the heap
	 *         which end with the suffix
	 */
	public List<String> getIngWords() {
		List<String> ingWordsList = new ArrayList<String>();
		if (root != null && root.endsWith("ing")) {
			ingWordsList.add(root.data);
		}
		if (root.left != null) {
			ingWordsList.addAll(root.left.getIngWords());
		}
		if (root.right != null) {
			ingWordsList.addAll(root.right.getIngWords());
		}
		return ingWordsList;
	}
    //moved
	public int size() {
		return size;
	}
//moved
	public String peek() {
		if (root != null)
			return root.data;
		return null;
	}
	//moved
	public boolean offer(String newNodeValue){
		if(add(newNodeValue)==null)
			return false;
		return true;
	}

	/**
	 * Retrieves and removes the head of this queue, or returns null if this queue is empty. moved
	 */
	public String poll(){
		if(root==null){
			return null;
		}
		String tobeRemoved=root.data;
		root.data=lastNode.data;
		HeapNode nodeToDelete=lastNode;
		lastNode=root;
		size--;
		if(size>1){
			if(nodeToDelete.isLeftChild){
				nodeToDelete.decreaseParentsHeight();
				nodeToDelete.parent.left=null;
			}
			nodeToDelete.parent.right=null;
			nodeToDelete=null;
			root.heapifyDown();
		}
		return tobeRemoved;
	}
}

