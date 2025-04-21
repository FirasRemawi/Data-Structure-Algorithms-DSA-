package project1;

public class DoubleLinkedList {
	private DistrictNode front, back;
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

	public DistrictNode getFront() {
		return front;
	}

	public void setFront(DistrictNode front) {
		this.front = front;
	}

	public DistrictNode getBack() {
		return back;
	}

	public void setBack(DistrictNode back) {
		this.back = back;
	}

	public void addFirst(String element) {
		DistrictNode newNode = new DistrictNode(new District(element));
		if (isEmpty()) {
			front = back = newNode;
		} else {
			newNode.setNext(front);
			front.setPrev(newNode);
			front = newNode;
		}
		size++;
	}

	public void addLast(String element) {
		DistrictNode newNode = new DistrictNode(new District(element));
		if (isEmpty()) {
			front = back = newNode;
		} else {
			back.setNext(newNode);
			newNode.setPrev(back);
			back = newNode;
		}
		size++;
	}

	public String removeFirst() {
		if (isEmpty())
			return null;
		String element = (String) front.getDistrict().getName();
		if (front == back) {
			front = back = null;
		} else {
			front = front.getNext();
			front.setPrev(null);
		}
		size--;
		return element;
	}

	public String removeLast() {
		if (isEmpty())
			return null;
		String element = (String) back.getDistrict().getName();
		if (front == back) {
			front = back = null;
		} else {
			back = back.getPrev();
			back.setNext(null);
		}
		size--;
		return element;
	}

	public void add(int index, String element) {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		if (index == 0) {
			addFirst(element);
		} else if (index == size) {
			addLast(element);
		} else {
			DistrictNode newNode = new DistrictNode(new District(element));
			DistrictNode current = front;
			for (int i = 0; i < index; i++)
				current = current.getNext();

			newNode.setNext(current);
			newNode.setPrev(current.getPrev());
			current.getPrev().setNext(newNode);
			current.setPrev(newNode);
			size++;
		}
	}

	public void add(DistrictNode element) {

	}

	public DistrictNode findDistrictNode(String name) {
		DistrictNode current = front;
		while (current != null) {
			if (((String) current.getDistrict().getName()).equalsIgnoreCase(name)) {
				return current; // Node found
			}
			current = current.getNext();
		}
		return null; // Node not found
	}

	public void insertSorted(DistrictNode districtName) {
		DistrictNode Node = findDistrictNode(districtName.getDistrict().getName());

		if (Node != null) {
			return;
		}

		DistrictNode newNode = districtName;
		if (front == null || ((String) front.getDistrict().getName())
				.compareToIgnoreCase((String) districtName.getDistrict().getName()) > 0) {
			newNode.setNext(front);
			if (front != null) {
				front.setPrev(newNode);
			} else {
				back = newNode;
			}
			front = newNode;
		} else {
			DistrictNode current = front;
			while (current.getNext() != null && ((String) current.getNext().getDistrict().getName())
					.compareToIgnoreCase((String) districtName.getDistrict().getName()) < 0) {
				current = current.getNext();
			}
			newNode.setNext(current.getNext());
			if (current.getNext() != null) {
				current.getNext().setPrev(newNode);
			} else {
				back = newNode;
			}
			current.setNext(newNode);
			newNode.setPrev(current);
		}
		size++;
	}

	public boolean remove(String element) {
		DistrictNode current = front;
		while (current != null && !current.getDistrict().getName().equals(element)) {
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
		DistrictNode current = front;
		while (current != null) {
			DistrictNode next = current.getNext();
			current.setPrev(null);
			current.setNext(null);
			current = next;
		}
		front = back = null;
		size = 0;
	}

	public String get(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Invalid index: " + index);

		DistrictNode current;
		if (index < size / 2) { // Start from front
			current = front;
			for (int i = 0; i < index; i++)
				current = current.getNext();
		} else { // Start from back
			current = back;
			for (int i = size - 1; i > index; i--)
				current = current.getPrev();
		}
		return (String) current.getDistrict().getName();
	}

	public void print(DistrictNode current) {
		if (current != null) {
			System.out.println(current.getDistrict().getName() + (current.getNext() != null ? " <-> " : ""));
			print(current.getNext());
		}
	}

}
