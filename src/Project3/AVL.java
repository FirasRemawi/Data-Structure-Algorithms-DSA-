package Project3;

public class AVL {
	private AVLNode root;
	private boolean wasRemoved;

	public AVL() {
		setRoot(null);
	}

	// Return the height of a node e
	private int height(AVLNode e) {
		return (e == null) ? -1 : e.getHeight();
	}

	// Rotate binary tree node with left child (single rotate to right)
	private AVLNode rotateWithLeftChild(AVLNode k2) {
		if (k2 == null || k2.getLeft() == null) {
			return k2; // No left child to rotate with
		}
		AVLNode k1 = k2.getLeft();
		k2.setLeft(k1.getRight());
		k1.setRight(k2);
		k2.setHeight(Math.max(height(k2.getLeft()), height(k2.getRight())) + 1);
		k1.setHeight(Math.max(height(k1.getLeft()), k2.getHeight()) + 1);
		return k1;
	}

	// Rotate binary tree node with right child (single rotate to left)
	private AVLNode rotateWithRightChild(AVLNode k1) {
		if (k1 == null || k1.getRight() == null) {
			return k1; // No right child to rotate with
		}
		AVLNode k2 = k1.getRight();
		k1.setRight(k2.getLeft());
		k2.setLeft(k1);
		k1.setHeight(Math.max(height(k1.getLeft()), height(k1.getRight())) + 1);
		k2.setHeight(Math.max(height(k2.getRight()), k1.getHeight()) + 1);
		return k2;
	}

	// Double rotate binary tree node: first left child with its right child; then
	// node k3 with new left child
	private AVLNode doubleWithLeftChild(AVLNode k3) {
		if (k3 == null || k3.getLeft() == null) {
			return k3; // No left child to rotate with
		}
		k3.setLeft(rotateWithRightChild(k3.getLeft()));
		return rotateWithLeftChild(k3);
	}

	// Double rotate binary tree node: first right child with its left child; then
	// node k1 with new right child
	private AVLNode doubleWithRightChild(AVLNode k1) {
		if (k1 == null || k1.getRight() == null) {
			return k1; // No right child to rotate with
		}
		k1.setRight(rotateWithLeftChild(k1.getRight()));
		return rotateWithRightChild(k1);
	}

	// Insert a new Martyr into the AVL tree
	public void insert(Martyr data) {
		setRoot(insert(getRoot(), data));
	}

	private AVLNode insert(AVLNode node, Martyr data) {
		if (node == null)
			return new AVLNode(data);

		if (data.getDistrict().compareTo(node.getMartyr().getDistrict()) < 0) {
			node.setLeft(insert(node.getLeft(), data));
			if (height(node.getLeft()) - height(node.getRight()) == 2) {
				if (data.getDistrict().compareTo(node.getLeft().getMartyr().getDistrict()) < 0)
					node = rotateWithLeftChild(node);
				else
					node = doubleWithLeftChild(node);
			}
		} else if (data.getDistrict().compareTo(node.getMartyr().getDistrict()) > 0) {
			node.setRight(insert(node.getRight(), data));
			if (height(node.getRight()) - height(node.getLeft()) == 2) {
				if (data.getDistrict().compareTo(node.getRight().getMartyr().getDistrict()) > 0)
					node = rotateWithRightChild(node);
				else
					node = doubleWithRightChild(node);
			}
		} else {
			if (data.getName().compareTo(node.getMartyr().getName()) < 0) {
				node.setLeft(insert(node.getLeft(), data));
				if (height(node.getLeft()) - height(node.getRight()) == 2) {
					if (data.getName().compareTo(node.getLeft().getMartyr().getName()) < 0)
						node = rotateWithLeftChild(node);
					else
						node = doubleWithLeftChild(node);
				}
			} else if (data.getName().compareTo(node.getMartyr().getName()) > 0) {
				node.setRight(insert(node.getRight(), data));
				if (height(node.getRight()) - height(node.getLeft()) == 2) {
					if (data.getName().compareTo(node.getRight().getMartyr().getName()) > 0)
						node = rotateWithRightChild(node);
					else
						node = doubleWithRightChild(node);
				}
			}
		}

		node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
		return node;
	}

	private Martyr minValue(AVLNode node) {
		Martyr minVal = node.getMartyr(); // Start with the initial node's value
		while (node.getLeft() != null) { // Continue to traverse left
			node = node.getLeft(); // Move to the left node
			minVal = node.getMartyr(); // Update the minVal to current node's value
		}
		return minVal; // Return the smallest martyr found at the leftmost node
	}

	public boolean remove(Martyr martyr) {
		wasRemoved = false; // Reset the flag
		root = removeRecursive(root, martyr); // Start recursive deletion from the root
		return wasRemoved; // Return whether the removal was successful
	}

	private AVLNode removeRecursive(AVLNode node, Martyr martyr) {
		if (node == null) {
			return null; // Base case: node not found
		}

		int compareResult = martyr.compareTo(node.getMartyr());
		if (compareResult < 0) {
			node.setLeft(removeRecursive(node.getLeft(), martyr)); // Go left
		} else if (compareResult > 0) {
			node.setRight(removeRecursive(node.getRight(), martyr)); // Go right
		} else {
			wasRemoved = true; // Martyr found and will be removed
			// Node with only one child or no child
			if (node.getLeft() == null) {
				return node.getRight();
			} else if (node.getRight() == null) {
				return node.getLeft();
			}

			// Node with two children: Get the inorder successor (smallest in the right
			// subtree)
			node.setMartyr(minValue(node.getRight()));
			node.setRight(removeRecursive(node.getRight(), node.getMartyr()));
		}

		// Update height and rebalance here if you are using AVL properties
		return node;
	}

	public Martyr find(Martyr data) {
		return find(getRoot(), data);
	}

	private Martyr find(AVLNode node, Martyr data) {
		if (node == null) {
			return null; // Base case: data not found
		}
		int districtCompare = data.getDistrict().compareTo(node.getMartyr().getDistrict());
		if (districtCompare < 0) {
			return find(node.getLeft(), data); // Search in the left subtree
		} else if (districtCompare > 0) {
			return find(node.getRight(), data); // Search in the right subtree
		} else {
			int nameCompare = data.getName().compareTo(node.getMartyr().getName());
			if (nameCompare < 0) {
				return find(node.getLeft(), data); // Further refine search in the left subtree
			} else if (nameCompare > 0) {
				return find(node.getRight(), data); // Further refine search in the right subtree
			} else {
				return node.getMartyr(); // Martyr found
			}
		}
	}

	public Martyr findByName(String name) {
		return findByName(getRoot(), name);
	}

	private Martyr findByName(AVLNode node, String name) {
		if (node == null) {
			return null; // Base case: reached the end of the path without a match
		}
		int compareResult = name.compareTo(node.getMartyr().getName());
		if (compareResult < 0) {
			return findByName(node.getLeft(), name); // Search in the left subtree
		} else if (compareResult > 0) {
			return findByName(node.getRight(), name); // Search in the right subtree
		} else {
			return node.getMartyr(); // Name matches, Martyr found
		}
	}

	// Find the minimum element in the AVL tree
	public AVLNode findMin() {
		return findMin(getRoot());
	}

	private AVLNode findMin(AVLNode node) {
		if (node == null) {
			return null;
		} else if (node.getLeft() == null) {
			return node;
		} else {
			return findMin(node.getLeft());
		}
	}

	// Find the maximum element in the AVL tree
	public AVLNode findMax() {
		return findMax(getRoot());
	}

	private AVLNode findMax(AVLNode node) {
		if (node == null) {
			return null;
		} else if (node.getRight() == null) {
			return node;
		} else {
			return findMax(node.getRight());
		}
	}

	// Search for a Martyr in the AVL tree
	public boolean contains(Martyr data) {
		return contains(getRoot(), data);
	}

	private boolean contains(AVLNode node, Martyr data) {
		if (node == null) {
			return false;
		}
		if (data.getDistrict().compareTo(node.getMartyr().getDistrict()) < 0) {
			return contains(node.getLeft(), data);
		} else if (data.getDistrict().compareTo(node.getMartyr().getDistrict()) > 0) {
			return contains(node.getRight(), data);
		} else {
			if (data.getName().compareTo(node.getMartyr().getName()) < 0) {
				return contains(node.getLeft(), data);
			} else if (data.getName().compareTo(node.getMartyr().getName()) > 0) {
				return contains(node.getRight(), data);
			} else {
				return true;
			}
		}
	}

	public int getHeight() {
		return getHeight(root);
	}

	// Recursive helper method to calculate tree height
	private int getHeight(AVLNode node) {
		if (node == null) {
			return -1; // Height of an empty tree is -1
		}
		return 1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
	}

	// Method to return the size of the AVL tree (total number of nodes)
	public int getSize() {
		return getSize(root);
	}

	private int getSize(AVLNode node) {
		if (node == null) {
			return 0;
		}
		return 1 + getSize(node.getLeft()) + getSize(node.getRight());
	}

	public void printLevelByLevelRightToLeft() {
		if (root == null) {
			System.out.println("The tree is empty");
			return;
		}

		Queue queue = new Queue(); // Your custom Queue
		queue.enQueue(root);

		while (!queue.isEmpty()) {
			int levelSize = queue.getSize(); // Get the current level size
			Stack stack = new Stack(); // Your custom Stack

			for (int i = 0; i < levelSize; i++) {
				AVLNode node = (AVLNode) queue.deQueue(); // Cast as AVLNode, since Queue returns Object
				stack.push(node); // Push nodes onto stack to reverse order

				// Enqueue child nodes
				if (node.getRight() != null) {
					queue.enQueue(node.getRight()); // Right child enqueued first
				}
				if (node.getLeft() != null) {
					queue.enQueue(node.getLeft()); // Left child enqueued second
				}
			}

			while (!stack.isEmpty()) {
				AVLNode node = (AVLNode) stack.pop(); // Cast as AVLNode
				System.out.print(node.getMartyr() + " ");
			}
		}
	}// (getRoot() != null ? "root," + getRoot() : "")

	@Override
	public String toString() {
		return "AVL " + root;
	}

	public AVLNode getRoot() {
		return root;
	}

	public void setRoot(AVLNode root) {
		this.root = root;
	}

	public static void main(String[] args) {
		AVL tree = new AVL();

	}

}
