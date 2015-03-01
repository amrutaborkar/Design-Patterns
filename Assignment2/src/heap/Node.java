package heap;


public class Node {

	String data;
	Node left;
	Node right;
	Node parent;
	int height;
	boolean isLeftChild;

	protected Node() {
	}

	public Node(String newValue) {
		data = newValue;
		left = NullNode.getInstance();
		right = NullNode.getInstance();
		parent = NullNode.getInstance();
	}

	public Node add(IHeapStrategy heapStrategy, String newNodeValue) {
		boolean result = heapStrategy.add(this.data, newNodeValue);
		if (result) {
			return addNodeToSubtree(heapStrategy, newNodeValue);
		} else {
			String dataToSwap = this.data;
			this.data = newNodeValue;
			newNodeValue = dataToSwap;
			return this.add(heapStrategy, newNodeValue);
		}
	}

	/**
	 * Adds value in the appropriate sub-tree
	 */
	private Node addNodeToSubtree(IHeapStrategy heapStrategy, String newNodeValue) {
		if (this.getLeft().isNull()) {
			return this.setLeft(this.getLeft().add(heapStrategy, newNodeValue));
		} else if (this.getRight().isNull()) {
			return this.setRight(this.getRight().add(heapStrategy, newNodeValue));
		} else {
			return this.childWithSmallerHeight().add(heapStrategy, newNodeValue);
		}
	}

	/**
	 * Swaps data of current node and given node
	 */
	void swapValueWith(Node newNode) {
		String swapData = newNode.data;
		newNode.data = this.data;
		this.data = swapData;
	}

	/**
	 * Sets element node at appropriate location in the sub-tree
	 */
	void heapifyDown() {
		if (left.isNull()) {
			// leaf node
			return;
		}
		Node smallerChild = getSmallerChild();
		if (this.data.compareTo(smallerChild.data) <= 0) {
			return;
		}
		this.swapValueWith(smallerChild);
		smallerChild.heapifyDown();
	}

	/**
	 * Removes a leaf node
	 */
	void removeNode() {
		if (this.isLeftChild) {
			this.decreaseParentsHeight();
			this.parent.left = NullNode.getInstance();
		}
		this.parent.right = NullNode.getInstance();
	}

	boolean isNull() {
		return false;
	}

	private Node getSmallerChild() {
		if (right.isNull()) {
			return left;
		}
		if (left.data.compareTo(right.data) <= 0) {
			return left;
		}
		return right;
	}

	/**
	 * Decreases the height of all the parent nodes of the present node
	 */
	private void decreaseParentsHeight() {

		if (!(this.height == this.parent.height - 1)) {
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

	private Node setRight(Node newNode) {
		right = newNode;
		right.parent = this;
		return right;
	}

	private Node childWithSmallerHeight() {
		if (left.height <= right.height) {
			return left;
		}
		return right;
	}

	private Node setLeft(Node newNode) {
		left = newNode;
		left.parent = this;
		left.increaseParentsHeight();
		left.isLeftChild = true;
		return left;
	}

	private void increaseParentsHeight() {
		if (!this.getParent().isNull() && !(this.getHeight() == this.parent.getHeight() - 1)) {
			if (!this.getParent().isBalanced()) {
				// increase height for all the parents
				this.parent.increaseHeight();
				this.parent.increaseParentsHeight();
			}
		}
	}

	private int getHeight() {
		return height;
	}

	private Node getParent() {
		return parent;
	}

	private void increaseHeight() {
		this.height++;
	}

	private boolean isBalanced() {
		if (!left.isNull() && !right.isNull()) {
			if (left.height == right.height) {
				return true;
			}
		}
		return false;
	}

	private Node getRight() {
		return right;
	}

	private Node getLeft() {
		return left;
	}

	/**
	 * Returns String representation
	 */
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(this.data + ", ");
		stringBuilder.append(this.left.toString());
		stringBuilder.append(this.right.toString());
		return stringBuilder.toString();
	}

}
