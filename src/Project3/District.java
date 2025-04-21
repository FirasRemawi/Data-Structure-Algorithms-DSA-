package Project3;

public class District {
	private String name;
	private int numberOfMartyrs;
	private Stack locationStack; // Stack to hold locations

	public District(String name) {
		this.name = name;
		this.numberOfMartyrs = 1;
		this.locationStack = new Stack();
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

	// Find or add a location to the district
	public void addOrUpdateLocation(String locationName) {
		Location location = (Location) locationStack.findLocation(locationName);
		if (location == null) {
			location = new Location(locationName);
			locationStack.push(location);
		} else {
			location.incrementMartyrCount();
		}
	}

	public Location findMaxMartyrLocation() {
		if (locationStack.isEmpty()) {
			return null; // No locations in this district
		}

		Location maxLocation = null;
		int maxMartyrs = 0;

		// Temporary stack to hold elements while iterating
		Stack tempStack = new Stack();

		// Traverse the location stack
		while (!locationStack.isEmpty()) {
			Location currentLocation = (Location) locationStack.pop();
			if (currentLocation.getNumberOfMartyrs() > maxMartyrs) {
				maxMartyrs = currentLocation.getNumberOfMartyrs();
				maxLocation = currentLocation;
			}
			tempStack.push(currentLocation); // Push to temporary stack to preserve order
		}

		// Restore the original stack from temporary
		while (!tempStack.isEmpty()) {
			locationStack.push(tempStack.pop());
		}

		return maxLocation;
	}

	public Stack getLocationStack() {
		return locationStack;
	}

	@Override
	public String toString() {
		return name;
	}
}
