package project2;

public class DistrictTree {
	private DistrictNode root;

	public DistrictTree() {
		root = null;
	}

	// Public method to check whether the district name is in the tree
	public boolean contains(District districtName) {
		return contains(districtName, root);
	}

	// Private helper method for contains
	private boolean contains(District districtName, DistrictNode current) {
		if (current == null) {
			return false; // Not found, empty subtree.
		} else if (districtName.getName().compareToIgnoreCase(current.getDistrictName().getName()) < 0) {
			return contains(districtName, current.left); // Search left subtree
		} else if (districtName.getName().compareToIgnoreCase(current.getDistrictName().getName()) > 0) {
			return contains(districtName, current.right); // Search right subtree
		}
		return true; // District found.
	}

	// Public method to insert a district name
	public DistrictNode insert(District districtName) {
		return root = insertRec(root, districtName);
	}

	private DistrictNode insertRec(DistrictNode root, District districtName) {
		if (root == null) {
			root = new DistrictNode(districtName);
			root.setHead(new LocationTree());
			return root;
		}

		int cmp = districtName.getName().compareToIgnoreCase(root.getDistrictName().getName());
		if (cmp < 0) {
			root.left = insertRec(root.left, districtName);
		} else if (cmp > 0) {
			root.right = insertRec(root.right, districtName);
		}
		return root;
	}

	// Public method to find the minimum district name
	public DistrictNode findMin() {
		return findMin(root);
	}

	// Private helper method to find the minimum district name
	private DistrictNode findMin(DistrictNode current) {
		if (current == null) {
			return null; // No elements in the tree
		} else if (current.left == null) {
			return current; // Minimum found
		} else {
			return findMin(current.left); // Continue to left subtree
		}
	}

	// Public method to find the maximum district name
	public DistrictNode findMax() {
		return findMax(root);
	}

	// Private helper method to find the maximum district name
	private DistrictNode findMax(DistrictNode current) {
		if (current == null) {
			return null; // No elements in the tree
		} else if (current.right == null) {
			return current; // Maximum found
		} else {
			return findMax(current.right); // Continue to right subtree
		}
	}

	public void remove(String element) {
		root = remove(element, root);
	}

	private DistrictNode remove(String element, DistrictNode current) {
		if (current == null) {
			return null; // Element not found
		}
		int cmp = element.compareToIgnoreCase(current.getDistrictName().getName());
		if (cmp < 0) {
			current.left = remove(element, current.left); // Search left subtree
		} else if (cmp > 0) {
			current.right = remove(element, current.right); // Search right subtree
		} else {
			if (current.left != null && current.right != null) { // Two children
				DistrictNode min = findMin(current.right);
				current.setDistrictName(min.getDistrictName()); // Copy min's values to current
				current.getDistrictName().setLocationTree(min.getDistrictName().getLocationTree()); // Ensure location
																									// tree is also
																									// copied
				current.right = remove(min.getDistrictName().getName(), current.right); // Remove the min node
			} else {
				current = (current.left != null) ? current.left : current.right;
			}
		}
		return current;
	}

	public DistrictNode find(District element) {
		return find(element, root);
	}

	private DistrictNode find(District element, DistrictNode current) {
		if (current == null) {
			return null; // Not found, reached leaf node
		} else {
			
			int cmp = element.getName().compareToIgnoreCase(current.getDistrictName().getName());
			if (cmp < 0)
				return find(element, current.getLeft()); // Search left subtree
			else if (cmp > 0)
				return find(element, current.getRight()); // Search right subtree
			else {
				return current; // Element found
			}
		}
	}

	public DistrictNode findNode(String element) {
		return findNode(element, root);
	}

	private DistrictNode findNode(String element, DistrictNode current) {
		if (current == null) {
			return null; // Not found, reached leaf node
		}
		int cmp = element.compareToIgnoreCase(current.getDistrictName().getName());
		if (cmp < 0) {
			return findNode(element, current.getLeft()); // Search left subtree
		} else if (cmp > 0) {
			return findNode(element, current.getRight()); // Search right subtree
		} else {
			return current; // Element found
		}
	}

	public void inOrderTraversal() {
		System.out.println("Starting in-order traversal:");
		inOrderHelper(root);
		System.out.println("Traversal complete.");
	}

	// Helper method to perform in-order traversal
	private void inOrderHelper(DistrictNode node) {
		if (node != null) {
			inOrderHelper(node.getLeft()); // Visit left subtree
			System.out.println("Visited District: " + node.getDistrictName()); // Process node
			inOrderHelper(node.getRight()); // Visit right subtree
		} else
			System.out.println("unvistied");
	}

	public void printTree() {
		System.out.println("District Tree Structure:");
		printTreeHelper(root);
	}

	void printTreeHelper(DistrictNode node) {
		if (node != null) {
			printTreeHelper(node.getLeft());
			System.out.println("District: " + node.getDistrictName().getName());
			if (node.getDistrictName().getLocationTree() != null) {
				System.out.println("  Locations:");
				node.getHead().printTree();

			}
			printTreeHelper(node.getRight());
		}
	}

	public DistrictNode getRoot() {
		return root;
	}

	public void clear() {
		root = null;
	}

	public District[] toList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "DistrictTree " + (root != null ? "root," + root : "");
	}

}