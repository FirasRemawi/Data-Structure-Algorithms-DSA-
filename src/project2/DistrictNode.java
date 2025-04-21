package project2;

import java.io.PrintWriter;

public class DistrictNode implements BSTNode {
	private District element;
	DistrictNode left;
	DistrictNode right;
	private LocationTree head;

	public DistrictNode(District districtName) {
		this.element = districtName;
		this.left = null;
		this.right = null;
		this.head = new LocationTree();
	}

	public District getDistrictName() {
		return element;
	}

	public void setDistrictName(District districtName) {
		this.element = districtName;
	}

	public DistrictNode getLeft() {
		return left;
	}

	public DistrictNode getRight() {
		return right;
	}

	@Override
	public void setLeft(BSTNode node) {
		if (node instanceof DistrictNode || node == null) {
			this.left = (DistrictNode) node;
		} else {
			throw new IllegalArgumentException("Left child must be a DistrictNode or null");
		}
	}

	@Override
	public void setRight(BSTNode node) {
		if (node instanceof DistrictNode || node == null) {
			this.right = (DistrictNode) node;
		} else {
			throw new IllegalArgumentException("Right child must be a DistrictNode or null");
		}
	}

	@Override
	public String toString() {
		return "District Name: " + this.getDistrictName();
	}

	public void setHead(LocationTree locationTree) {

	}

	public District getElement() {
		return element;
	}

	public void setElement(District element) {
		this.element = element;
	}

	public LocationTree getHead() {
		return head;
	}

	public void writeOnFile(String filename) {
		try (PrintWriter writer = new PrintWriter(filename)) {
			writer.println("Name,eventDate,Age,location,District,Gender");
			writeDistrictNode(this, writer);
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	private void writeDistrictNode(DistrictNode node, PrintWriter writer) {
		if (node != null) {
			writeLocationTree(node.getHead().getRoot(), writer, node.getDistrictName().getName());
			writeDistrictNode(node.getLeft(), writer);
			writeDistrictNode(node.getRight(), writer);
		}
	}

	private void writeLocationTree(LocationNode node, PrintWriter writer, String districtName) {
		if (node != null) {
			writeDateTree(node.getHead().getRoot(), writer, node.getLocation().getName(), districtName);
			writeLocationTree(node.left, writer, districtName);
			writeLocationTree(node.right, writer, districtName);
		}
	}

	private void writeDateTree(DateNode node, PrintWriter writer, String locationName, String districtName) {
		if (node != null) {
			writeMartyrList(node.getMartyrs(), writer, locationName, districtName);
			writeDateTree(node.getLeft(), writer, locationName, districtName);
			writeDateTree(node.getRight(), writer, locationName, districtName);
		}
	}

	private void writeMartyrList(LinkedList martyrList, PrintWriter writer, String locationName, String districtName) {
		MartyrNode current = martyrList.getFront();
		while (current != null) {
			Martyr martyr = (Martyr) current.getElement();
			writer.printf("%s,%s,%d,%s,%s,%c%n", martyr.getName(), martyr.getDate(), martyr.getAge(), locationName,
					districtName, martyr.getGender());
			current = current.getNext();
		}
	}
}
