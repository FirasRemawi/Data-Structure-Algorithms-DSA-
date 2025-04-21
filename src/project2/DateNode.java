package project2;

public class DateNode implements BSTNode {
	private Date date; // Date stored as a string
	private LinkedList head;
	private DateNode left;
	private DateNode right;

	public DateNode(Date date) {
		if (!isValidDate(date.getDate())) {
			return;
		}
		this.date = date;
		this.head = new LinkedList(); 
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		if (!isValidDate(date.getDate())) {
			return;
		}
		this.date = date;
	}

	public LinkedList getHead() {
		return head;
	}

	public void setHead(LinkedList martyrList) {
		this.head = martyrList;
	}

	@Override
	public DateNode getLeft() {
		return left;
	}

	@Override
	public void setLeft(BSTNode node) {
		if (node instanceof DateNode || node == null) {
			this.left = (DateNode) node;
		} else {
			throw new IllegalArgumentException("Left child must be a DateNode or null");
		}
	}

	@Override
	public DateNode getRight() {
		return right;
	}

	@Override
	public void setRight(BSTNode node) {
		if (node instanceof DateNode || node == null) {
			this.right = (DateNode) node;
		} else {
			throw new IllegalArgumentException("Right child must be a DateNode or null");
		}
	}

	private boolean isValidDate(String date) {
		return date.matches("\\d{1}/\\d{1}/\\d{4}") || date.matches("\\d{1}/\\d{2}/\\d{4}")
				|| date.matches("\\d{2}/\\d{1}/\\d{4}") || date.matches("\\d{2}/\\d{2}/\\d{4}"); // Simple pattern to
																									// check dates in
																									// the
		// format DD/MM/YYYY
	}

	@Override
	public String toString() {
		return "date," + getDate();
	}

	public LinkedList getMartyrs() {
		// TODO Auto-generated method stub
		return head;
	}
}
