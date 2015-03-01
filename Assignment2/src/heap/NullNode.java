package heap;

public class NullNode extends Node {

	private static NullNode instance = null;

	private NullNode() {
	}

	public static NullNode getInstance() {
		if (instance == null) {
			instance = new NullNode();
		}
		return instance;
	}

	public boolean isNull() {
		return true;
	}

	public Node add(IHeapStrategy heaStrategy, String newNodeValue) {
		return new Node(newNodeValue);
	}

	public String toString() {
		return "";
	}
	
	public boolean isBalanced() {
		return true;
	}
}
