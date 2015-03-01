package heap;

public class MinHeapStrategy implements IHeapStrategy {

	@Override
	public boolean add(String data, String data2) {
		if(data.compareTo(data2)<=0){
			return true;
		}
		return false;
	}
}
