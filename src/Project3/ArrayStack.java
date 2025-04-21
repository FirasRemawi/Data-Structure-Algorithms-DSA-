package Project3;

public class ArrayStack {
	private int maxSize;
	private Object[] stackArray;// Holds the elements
	private int top;

	public ArrayStack(int maxSize) {

		this.maxSize = maxSize;
		stackArray = new Object[maxSize];
		top = -1; // Empty stack
	}

	public void push(Object element) {
		// Adds a new element to the top of the stack
		stackArray[++top] = element;
	}

	public Object pop() {
		// Removes and returns the stack's top element
		if (!isEmpty())
			return stackArray[top--];
		return null; // Empty stack
	}

	public Object peek() {
		// Return the top element without changing the stack
		if (!isEmpty())
			return stackArray[top];
		return null; // Empty stack
	}

	public boolean isEmpty() {
		return (top == -1); // Returns true if stack is empty
	}

	public boolean isFull() {
		return (top == maxSize - 1); // Returns true if stack is full
	}

	public int size() {
		return top + 1; // returns number of elements inside stack
	}
	
}
