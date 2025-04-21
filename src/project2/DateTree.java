package project2;

public class DateTree {
	private DateNode root;

	public DateTree() {
		root = null;
	}

	public boolean contains(String date) {
		return contains(date, root);
	}

	private boolean contains(String date, DateNode current) {
		if (current == null) {
			return false;
		} else if (date.compareTo(current.getDate().getDate()) < 0) {
			return contains(date, current.getLeft());
		} else if (date.compareTo(current.getDate().getDate()) > 0) {
			return contains(date, current.getRight());
		}
		return true;
	}

	public DateNode insert(Date date) {
		return root = insertRec(root, date);
	}

	private DateNode insertRec(DateNode root, Date date) {
		if (root == null) {
			root = new DateNode(new Date(date.getDate()));
			root.setHead(date.getMartyrList());
			return root;
		}
		if (date.getDate().compareTo(root.getDate().getDate()) < 0) {
			root.setLeft(insertRec(root.getLeft(), date));
		} else if (date.getDate().compareTo(root.getDate().getDate()) > 0) {
			root.setRight(insertRec(root.getRight(), date));
		}
		return root;
	}

	public DateNode findMin() {
		return findMin(root);
	}

	private DateNode findMin(DateNode current) {
		if (current == null || current.getLeft() == null) {
			return current;
		}
		return findMin(current.getLeft());
	}

	public DateNode findMax() {
		return findMax(root);
	}

	private DateNode findMax(DateNode current) {
		if (current == null || current.getRight() == null) {
			return current;
		}
		return findMax(current.getRight());
	}

	public void remove(String date) {
		root = remove(date, root);
	}

	private DateNode remove(String date, DateNode current) {
		if (current == null) {
			return null;
		}
		if (date.compareTo(current.getDate().getDate()) < 0) {
			current.setLeft(remove(date, current.getLeft()));
		} else if (date.compareTo(current.getDate().getDate()) > 0) {
			current.setRight(remove(date, current.getRight()));
		} else if (current.getLeft() != null && current.getRight() != null) {
			DateNode min = findMin(current.getRight());
			current.setDate(min.getDate());
			current.setRight(remove(min.getDate().getDate(), current.getRight()));
		} else {
			current = (current.getLeft() != null) ? current.getLeft() : current.getRight();
		}
		return current;
	}

	public DateNode find(Date element) {
		return find(element, root);
	}

	private DateNode find(Date element, DateNode current) {
		if (current == null) {
			return null; // Not found, reached leaf node
		} else {
			int cmp = element.getDate().compareTo(current.getDate().getDate());
			if (cmp < 0)
				return find(element, current.getLeft()); // Search left subtree
			else if (cmp > 0)
				return find(element, current.getRight()); // Search right subtree
			else
				return current; // Element found
		}
	}

	public DateNode findNode(String element) {
		return findNode(element, root);
	}

	private DateNode findNode(String element, DateNode current) {
		if (current == null) {
			return null; // Not found, reached leaf node
		} else {
			int cmp = element.compareTo(current.getDate().getDate());
			if (cmp < 0)
				return findNode(element, current.getLeft()); // Search left subtree
			else if (cmp > 0)
				return findNode(element, current.getRight()); // Search right subtree
			else
				return current; // Element found
		}
	}

	public int getTotalMartyrs() {
		return getTotalMartyrsRecursive(root);
	}

	private int getTotalMartyrsRecursive(DateNode node) {
		if (node == null) {
			return 0;
		}

		int totalMartyrs = node.getMartyrs().size();
		totalMartyrs += getTotalMartyrsRecursive(node.getLeft());
		totalMartyrs += getTotalMartyrsRecursive(node.getRight());

		return totalMartyrs;
	}

	public DateNode getRoot() {
		return root;
	}
}
