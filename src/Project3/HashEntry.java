package Project3;

public class HashEntry {
	private String date;
	private AVL avlTree;
	private char status;// 'E': empty, 'F': full, 'D': deleted

	public HashEntry(String date, AVL avlTree, char status) {
		this.date = date;
		this.avlTree = avlTree != null ? avlTree : new AVL(); // Ensure the AVL tree is never null
		this.status = status;
	}

	// Getters and setters...
	public AVL getAvlTree() {
		return avlTree;
	}

	public void setAvlTree(AVL avlTree) {
		this.avlTree = avlTree;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "HashEntry " + (date != null ? "date," + date + ", " : "")
				+ (avlTree != null ? "avlTree," + avlTree + ", " : "") + "status," + status;
	}

}
