package heap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IngFilter implements Iterator<String> {

	Iterator<String> itr;
	int cursor = 1;
	int size;

	public IngFilter(Iterator<String> input) {
		List<String> itrList = new ArrayList<String>();
		while (input.hasNext()) {
			String data = input.next();
			if (data.endsWith("ing")) {
				itrList.add(data);
				size++;
			}
		}
		itr = itrList.iterator();
	}

	public boolean hasNext() {
		if (cursor <= size) {
			return true;
		}
		return false;
	}

	public String next() {
		while (hasNext()) {
			cursor++;
			return itr.next();
		}
		return null;
	}

	@Override
	public void remove() {
	}
}
