package project1;

public class MartyrNode extends Node {
	Martyr martyr;
	MartyrNode next;

	public MartyrNode(Martyr martyr) {
		super(new Martyr());
		this.martyr = martyr;

	}

	public MartyrNode getNext() {
		return next;
	}

	public void setNext(MartyrNode next) {
		this.next = next;
	}

	public Martyr getMartyr() {
		return martyr;
	}

	public void setMartyr(Martyr martyr) {
		this.martyr = martyr;
	}

	@Override
	public String toString() {
		// Assuming Martyr has a meaningful toString implementation
		return "martyr: " + martyr.toString();
	}

	public void insertSorted(MartyrNode martyrNode) {
		// TODO Auto-generated method stub
		
	}
}
