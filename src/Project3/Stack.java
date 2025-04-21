package Project3;

public class Stack {
	private int size; // number of elements in the stack
	private Node Front; // pointer to the top node

	public Stack() {
		// empty stack
		Front = null;
		size = 0;
	}

	public void push(Object element) {

		Node newNode;
		newNode = new Node(element);

		newNode.next = Front;
		Front = newNode;

		size++;// update size
	}

	public void sortedPush(District newDistrict) {
		Stack temp = new Stack();

		while (!isEmpty() && ((District) peek()).getName().compareToIgnoreCase(newDistrict.getName()) < 0) {
			temp.push(pop());
		}

		// Insert the new district in the correct position
		push(newDistrict);

		// Move the elements back from the temporary stack to the main stack
		while (!temp.isEmpty()) {
			push(temp.pop());
		}
	}

	public District find(String name) {
		Stack temp = new Stack();
		District district = null;

		while (!isEmpty()) {
			Object item = pop();
			if (item instanceof District) {
				District current = (District) item;
				if (current.getName().equalsIgnoreCase(name)) {
					district = current;
				}
				temp.push(current);
			}
		}

		// Restore the stack
		restore(temp);
		return district;
	}

	public Location findLocation(String name) {
		Stack temp = new Stack();
		Location location = null;

		while (!isEmpty()) {
			Object item = pop();
			if (item instanceof Location) {
				Location current = (Location) item;
				if (current.getName().equalsIgnoreCase(name)) {
					location = current;
				}
				temp.push(current);
			}
		}

		// Restore the stack
		restore(temp);
		return location;
	}

	private void restore(Stack temp) {
		while (!temp.isEmpty()) {
			push(temp.pop());
		}
	}

	public Object pop() {

		if (!isEmpty()) {
			Node top = Front; // save reference
			Front = Front.next;// Remove first node
			size--;
			return top.getElement();// Return the element from the saved ref
		} else
			return null;

	}

	public Object peek() {
		// Return the top element without changing the stack
		if (!isEmpty())
			return Front.element;
		else
			return null;
	}

	public int Size() {
		return size;
	}

	public boolean isEmpty() {
		return (Front == null); // return true if the stack is empty
	}

	public District max(Stack s) {
		Stack restore = new Stack();
		District maxDistrict = (District) s.pop();
		restore.push(maxDistrict);

		while (!s.isEmpty()) {
			District nextDistrict = (District) s.pop();
			if (nextDistrict.getNumberOfMartyrs() > maxDistrict.getNumberOfMartyrs()) {
				maxDistrict = nextDistrict;
			}
			restore.push(nextDistrict);
		}

		while (!restore.isEmpty()) {
			s.push(restore.pop());
		}

		return maxDistrict;
	}

	public int min(Stack s) {
		Stack restore = new Stack();
		int minvValue = (int) s.pop();
		restore.push(minvValue);
		while (!s.isEmpty()) {
			int next = (int) s.pop();
			minvValue = Math.min(minvValue, next);
			restore.push(next);
		}
		while (!restore.isEmpty()) {
			s.push(restore.pop());
		}
		return minvValue;
	}

	public void printList() {
		Stack tempStack = new Stack();
		while (!this.isEmpty()) {
			Object obj = pop();
			System.out.println(obj);
			tempStack.push(obj);
		}

		while (!tempStack.isEmpty()) {
			push(tempStack.pop());
		}

	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Node getFront() {
		return Front;
	}

	public void setFront(Node front) {
		Front = front;
	}

	@Override
	public String toString() {
		return (Front != null ? "Front," + Front : "");
	}

	public void clear() {
		while (!this.isEmpty())
			this.pop();

	}

}
