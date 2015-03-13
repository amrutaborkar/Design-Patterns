package minheap;

import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.AbstractQueue;
import java.util.Iterator;

public class heapQ extends AbstractList<String>{

	@Override
	public String get(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

/*
	@Override
	public boolean offer(String arg0) {
		// implemented
		return false;
	}

	@Override
	public String peek() {
		//  implemented
		return null;
	}

	@Override
	public String poll() {
		// implemented
		return null;
	}

	@Override
	public Iterator<String> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// implemented
		return 0;
	}
*/
}
/*
Node findLastNode(Node root)
{
    if (root.left == nil)
        return root

    Queue q = new Queue();

    q.enqueue(root);
    Node n = null;

    while(!q.isEmpty()){
     n = q.dequeue();
    if ( n.left != null )
        q.enqueue(n.left);
    if ( n.right != null )
        q.enqueue(n.right);
       }
  return n;
}*/