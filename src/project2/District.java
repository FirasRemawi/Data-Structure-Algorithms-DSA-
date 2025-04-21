package project2;

public class District {
	private String name;
	private LocationTree locationTree;

	public District(String name) {
		this.name = name;
		this.locationTree = new LocationTree();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocationTree getLocationTree() {
		return locationTree;
	}

	public void setLocationTree(LocationTree locationTree) {
		this.locationTree = locationTree;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		District district = (District) obj;
		return name != null ? name.equals(district.name) : district.name == null;
	}

	public String toString() {
		return "District{" + "name='" + name + '\'' + ", locationTree=" + locationTree + '}';
	}
}
