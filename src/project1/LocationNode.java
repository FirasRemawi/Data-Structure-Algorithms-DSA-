package project1;

public class LocationNode extends Node{
	private String locationName;
	LocationNode next;

	public LocationNode() {
		super(null);
	}

	public LocationNode(String locationName) {
		super(new Location(locationName));
		this.locationName = locationName;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public void setNext(LocationNode next) {
		this.next = next;
	}

	public LocationNode getNext() {
		return next;
	}

	@Override
	public String toString() {
		return " " + locationName;
	}

	public void insertSorted(LocationNode locationNode) {
		// TODO Auto-generated method stub

	}

}
