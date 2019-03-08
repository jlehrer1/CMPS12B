class Dictionary implements DictionaryInterface {

	private class Node {
		String keyString, valueString;
		Node next;
		int numItems;

		Node(String key, String value) {
			keyString = key;
			valueString = value; 
			next = null;
		}
 	}

	private Node head;
	private int numItems;

	public Dictionary() {
		head = null;
		numItems = 0;
	}

	public Node findKey(String key) {
		Node n = head;
		//System.out.println(head.keyString);
		while (n != null) {
			if (n.keyString.equals(key)) {
				return n;
			}
			n = n.next;
		}
		return n;
	}

	public boolean isEmpty() {
		return numItems == 0;
	}

	public int size() {
		return numItems;
	}

	public String lookup(String key) {
		Node toSearch = findKey(key);
		if (toSearch != null) {
			return toSearch.valueString;
		} else {
			return null;
		}
	}

	public void insert(String key, String value) throws DuplicateKeyException {
		Node n = head;
		if (findKey(key) != null) {
			throw new DuplicateKeyException("Cannot insert duplicate keys");
		}
		else {
			if (n == null) {
				head = new Node(key,value);
				head.next = null;
			} else {
				while (n.next != null) {
					n = n.next;
				}
				Node append = new Node(key, value);
				n.next = append;
			}
			numItems++;
		}
	}

	public void delete(String key) throws KeyNotFoundException {
		Node temp = head;
		Node prev = null;
		if (findKey(key) == null) {
			throw new KeyNotFoundException("Error: Key not found");
		} else {
			if (temp != null && temp.keyString == key) {
				head = temp.next;
				numItems--;
				return;
			}
			while (temp != null && temp.keyString != key) {
				prev = temp;
				temp = temp.next;
			}
			prev.next = temp.next;
			numItems--;
		}
	}

	public void makeEmpty() {
		head = null;
		numItems = 0;
	}

	public String toString() {
		Node N = head;
		String test = "";
		// for (int i = 0; i < numItems - 1; i++) {
		// 	N = N.next;
		// 	test += N.keyString + " " + N.valueString + "\n";
		// }
		while (N != null) {
			test += N.keyString + " " + N.valueString + "\n";
			N = N.next;
		}
		return test;
	}
}