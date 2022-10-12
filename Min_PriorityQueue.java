package hw9;

public class Min_PriorityQueue {
	private int maxSize;
	private MinHeap theHeap;
	private int nItems;

	public Min_PriorityQueue(int size)
	{ 
		theHeap = new MinHeap(size);
		maxSize = size;
		nItems = 0;
	}

	public void insert(int item)
	{
		if (nItems < maxSize) {
			theHeap.insert(item);
			nItems++;
		}
	}

	public int remove()
	{
		if (theHeap.isEmpty()) {
			return -1;
		}
		else {
			nItems--;
			return theHeap.remove().getKey();
		}
	}

	public int peek()
	{
		if (!isEmpty()) {
			Node n = theHeap.remove();
			theHeap.insert(n.getKey());
			return n.getKey();
		}
		else return -1;
	}

	public boolean isEmpty()
	{
		if (theHeap.isEmpty()) {
			return true;
		}
		else return false;
	}


	public boolean isFull()
	{
		if (nItems == maxSize) {
			return true;
		}
		else return false;
	}

	public static void main(String[] args) {
		Min_PriorityQueue thePQ = new Min_PriorityQueue(5);

		thePQ.insert(30);
		thePQ.insert(50);
		thePQ.insert(10);
		thePQ.insert(40);
		thePQ.insert(20);

		while (!thePQ.isEmpty()) {
			long item = thePQ.remove();
			System.out.print(item + " ");
		}
		System.out.println("");
	}
}