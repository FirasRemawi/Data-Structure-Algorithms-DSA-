package Project3;

public class AVLNode {
	private Martyr martyr;
	private AVLNode left;
	private AVLNode right;
	private int height;

	AVLNode(Martyr element) {
		this.martyr = element;
		this.left = null;
		this.right = null;
		this.height = 0;
	}

	public Martyr getMartyr() {
		return martyr;
	}

	public void setMartyr(Martyr martyr) {
		this.martyr = martyr;
	}

	public AVLNode getLeft() {
		return left;
	}

	public void setLeft(AVLNode left) {
		this.left = left;
	}

	public AVLNode getRight() {
		return right;
	}

	public void setRight(AVLNode right) {
		this.right = right;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "AVLTreeNode " + (martyr != null ? "element," + martyr + ", " : "")
				+ (left != null ? "left," + left + ", " : "") + (right != null ? "right," + right + ", " : "")
				+ "height," + height;
	}

}
