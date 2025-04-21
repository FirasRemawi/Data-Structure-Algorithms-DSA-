package project2;

public class DataManager {
	private DistrictTree districtTree;
	private LinkedListStack backwardStack, forwardStack;
	private LinkedListStack locationBackwardStack, locationForwardStack;
	private LinkedListStack dateBackwardStack, dateForwardStack;
	private LinkedListQueue locationQueue;
	private StringBuilder str;

	public DataManager(DistrictTree districtTree) {
		this.districtTree = districtTree;
		forwardStack = new LinkedListStack();
		backwardStack = new LinkedListStack();
		locationBackwardStack = new LinkedListStack();
		locationForwardStack = new LinkedListStack();
		dateBackwardStack = new LinkedListStack();
		dateForwardStack = new LinkedListStack();
		locationQueue = new LinkedListQueue();
		initializeTraversal();
	}

	public DistrictTree getDistrictTree() {
		return districtTree;
	}

	public void setDistrictTree(DistrictTree districtTree) {
		this.districtTree = districtTree;
	}

	public boolean addDistrict(String name) {
		name = name.replaceAll("[^a-zA-Z]", "");
		if (name.isEmpty()) {
			return false;
		}
		District newDistrict = new District(name);
		if (districtTree.find(newDistrict) == null) {
			districtTree.insert(newDistrict);
			return true;
		}
		return false;
	}

	public boolean remove(String name) {
		return removeDistrict(name);
	}

	private boolean removeDistrict(String text) {
		if (text.isEmpty()) {
			return false;
		}
		DistrictNode current = districtTree.find(new District(text));
		if (current == null) {
			return false;
		}
		districtTree.remove(text);
		return true;
	}

	public boolean updateDistrict(String oldName, String newName) {
		newName = newName.replaceAll("[^a-zA-Z]", "");
		if (newName.isEmpty()) {
			return false;
		}
		DistrictNode node = districtTree.find(new District(oldName));
		if (node != null && !oldName.equals(newName)) {
			node.setDistrictName(new District(newName));
			return true;
		}
		return false;
	}

	boolean updateDistrictRecord(String oldName, String newName) {
		newName = newName.replaceAll("[^a-zA-Z]", "");

		if (newName.isEmpty()) {
			return false;
		}
		if (oldName.equalsIgnoreCase(newName)) {
			return false;
		}
		DistrictNode current = districtTree.find(new District(oldName));
		if (current == null) {
			return false;
		}
		current.setDistrictName(new District(newName));
		return true;
	}

	private void initializeTraversal() {
		if (districtTree == null) {
			return;
		}
		forwardStack.clear();
		DistrictNode node = districtTree.getRoot();
		pushLeft(node);
	}

	void initializeLocationTraversal(DistrictNode dis) {
		if (dis == null) {
			return;
		}
		locationQueue.clear();
		locationForwardStack.clear();
		locationBackwardStack.clear();
		LocationNode loc = dis.getHead().getRoot();
		locationTraverse(loc);
	}

	private void pushLeft(DistrictNode node) {
		while (node != null) {
			forwardStack.push(node);
			node = node.getLeft();
		}
	}

	public District nextDistrict() {
		if (forwardStack.isEmpty()) {
			return null;
		}

		DistrictNode node = (DistrictNode) forwardStack.pop();
		backwardStack.push(node);
		pushLeft(node.getRight());

		return node.getElement();
	}

	public District previousDistrict() {
		if (backwardStack.isEmpty()) {
			return null;
		}

		if (backwardStack.Size() <= 1) {
			return null;
		}

		forwardStack.push(backwardStack.pop());
		return ((DistrictNode) backwardStack.peek()).getElement();
	}

	private void locationTraverse(LocationNode node) {
		if (node == null) {
			return;
		}
		LinkedListQueue queue = new LinkedListQueue();
		queue.enQueue(node);

		while (!queue.isEmpty()) {
			LocationNode current = (LocationNode) queue.deQueue();
			locationQueue.enQueue(current);

			if (current.getLeft() != null) {
				queue.enQueue(current.getLeft());
			}
			if (current.getRight() != null) {
				queue.enQueue(current.getRight());
			}
		}
	}

	public Location nextLocation() {
		if (!locationForwardStack.isEmpty()) {
			LocationNode node = (LocationNode) locationForwardStack.pop();
			locationBackwardStack.push(node);
			return node.getLocation();
		}

		if (locationQueue.isEmpty()) {
			return null;
		}

		LocationNode node = (LocationNode) locationQueue.deQueue();
		locationBackwardStack.push(node);

		if (!locationQueue.isEmpty()) {
			LocationNode nextNode = (LocationNode) locationQueue.peek();
			locationForwardStack.push(nextNode);
		}

		return node.getLocation();
	}

	public Location previousLocation() {
		if (locationBackwardStack.isEmpty()) {
			return null;
		}

		LocationNode node = (LocationNode) locationBackwardStack.pop();
		locationForwardStack.push(node);

		if (!locationBackwardStack.isEmpty()) {
			return ((LocationNode) locationBackwardStack.peek()).getLocation();
		} else {
			return null;
		}
	}

	void initializeDateTraversal(LocationNode locNode) {
		if (locNode == null) {
			return;
		}
		dateForwardStack.clear();
		dateBackwardStack.clear();
		DateNode node = locNode.getHead().getRoot();

		pushDateLeft(node);
	}

	private void pushDateLeft(DateNode node) {
		while (node != null) {
			dateForwardStack.push(node);
			node = node.getLeft();
		}
	}

	public Date nextDate() {
		if (dateForwardStack.isEmpty()) {
			return null;
		}

		DateNode node = (DateNode) dateForwardStack.pop();
		dateBackwardStack.push(node);
		pushDateLeft(node.getRight());

		calculateDateStatistics(node);

		return node.getDate();
	}

	public Date previousDate() {
		if (dateBackwardStack.isEmpty()) {
			return null;
		}

		DateNode node = (DateNode) dateBackwardStack.pop();
		dateForwardStack.push(node);

		if (!dateBackwardStack.isEmpty()) {
			calculateDateStatistics((DateNode) dateBackwardStack.peek());
			return ((DateNode) dateBackwardStack.peek()).getDate();
		} else {
			return null;
		}
	}

	private StringBuilder calculateDateStatistics(DateNode dateNode) {
		LinkedList martyrsList = dateNode.getMartyrs();
		if (martyrsList == null || martyrsList.getFront() == null) {
			return new StringBuilder();
		}

		int totalAge = 0, count = 0, minAge = Integer.MAX_VALUE, maxAge = Integer.MIN_VALUE;
		MartyrNode youngestMartyr = null, oldestMartyr = null;
		MartyrNode current = martyrsList.getFront();
		while (current != null) {
			Martyr martyr = (Martyr) current.getElement();
			int age = martyr.getAge();
			if (age != -1) {
				totalAge += age;
				count++;
				if (age < minAge) {
					minAge = age;
					youngestMartyr = current;
				}
				if (age > maxAge) {
					maxAge = age;
					oldestMartyr = current;
				}
			}
			current = current.getNext();
		}

		double averageAge = count == 0 ? 0 : (double) totalAge / count;
		str = new StringBuilder();
		str.append("Average Age: " + averageAge);
		if (youngestMartyr != null) {
			str.append("\nYoungest Martyr: " + ((Martyr) youngestMartyr.getElement()).getName() + ", Age: " + minAge);
		}
		if (oldestMartyr != null) {
			str.append("\nOldest Martyr: " + ((Martyr) oldestMartyr.getElement()).getName() + ", Age: " + maxAge);
		}
		return str;
	}

	boolean updateLocationRecord(String districtName, String oldLocation, String newLocation) {
		if (districtName == null) {
			return false;
		}
		if (oldLocation == null) {
			return false;
		}
		if (oldLocation.equalsIgnoreCase(newLocation)) {
			return false;
		}
		DistrictNode current = districtTree.find(new District(districtName));
		if (current == null) {
			return false;
		}
		LocationNode locNode = current.getHead().find(new Location(oldLocation));
		if (locNode == null) {
			return false;
		}
		locNode.getLocation().setName(newLocation);
		return true;
	}

	private int calculateTotalMartyrs(LocationNode node) {
		if (node == null) {
			return 0;
		}

		int totalMartyrs = node.getHead().getTotalMartyrs(); // Total martyrs at this location
		totalMartyrs += calculateTotalMartyrs(node.getLeft());
		totalMartyrs += calculateTotalMartyrs(node.getRight());
		return totalMartyrs;
	}

	public int getTotalMartyrs(DistrictNode districtNode) {
		if (districtNode == null) {
			return 0;
		}

		LocationNode locationNode = districtNode.getHead().getRoot();
		return calculateTotalMartyrs(locationNode);
	}

	public StringBuilder getStr() {
		return str;
	}
}
