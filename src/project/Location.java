package project;

class Location {
	private String name;
	private LinkedList martyrs;

	public Location(String name) {
		this.name = name;
		this.martyrs = new LinkedList();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LinkedList getMartyrs() {
		return martyrs;
	}

	public void setMartyrs(LinkedList martyrs) {
		this.martyrs = martyrs;
	}

	@Override
	public String toString() {
		return "Location name," + name;
	}
}