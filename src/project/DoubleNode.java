package project;

 class DoubleNode {
	private District element;
	private DoubleNode next;
	private DoubleNode prev;

	public DoubleNode(District element) {
		this(element, null, null);
	}

	public DoubleNode(District element, DoubleNode prev, DoubleNode next) {
		this.element = element;
		this.prev = prev;
		this.next = next;
	}

	public District getElement() {
		return element;
	}

	public DoubleNode getNext() {
		return next;
	}

	public void setNext(DoubleNode next) {
		this.next = next;
	}

	public DoubleNode getPrev() {
		return prev;
	}

	public void setPrev(DoubleNode prev) {
		this.prev = prev;
	}

	@Override
	public String toString() {
		return "Node element," + element + ", next," + next;
	}

}
