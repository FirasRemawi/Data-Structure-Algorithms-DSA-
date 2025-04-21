package project2;

public class LinkedListStack {
	private int size; // number of elements in the stack
	private Node Front; // pointer to the top node

	public LinkedListStack() {
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

	public int max(LinkedListStack s) {
		LinkedListStack restore = new LinkedListStack();
		int maxValue = (int) s.pop();
		restore.push(maxValue);
		while (!s.isEmpty()) {
			int next = (int) s.pop();
			maxValue = Math.max(maxValue, next);
			restore.push(next);
		}
		while (!restore.isEmpty()) {
			s.push(restore.pop());
		}
		return maxValue;
	}

	public int min(LinkedListStack s) {
		LinkedListStack restore = new LinkedListStack();
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
		LinkedListStack tempStack = new LinkedListStack();
		while (!this.isEmpty()) {
			Object obj = pop();
			System.out.println(obj);
			tempStack.push(obj);
		}

		while (!tempStack.isEmpty()) {
			push(tempStack.pop());
		}

	}

	public static boolean isBalanced(String expression) {
		LinkedListStack stack = new LinkedListStack();

		for (char ch : expression.toCharArray()) {
			// Push any open brackets onto the stack
			if (ch == '(' || ch == '{' || ch == '[') {
				stack.push(ch);
			} else {
				// Check for closing brackets
				if (stack.isEmpty()) {
					// If there are no open brackets to match, it's unbalanced
					return false;
				} else {
					char top = (char) stack.pop(); // Cast needed since pop returns Object
					if (!matches(top, ch)) {
						// If the brackets do not match, it's unbalanced
						return false;
					}
				}
			}
		}

		// The stack should be empty if all opening brackets had matching closing
		// brackets
		return stack.isEmpty();
	}

	private static boolean matches(char open, char close) {
		return (open == '(' && close == ')') || (open == '{' && close == '}') || (open == '[' && close == ']');
	}

	public void clear() {
		Front = null;

	}
}
