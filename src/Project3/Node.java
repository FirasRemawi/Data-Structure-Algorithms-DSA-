package Project3;

class Node {
	Object element;
	Node next;
	Node prev;
	int cursorNext;

	public Node(Object element) {
		this(element, null, null);
	}

	public Node(Object element, Node prev, Node next) {
		this.element = element;
		this.prev = prev;
		this.next = next;
	}

	public Node(Object element, int next) {
		this.element = element;
		this.cursorNext = next;
	}

	public Object getElement() {
		return element;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Node getPrev() {
		return prev;
	}

	public void setPrev(Node prev) {
		this.prev = prev;
	}

	@Override
	public String toString() {
		return "Node element," + element + ", next," + next;
	}

}
