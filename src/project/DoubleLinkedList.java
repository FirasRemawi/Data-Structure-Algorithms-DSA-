package project;

public class DoubleLinkedList {
	private DoubleNode front, back;
	private int size;

	public DoubleLinkedList() {
		front = back = null;
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void addFirst(District element) {
		DoubleNode newNode = new DoubleNode(element);
		if (isEmpty()) {
			front = back = newNode;
		} else {
			newNode.setNext(front);
			front.setPrev(newNode);
			front = newNode;
		}
		size++;
	}

	public void addLast(District element) {
		DoubleNode newNode = new DoubleNode(element);
		if (isEmpty()) {
			front = back = newNode;
		} else {
			back.setNext(newNode);
			newNode.setPrev(back);
			back = newNode;
		}
		size++;
	}

	public void addSorted(District element) {
		DoubleNode newNode = new DoubleNode(element);
		if (isEmpty() || element.getName().compareToIgnoreCase(front.getElement().getName()) <= 0) {
			addFirst(element);
		} else if (element.getName().compareToIgnoreCase(back.getElement().getName()) > 0) {
			addLast(element);
		} else {
			DoubleNode current = front;
			while (current.getNext() != null
					&& element.getName().compareToIgnoreCase(current.getNext().getElement().getName()) > 0) {
				current = current.getNext();
			}
			newNode.setNext(current.getNext());
			newNode.setPrev(current);
			current.setNext(newNode);
			if (newNode.getNext() != null) {
				newNode.getNext().setPrev(newNode);
			}
			size++;
		}
	}

	public DoubleNode findDistrictNode(String name) {
		DoubleNode current = front;
		while (current != null) {
			if (current.getElement().getName().equalsIgnoreCase(name)) {
				return current; // Node found
			}
			current = current.getNext();
		}
		return null; // Node not found
	}

	public Object removeFirst() {
		if (isEmpty())
			return null;
		Object element = front.getElement();
		if (front == back) {
			front = back = null;
		} else {
			front = front.getNext();
			front.setPrev(null);
		}
		size--;
		return element;
	}

	public Object removeLast() {
		if (isEmpty())
			return null;
		Object element = back.getElement();
		if (front == back) {
			front = back = null;
		} else {
			back = back.getPrev();
			back.setNext(null);
		}
		size--;
		return element;
	}

	public void add(District element) {
		add(size(), element);
	}

	public void add(int index, District element) {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		if (index == 0) {
			addFirst(element);
		} else if (index == size) {
			addLast(element);
		} else {
			DoubleNode newNode = new DoubleNode(element);
			DoubleNode current = front;
			for (int i = 0; i < index; i++)
				current = current.getNext();

			newNode.setNext(current);
			newNode.setPrev(current.getPrev());
			current.getPrev().setNext(newNode);
			current.setPrev(newNode);
			size++;
		}
	}

	public boolean remove(District element) {
		DoubleNode current = front;
		while (current != null && !current.getElement().equals(element)) {
			current = current.getNext();
		}
		if (current == null)
			return false;

		if (current == front) {
			removeFirst();
		} else if (current == back) {
			removeLast();
		} else {
			current.getPrev().setNext(current.getNext());
			current.getNext().setPrev(current.getPrev());
			size--;
		}
		return true;
	}

	public void clear() {
		DoubleNode current = front;
		while (current != null) {
			DoubleNode next = current.getNext();
			current.setPrev(null);
			current.setNext(null);
			current = next;
		}
		front = back = null;
		size = 0;
	}

	public DoubleNode get(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Invalid index: " + index);

		DoubleNode current;
		if (index < size / 2) { // Start from front
			current = front;
			for (int i = 0; i < index; i++)
				current = current.getNext();
		} else { // Start from back
			current = back;
			for (int i = size - 1; i > index; i--)
				current = current.getPrev();
		}
		return current;
	}

	public DoubleNode getFront() {
		return front;
	}

	public void setFront(DoubleNode front) {
		this.front = front;
	}

	public DoubleNode getBack() {
		return back;
	}

	public void setBack(DoubleNode back) {
		this.back = back;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void print(DoubleNode current) {
		if (current != null) {
			System.out.println(current.getElement() + (current.getElement().getLocations() != null ? " <-> " : ""));
			print(current.getNext());
		}
	}

}
