package Project3;

public class Location {
	private String name;
	private int numberOfMartyrs;

	public Location(String name) {
		this.name = name;
		this.numberOfMartyrs = 1; // Start with one martyr
	}

	public void incrementMartyrCount() {
		this.numberOfMartyrs++;
	}

	public String getName() {
		return name;
	}

	public int getNumberOfMartyrs() {
		return numberOfMartyrs;
	}

	@Override
	public String toString() {
		return "Location{" + "name='" + name + '\'' + ", numberOfMartyrs=" + numberOfMartyrs + '}';
	}
}
