class Queue implements QueueInterface {
	private class Node {
		Object item;
		Node next;

		Node(Object newNode) {
			item = newNode;
			next = null;
		}
	}
	
	private Node head;
	private Node tail;
	private int numItems;

	public Queue() {
		head = null;
		tail = null;
		numItems = 0;
	}

	public boolean isEmpty() {
		return numItems == 0;
	}

	public int length() {
		return numItems;
	}

	public void enqueue(Object newItem) {
		Node N = new Node(newItem);
		if (isEmpty()) {
			head = N;
			tail = head;
		} else {
			tail.next = N;
			tail = N;
		}
		numItems++;
	}

	public Object dequeue() throws QueueEmptyException {
		if (isEmpty()) {
			throw new QueueEmptyException("Error: cannot call dequeue() on an empty Queue");
		}

		Object temp = head.item;
		if (head == tail) {
			head = null;
			tail = null;
		} else {
			head = head.next;
		}

		numItems--;
		return temp;
	}

	public Object peek() throws QueueEmptyException {
		if (isEmpty()) {
			throw new QueueEmptyException("Error: cannot called peej() on an empty Queue");
		}
		return head.item;

	}

   	public void dequeueAll() throws QueueEmptyException {
   		if (isEmpty()) {
   			throw new QueueEmptyException("Error: cannot called dequeueAll() on an empty Queue");
   		}
   		numItems = 0;
   		head = null;
   		tail = null;
   	}

   	public String toString() {
   		String toReturn = "";
   		Node N = head;

   		while (N != null) {
   			toReturn += N.item.toString() + " ";
   			N = N.next;
   		}
   		return toReturn;
   	}

}