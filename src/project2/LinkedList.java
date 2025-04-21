package project2;

public class LinkedList {
	private MartyrNode Front, Back;
	private int Size;

	public LinkedList() {
		Front = Back = null;
		Size = 0;
	}

	public MartyrNode getFront() {
		return Front; // Just return the Front node directly without casting
	}

	public void setFront(MartyrNode front) {
		this.Front = front;
	}

	public MartyrNode getBack() {
		return Back;
	}

	public void setBack(MartyrNode Back) {
		this.Back = Back;
	}

	public int size() {
		return Size;
	}

	public void addFirst(Object element) {
		MartyrNode newNode;
		newNode = new MartyrNode(element);
		if (Size == 0) {// Empty List
			Front = Back = newNode;
		} else {
			newNode.setNext(Front);
			Front = newNode;
		}
		Size++;// update Size
	}

	public MartyrNode getFirst() {
		if (Size == 0)
			return null;
		else
			return (MartyrNode) Front.getElement();
	}

	public void addLast(Object element) {
		MartyrNode newNode;
		newNode = new MartyrNode(element);
		if (Size == 0) {// Empty List
			Front = Back = newNode;
		} else {
			Back.setNext(newNode);
			Back = newNode; // Or Back=Back.next;
		}
		Size++;// update Size
	}

	public MartyrNode getLast() {
		if (Size == 0)
			return null;
		else
			return (MartyrNode) Back.getElement();
	}

	public MartyrNode get(int index) {
		if (Size == 0)
			return null; // empty list
		else if (index == 0)
			return getFirst(); // first element
		else if (index == Size - 1)
			return getLast(); // last element
		else if (index > 0 && index < Size - 1) {
			MartyrNode current = Front;
			for (int i = 0; i < index; i++)
				current = current.getNext();
			return (MartyrNode) current.getElement();
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
			MartyrNode newNode = new MartyrNode(element);
			MartyrNode current = Front; // used to advance to proper position
			for (int i = 0; i < index - 1; i++)
				current = current.getNext();
			newNode.setNext(current.getNext());
			current.setNext(newNode);
			Size++;// update size
		}
	}

	public MartyrNode findMartyrNode(String martyrName) {
		MartyrNode current = Front;
		while (current != null) {
			if (((Martyr) current.getElement()).getName().equalsIgnoreCase(martyrName)) {
				return current;
			}
			current = current.next;
		}
		return null;
	}

	public void addMartyrSorted(Martyr martyr) {
		MartyrNode newNode = new MartyrNode(martyr);
		if (Size == 0 || martyr.getAge() <= ((Martyr) Front.getElement()).getAge()) {
			addFirst(martyr);

		} else if (martyr.getAge() >= ((Martyr) Back.getElement()).getAge()) {
			addLast(martyr);

		} else {
			MartyrNode current = Front;
			while (current.getNext() != null && martyr.getAge() > ((Martyr) current.getNext().getElement()).getAge()) {
				current = current.getNext();
			}

			newNode.setNext(current.getNext());
			current.setNext(newNode);
			Size++;
		}
	}

	public void addMartyrNameSorted(Martyr martyr) {
		MartyrNode newNode = new MartyrNode(martyr);

		// If the list is empty or the new martyr should be inserted at the beginning
		if (Size == 0 || martyr.getName().compareToIgnoreCase(((Martyr) Front.getElement()).getName()) <= 0) {
			addFirst(martyr);
		}
		// If the new martyr should be inserted at the end
		else if (martyr.getName().compareToIgnoreCase(((Martyr) Back.getElement()).getName()) >= 0) {
			addLast(martyr);
		}
		//in the middle
		else {
			MartyrNode current = Front;
			while (current.getNext() != null
					&& martyr.getName().compareToIgnoreCase(((Martyr) current.getNext().getElement()).getName()) > 0) {
				current = current.getNext();
			}

			newNode.setNext(current.getNext());
			current.setNext(newNode);
			Size++;
		}
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
			MartyrNode current = Front;
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

		MartyrNode current = Front;
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
			MartyrNode current = Front;
			for (int i = 0; i < index - 1; i++)
				current = current.getNext();
			current.setNext(current.getNext().getNext());
			Size--;
			return true;
		} else
			return false; // out of boundary(invalid index)
	}

	public void print(MartyrNode current) {
		if (current != null) {
			System.out.println(current.getElement().toString());
			print(current.getNext());
		}
	}

	public void clear() {
		MartyrNode current = Front;
		while (current != null) {
			MartyrNode next = current.getNext();
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

	public void insert(String name, String date, byte age, String location, String district, char gender) {
		Martyr martyr = new Martyr();
		martyr.setName(name);
		martyr.setAge(age);
		martyr.setGender(gender);
		martyr.setDistrict(district);
		martyr.setLocation(location);
		addMartyrSorted(martyr);

	} // Example of a method to find a martyr by name part, assuming names are unique
		// for simplicity

	public LinkedList searchByNamePart(String partOfName) {
		LinkedList results = new LinkedList();
		MartyrNode current = Front;
		while (current != null) {
			if (current.getMartyr().getName().toLowerCase().contains(partOfName.toLowerCase())) {
				results.addMartyrSorted(current.getMartyr());
			}
			current = current.getNext();
		}
		return results;
	}

	public void addAll(LinkedList other) {
		MartyrNode current = other.Front;
		while (current != null) {
			this.addMartyrSorted(current.getMartyr());
			current = current.getNext();
		}
	}

}
