package minheap;

public class HeapMain {

	public static void main(String arg[]){
		MinHeap minHeap= new MinHeap();
		int arr[]=new int[]{45,65,72,90,81,82,96,99,95};
		for(int i=0;i<arr.length;i++){
			if(arr[i]%5!=0)
			minHeap.add(Integer.toString(arr[i])+"ing");
			else
				minHeap.add(Integer.toString(arr[i]));
		}
		
		System.out.println(minHeap.getIngWords());
		//System.out.println(minHeap.printPreorder());
		minHeap.add(Integer.toString(65));
		//System.out.println(minHeap.printPreorder());
		System.out.println(minHeap.getIngWords());
		MinHeap minHeap2= new MinHeap();
		String arr2[]=new String[]{"eating","fruit","writing","program","testing","code","using","junit"};
		for(int i=0;i<arr2.length;i++){
			minHeap2.add(arr2[i]);
		}
		System.out.println(minHeap2.getPreorder().toString());
		System.out.println(minHeap2.getIngWords());

	}
	
	
}
