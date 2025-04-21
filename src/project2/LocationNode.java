package project2;

public class LocationNode implements BSTNode {
	private Location element;
	LocationNode left;
	LocationNode right;
	private DateTree head;

	public LocationNode(Location LocationName) {
		this.element = LocationName;
		this.left = null;
		this.right = null;
		this.head = new DateTree(); 
	}

	public Location getLocation() {
		return element;
	}

	public void setLocationName(Location locationName) {
		this.element = locationName;
	}

	public LocationNode getLeft() {
		return left;
	}

	public LocationNode getRight() {
		return right;
	}

	@Override
	public void setLeft(BSTNode node) {
		if (node instanceof LocationNode || node == null) {
			this.left = (LocationNode) node;
		} else {
			throw new IllegalArgumentException("Left child must be a LocationNode or null");
		}
	}

	@Override
	public void setRight(BSTNode node) {
		if (node instanceof LocationNode || node == null) {
			this.right = (LocationNode) node;
		} else {
			throw new IllegalArgumentException("Right child must be a LocationNode or null");
		}
	}

	public Location getElement() {
		return element;
	}

	public void setElement(Location element) {
		this.element = element;
	}

	public DateTree getHead() {
		return head;
	}

	public void setHead(DateTree head) {
		this.head = head;
	}

}
