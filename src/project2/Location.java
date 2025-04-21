package project2;

public class Location {
	private String name;
	private DateTree dateTree;

	public Location(String name) {
		this.name = name;
		this.dateTree = new DateTree();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DateTree getDateTree() {
		return dateTree;
	}

	public void setDateTree(DateTree dateTree) {
		this.dateTree = dateTree;
	}

	public int getNumberOfMartyrs() {
		// Traverse the date tree and count the martyrs in each date
		return countMartyrs(dateTree.getRoot());
	}

	private int countMartyrs(DateNode dateNode) {
		if (dateNode == null) {
			return 0; // Base case: No nodes in the date tree
		}

		// Get the number of martyrs in the current date
		int martyrsInCurrentDate = dateNode.getMartyrs().size();

		// Recursively calculate martyrs in left and right subtrees
		int martyrsInLeftSubtree = countMartyrs(dateNode.getLeft());
		int martyrsInRightSubtree = countMartyrs(dateNode.getRight());

		// Total martyrs = current date + left subtree + right subtree
		return martyrsInCurrentDate + martyrsInLeftSubtree + martyrsInRightSubtree;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Location location = (Location) obj;
		return name != null ? name.equalsIgnoreCase(location.name) : location.name == null;
	}

	@Override
	public String toString() {
		return "Location name: " + name;
	}
}
