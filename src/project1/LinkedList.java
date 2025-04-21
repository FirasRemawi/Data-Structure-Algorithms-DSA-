package project1;

public class LinkedList {
	private Node front, back;
	private int size;

	public LinkedList() {
		front = back = null;
		size = 0;
	}

	public LocationNode findLocationNode(LocationNode location) {
		LocationNode current = (LocationNode) front;
		while (current != null) {
			if (current.getLocationName() == location.getLocationName()) {
				return current;

			}
			current = current.next;
		}
		return null;
	}

	public void insertLocationSorted(LocationNode location) {
		LocationNode newNode = new LocationNode(location.getLocationName());

		if (front == null
				|| ((LocationNode) front).getLocationName().compareToIgnoreCase(location.getLocationName()) >= 0) {
			addFirst(newNode);
		} else {
			Node current = front;
			while (current.getNext() != null && ((LocationNode) current.getNext()).getLocationName()
					.compareToIgnoreCase(location.getLocationName()) < 0) {
				current = current.getNext();
			}
			// Insert the new node after 'current'
			newNode.setNext(current.getNext());
			current.setNext(newNode);
		}

		size++; // Increase the size of the list
	}

	public void add(Node element) {
		add(getSize(), element);
	}

	public void add(int index, Node element) {
		if (index == 0)
			addFirst(element);
		else if (index >= getSize())
			addLast(element);
		else {
			Node newNode = new Node(element);
			Node current = front; // used to advance to proper position
			for (int i = 0; i < index - 1; i++)
				current = current.getNext();
			newNode.setNext(current.getNext());
			current.setNext(newNode);
			size++;// update size
		}
	}

	/*
	 * public void insertMartyrSorted(MartyrNode newNode) { Martyr martyr =
	 * newNode.getMartyr(); if (front == null || ((Martyr) front.getData()).getAge()
	 * >= martyr.getAge()) { if (front == null) { back = newNode; }
	 * newNode.setNext(front); front = newNode; } else { Node current = front; while
	 * (current.getNext() != null && ((Martyr) current.getNext().getData()).getAge()
	 * < martyr.getAge()) { current = current.getNext(); }
	 * newNode.setNext(current.getNext()); current.setNext(newNode);
	 * 
	 * if (current == back) { back = newNode; } } size++; }
	 */

	public Object findNode(String name) {
		Node current = front;
		while (current != null) {
			if (current instanceof LocationNode && ((Location) current.getData()).getName().equalsIgnoreCase(name)) {
				return (LocationNode) current; // Location found
			} else if (current instanceof MartyrNode && ((Martyr) current.getData()).getName().equalsIgnoreCase(name)) {
				return current; // Martyr found
			}
			current = current.getNext();
		}
		return null; // Node not found
	}

	public void addFirst(Node newNode) {
		if (size == 0) {
			front = back = newNode;
		} else {
			newNode.setNext(front);
			front = newNode;
		}
		size++;
	}

	// Add a new Node to the end of the list
	public void addLast(Node newNode) {
		if (size == 0) {
			front = back = newNode;
		} else {
			back.setNext(newNode);
			back = newNode;
		}
		size++;
	}

	public Object removeFirst() {
		if (size == 0) {
			return null; // Empty list
		}

		Object data = front.getData();
		front = front.getNext();
		if (front == null) {
			back = null; // List became empty
		}
		size--;
		return data;
	}

	// Removes the last node from the list and returns its data
	public Object removeLast() {
		if (size == 0) {
			return null; // Empty list
		}

		Object data = back.getData();
		if (size == 1) {
			front = back = null; // Only one element was in the list
		} else {
			// Iterate to the second-to-last node
			Node current = front;
			while (current.getNext() != back) {
				current = current.getNext();
			}
			back = current;
			back.setNext(null);
		}
		size--;
		return data;
	}

	// Removes a node at a specific index and returns its data
	public Object remove(int index) {
		if (index < 0 || index >= size) {
			return null; // Index out of bounds
		}

		if (index == 0) {
			return removeFirst();
		}

		if (index == size - 1) {
			return removeLast();
		}

		// Iterate to the node before the one we want to remove
		Node current = front;
		for (int i = 0; i < index - 1; i++) {
			current = current.getNext();
		}

		Object data = current.getNext().getData();
		current.setNext(current.getNext().getNext());
		size--;
		return data;
	}

	// Retrieves the data at a specific index
	public Object get(int index) {
		if (index < 0 || index >= size) {
			return null; // Index out of bounds
		}

		Node current = front;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		return current.getData();
	}

	// Clears the list
	public void clear() {
		Node current = front;
		while (current != null) {
			Node next = current.getNext();
			current.setNext(null);
			current = next;
		}
		front = back = null;
		size = 0;
	}

	// Prints all elements in the list
	public void printList() {
		Node current = front;
		while (current != null) {
			System.out.println(current.getData());
			current = current.getNext();
		}
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Node getFront() {
		return front;
	}

	public void setFront(LocationNode front) {
		this.front = front;
	}

	public Node getBack() {
		return back;
	}

	public void setBack(LocationNode back) {
		this.back = back;
	}

	public int getSize() {
		return size;
	}

}
