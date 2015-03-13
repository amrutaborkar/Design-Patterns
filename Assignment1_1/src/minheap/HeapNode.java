package minheap;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Min Heap Node
 * 
 * @author Amruta
 * 
 */
public class HeapNode {

	String data;
	HeapNode left;
	HeapNode right;
	HeapNode parent;
	int height;
	boolean isLeftChild;

	public HeapNode(String dataString) {
		data = dataString;
		
	}

	public String getData() {
		return data;
	}

	private void increaseHeight() {
		this.height++;
	}

	/**
	 * Insert a HeapNode{@link HeapNode} into the Min heap
	 * 
	 * @param newNode
	 */
	public HeapNode heapify(HeapNode newNode) {
		if (this.data.compareTo(newNode.data) <= 0) {
			// If the original node has value less than that of newNode.
			// Continue traverse with newNode, without swapping
			if (left == null) {
				left = newNode;
				left.parent = this;
				left.increaseParentsHeight();
				left.isLeftChild=true;
				return left;
			} else if (right == null) {
				right = newNode;
				right.parent = this;
				return right;
			} else if ((left.height <= right.height)) {
				// If both left and right sub-trees have same height, then
				// insert the newNode to the left side
				return left.heapify(newNode);
			} else {
				return right.heapify(newNode);
			}
		} else {
			this.swapValueWith(newNode);
			return this.heapify(newNode);

/*			String oldValue=this.data;
			this.data=newNodeValue;
			return this.heapify(oldValue);
*/		}
				
	}

	/**
	 * Swaps data of current node and given node
	 * 
	 * @param newNode
	 *            {@link HeapNode}
	 */
	public void swapValueWith(HeapNode newNode) {
		String swapData = newNode.data;
		newNode.data = this.data;
		this.data = swapData;
	}



	/**
	 * Increases the height of all the parent nodes of the present node
	 */
	private void increaseParentsHeight() {

		if (this.parent != null && !(this.height == this.parent.height - 1)) {
			if (!this.parent.isBalanced()) {
				// increase height for all the parents
				this.parent.increaseHeight();
				this.parent.increaseParentsHeight();
			}
		}
	}
	/**
	 * Increases the height of all the parent nodes of the present node
	 */
	public void decreaseParentsHeight() {

		if (this.parent != null && !(this.height == this.parent.height - 1)) {
			if (!this.parent.isBalanced()) {
				// increase height for all the parents
				this.parent.decreaseHeight();
				this.parent.decreaseParentsHeight();
			}
		}
	}
	private void decreaseHeight() {
		this.height++;
	}

	/**
	 * Checks if the left and right sub heap of the given node are of same
	 * height or not. Returns true if they have same height, else returns false
	 * 
	 * @return
	 */
	private boolean isBalanced() {
		if (left == null && right == null) {
			// If no children exist,node is balanced OR
			return true;
		} else if (left != null && right != null) {
			if (left.height == right.height) {
				// If left and right child have same height then node is
				// balanced
				return true;
			}
		}
		return false;
	}

	/**
	 * Displays a Node
	 */
	public String toString() {
		return " " + data;
	}

	/**
	 * This method returns a pre order formation of the node, its left sub heap
	 * and rigfht sub heap
	 * 
	 * @param suffix
	 * @return aString which is a preorder formation of node, left subtree and
	 *         right sub tree in the heap which end with the suffix
	 */
	public String printFilteredPreorder(String suffix) {
		StringBuilder preOrder = new StringBuilder();
		if (this.endsWith(suffix)) {
			preOrder.append(this.toString());
		}
		if (left != null)
			preOrder.append(left.printFilteredPreorder(suffix));
		if (right != null)
			preOrder.append(right.printFilteredPreorder(suffix));
		return preOrder.toString();
	}

	/**
	 * Check if the current node has a data that ends with the given suffix
	 * 
	 * @param suffix
	 * @return
	 */
	public boolean endsWith(String suffix) {
		return this.data.endsWith(suffix);
	}

	public List<String> getPreorder() {
		List<String> heapPreorderList=new ArrayList<String>();
		heapPreorderList.add(this.data);
		if(this.left!=null)
			heapPreorderList.addAll(this.left.getPreorder());
		if(this.right!=null)
			heapPreorderList.addAll(this.right.getPreorder());
		return heapPreorderList;
	}

	public List<String> getIngWords() {
		List<String> ingWordsList=new ArrayList<String>();
		if(this.data.endsWith("ing"))
			ingWordsList.add(this.data);
		if(this.left!=null)
			ingWordsList.addAll(this.left.getIngWords());
		if(this.right!=null)
			ingWordsList.addAll(this.right.getIngWords());
		return ingWordsList;
	}
	
	public void heapifyDown(){
		//checkif LeafNode
		if(left==null){
		//leaf node
			return;
		}
		HeapNode smallerChild=getSmallerChild();
		if (this.data.compareTo(smallerChild.data)<=0){
			return;
		}
		this.swapValueWith(smallerChild);
		smallerChild.heapifyDown();
	}
	
	private HeapNode getSmallerChild(){
		if (right ==null){
			return left;
		}
		if(left.data.compareTo(right.data)<=0){
			return left;
		}
		return right;
	}

	
}

