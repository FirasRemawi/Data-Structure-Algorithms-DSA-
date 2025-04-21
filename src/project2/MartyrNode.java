package project2;

class MartyrNode {
	Object element;
	MartyrNode next;

	public MartyrNode(Object element) {
		this(element, null);
	}

	public MartyrNode(Object element, MartyrNode next) {
		this.element = element;
		this.next = next;
	}

	public Object getElement() {
		return element;
	}

	public void setElement(Object element) {
		this.element = element;
	}

	public MartyrNode getNext() {
		return next;
	}

	public void setNext(MartyrNode next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "Node element," + element + ", next," + next;
	}

	public Martyr getMartyr() {
		// TODO Auto-generated method stub
		return ((Martyr) element);
	}

}
