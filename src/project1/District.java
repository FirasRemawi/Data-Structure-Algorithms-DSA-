package project1;

public class District {
	private String name;
	private LinkedList locations;

	public District(String name) {
		this.name = name;
		this.locations = new LinkedList();
	}

	// Getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LinkedList getLocations() {
		return locations;
	}

	public void setLocations(LinkedList locations) {
		this.locations = locations;
	}

	@Override
	public String toString() {
		return "District name," + name;
	}

}
