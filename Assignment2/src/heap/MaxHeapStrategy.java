package heap;

public class MaxHeapStrategy implements IHeapStrategy {

	@Override
	public boolean add(String data, String data2) {
		if(data.compareTo(data2)>0){
			return true;
		}
		return false;
	}
}
