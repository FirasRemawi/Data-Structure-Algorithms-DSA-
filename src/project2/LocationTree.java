package project2;

public class LocationTree {
	private LocationNode root;

	public LocationTree() {
		root = null;
	}

	// Public method to check whether the district name is in the tree
	public boolean contains(Location LocationName) {
		return contains(LocationName, root);
	}

	// Private helper method for contains
	private boolean contains(Location LocationName, LocationNode current) {
		if (current == null) {
			return false; // Not found, empty subtree.
		} else if (LocationName.getName().compareToIgnoreCase(current.getLocation().getName()) < 0) {
			return contains(LocationName, current.left); // Search left subtree
		} else if (LocationName.getName().compareToIgnoreCase(current.getLocation().getName()) > 0) {
			return contains(LocationName, current.right); // Search right subtree
		}
		return true; // District found.
	}

	// Public method to insert a district name
	public LocationNode insert(Location LocationName) {
		return root = insertRec(root, LocationName);
	}

	// Private helper method for insert
	private LocationNode insertRec(LocationNode root, Location LocationName) {
		if (root == null) {
			root = new LocationNode(LocationName);
			root.setHead(LocationName.getDateTree());
			return root;
		}
		if (LocationName.getName().compareToIgnoreCase(root.getLocation().getName()) < 0) {
			root.left = insertRec(root.left, LocationName);
		} else if (LocationName.getName().compareToIgnoreCase(root.getLocation().getName()) > 0) {
			root.right = insertRec(root.right, LocationName);
		}
		return root;
	}

	// Public method to find the minimum district name
	public LocationNode findMin() {
		return findMin(root);
	}

	// Private helper method to find the minimum district name
	private LocationNode findMin(LocationNode current) {
		if (current == null) {
			return null; // No elements in the tree
		} else if (current.left == null) {
			return current; // Minimum found
		} else {
			return findMin(current.left); // Continue to left subtree
		}
	}

	// Public method to find the maximum district name
	public LocationNode findMax() {
		return findMax(root);
	}

	// Private helper method to find the maximum district name
	private LocationNode findMax(LocationNode current) {
		if (current == null) {
			return null; // No elements in the tree
		} else if (current.right == null) {
			return current; // Maximum found
		} else {
			return findMax(current.right); // Continue to right subtree
		}
	}

	public void remove(LocationNode element) {
		root = remove(element, root);
	}

	private LocationNode remove(LocationNode element, LocationNode current) {
		if (current == null) {
			return null; // Element not found
		}
		int cmp = element.getLocation().getName().compareToIgnoreCase(current.getLocation().getName());
		if (cmp < 0)
			current.setLeft(remove(element, current.getLeft())); // Search left subtree
		else if (cmp > 0)
			current.setRight(remove(element, current.getRight())); // Search right subtree
		else if (current.getLeft() != null && current.getRight() != null) { // Two children
			LocationNode min = findMin(current.getRight());
			current.setRight(remove(min, current.getRight()));
			min.setLeft(current.getLeft());
			min.setRight(current.getRight());
			current = min;
		} else { // One child or no child
			current = (current.getLeft() != null) ? current.getLeft() : current.getRight();
		}
		return current;
	}

	public LocationNode find(Location location) {
		return find(root, location);
	}

	private LocationNode find(LocationNode current, Location location) {
		if (current == null) {
			return null;
		}
		int cmp = location.getName().compareToIgnoreCase(current.getLocation().getName());
		if (cmp < 0) {
			return find(current.getLeft(), location);
		} else if (cmp > 0) {
			return find(current.getRight(), location);
		} else {
			return current; // Found the location
		}
	}

	public LocationNode find(String element) {
		return find(element, root);
	}

	private LocationNode find(String element, LocationNode current) {
		if (current == null) {
			return null;
		} else {
			int cmp = element.compareTo(current.getLocation().getName());
			if (cmp < 0)
				return find(element, current.getLeft()); // Search left subtree
			else if (cmp > 0)
				return find(element, current.getRight()); // Search right subtree
			else
				return current; // Element found
		}
	}

	public LocationNode findNode(String locationName) {
		return findNode(locationName, root);
	}

	private LocationNode findNode(String locationName, LocationNode current) {
		if (current == null) {
			return null; // Reached leaf node without finding
		}
		int cmp = locationName.compareToIgnoreCase(current.getLocation().getName());
		if (cmp < 0) {
			return findNode(locationName, current.getLeft()); // Search left subtree
		} else if (cmp > 0) {
			return findNode(locationName, current.getRight()); // Search right subtree
		} else {
			return current; // Match found
		}
	}

	public LocationNode getRoot() {
		return root;
	}

	@Override
	public String toString() {
		return "LocationTree " + (root != null ? "root," + root : "");
	}

	public void printTree() {
		printTreeHelper(root);
	}

	private void printTreeHelper(LocationNode node) {
		if (node != null) {
			printTreeHelper(node.getLeft());
			System.out.println("    - " + node.getLocation().getName());
			printTreeHelper(node.getRight());
		}
	}

}
