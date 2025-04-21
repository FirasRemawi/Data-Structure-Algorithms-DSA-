package project;

public class LinkedList {
	private Node Front, Back;
	private int Size;

	public LinkedList() {
		Front = Back = null;
		Size = 0;
	}

	public Node getFront() {
		return Front; // Just return the Front node directly without casting
	}

	public void setFront(Node front) {
		this.Front = front;
	}

	public Node getBack() {
		return Back;
	}

	public void setBack(Node Back) {
		this.Back = Back;
	}

	public int size() {
		return Size;
	}

	public void addFirst(Object element) {
		Node newNode;
		newNode = new Node(element);
		if (Size == 0) {// Empty List
			Front = Back = newNode;
		} else {
			newNode.setNext(Front);
			Front = newNode;
		}
		Size++;// update Size
	}

	public Object getFirst() {
		if (Size == 0)
			return null;
		else
			return Front.getElement();
	}

	public void addLast(Object element) {
		Node newNode;
		newNode = new Node(element);
		if (Size == 0) {// Empty List
			Front = Back = newNode;
		} else {
			Back.setNext(newNode);
			Back = newNode; // Or Back=Back.next;
		}
		Size++;// update Size
	}

	public Object getLast() {
		if (Size == 0)
			return null;
		else
			return Back.getElement();
	}

	public Object get(int index) {
		if (Size == 0)
			return null; // empty list
		else if (index == 0)
			return getFirst(); // first element
		else if (index == Size - 1)
			return getLast(); // last element
		else if (index > 0 && index < Size - 1) {
			Node current = Front;
			for (int i = 0; i < index; i++)
				current = current.getNext();
			return current.getElement();
		} else
			return null; // out of boundary
	}

	public void add(Object element) {
		add(size(), element);
	}

	public void add(int index, Object element) {
		if (index == 0)
			addFirst(element);
		else if (index >= size())
			addLast(element);
		else {
			Node newNode = new Node(element);
			Node current = Front; // used to advance to proper position
			for (int i = 0; i < index - 1; i++)
				current = current.getNext();
			newNode.setNext(current.getNext());
			current.setNext(newNode);
			Size++;// update size
		}
	}

	public Node findMartyrNode(String martyrName) {
		Node current = Front;
		while (current != null) {
			if (((Martyr) current.getElement()).getName().equalsIgnoreCase(martyrName)) {
				return current;
			}
			current = current.next;
		}
		return null;
	}

	public void addMartyrSorted(Martyr martyr) {
		Node newNode = new Node(martyr);
		if (Size == 0 || martyr.getAge() <= ((Martyr) Front.getElement()).getAge()) {
			addFirst(martyr);
		} else if (martyr.getAge() >= ((Martyr) Back.getElement()).getAge()) {
			addLast(martyr);
		} else {
			Node current = Front;
			while (current.getNext() != null && martyr.getAge() > ((Martyr) current.getNext().getElement()).getAge()) {
				current = current.getNext();
			}
			newNode.setNext(current.getNext());
			current.setNext(newNode);
			Size++;
		}
	}

	public void insertLocationSorted(Location location) {
		Node newNode = new Node(location); // Node ready to be inserted

		// If the list is empty or the new location should be the first element
		if (Front == null || ((Location) Front.getElement()).getName().compareToIgnoreCase(location.getName()) >= 0) {
			addFirst(location); // Use location directly
		} else {
			Node current = Front;
			// Traverse the list to find the insertion point
			while (current.getNext() != null && ((Location) current.getNext().getElement()).getName()
					.compareToIgnoreCase(location.getName()) < 0) {
				current = current.getNext();
			}
			newNode.setNext(current.getNext());
			current.setNext(newNode);
			Size++; // Increase the size
		}
	}

	public Node findLocationNode(String locationName) {
		Node current = Front;
		while (current != null) {
			if (((Location) current.getElement()).getName().equalsIgnoreCase(locationName)) {
				return current;

			}
			current = current.next;
		}
		return null;
	}

	public boolean removeFirst() {
		if (Size == 0)
			return false; // empty list
		else if (Size == 1) // one element inside list
			Front = Back = null;
		else
			Front = Front.getNext();
		Size--; // update size
		return true;
	}

	public boolean removeLast() {
		if (Size == 0)
			return false; // empty list
		else if (Size == 1) // one element inside the list
			Front = Back = null;
		else {
			Node current = Front;
			for (int i = 0; i < Size - 2; i++)
				current = current.getNext();
			current.setNext(null);
			Back = current;
		}
		Size--; // update size
		return true;
	}

	public boolean removeMartyr(String martyrName) {
		if (Front == null)
			return false; // List is empty

		// Special case for the head of the list
		if (martyrName.equalsIgnoreCase(((Martyr) Front.getElement()).getName())) {
			return removeFirst(); // Remove the first element and adjust the list
		}

		Node current = Front;
		while (current.getNext() != null) {
			// Check the next node to see if it's the one to remove
			if (martyrName.equalsIgnoreCase(((Martyr) current.getNext().getElement()).getName())) {
				// If the node to remove is the last one, adjust the Back pointer
				if (current.getNext() == Back) {
					Back = current;
				}
				current.setNext(current.getNext().getNext()); // Bypass the node to be removed
				Size--; // Decrement the size of the list
				return true; // Martyr removed successfully
			}
			current = current.getNext();
		}

		return false; // Martyr not found and not removed
	}

	public boolean remove(int index) {
		if (Size == 0)
			return false;// empty linked list
		else if (index == 0)
			return removeFirst(); // remove first element
		else if (index == size() - 1)
			return removeLast();// remove last element
		else if (index > 0 && index < Size - 1) {
			Node current = Front;
			for (int i = 0; i < index - 1; i++)
				current = current.getNext();
			current.setNext(current.getNext().getNext());
			Size--;
			return true;
		} else
			return false; // out of boundary(invalid index)
	}

	public boolean removeLocation(Location element) {
		if (Front == null)
			return false; // Empty list, nothing to remove

		// Special case for the head of the list
		if (((Location) Front.getElement()).equals(element)) {
			return removeFirst();
		}

		Node current = Front;
		while (current.getNext() != null && !((Location) current.getNext().getElement()).equals(element)) {
			current = current.getNext();
		}

		if (current.getNext() != null) {
			if (current.getNext() == Back) {
				Back = current; // Update Back pointer since we're removing the last node
			}
			current.setNext(current.getNext().getNext());
			Size--; // Decrement size after successful removal
			return true;
		}

		return false; // Element not found
	}// Find the index of the first occurrence of the specified element

	public int find(Object element) {
		Node current = Front;
		int index = 0;
		if (element instanceof Location) {
			while (current != null) {
				if (((Location) current.getElement()).getName().equalsIgnoreCase(((Location) element).getName())) {
					return index;
				}
				current = current.getNext();
				index++;
			}
			return -1; // Element not found
		}
		return -1;
	}

	public void print(Node current) {
		if (current != null) {
			System.out.println(current.getElement().toString());
			print(current.getNext());
		}
	}

	public void clear() {
		Node current = Front;
		while (current != null) {
			Node next = current.getNext();
			current.setNext(null); // Help with garbage collection
			current = next;
		}
		Front = null;
		Back = null; // For doubly linked lists
		Size = 0;
	}

	public int getSize() {
		return Size;
	}

	public boolean isEmpty() {
		return getSize() == 0;
	}

}
