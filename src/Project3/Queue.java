package Project3;

public class Queue {

	private Node front, rear;
	private int size;

	// Constructor for initializing the queue
	public Queue() {
		front = rear = null;
		size = 0; // Initialize size to 0

	}

	public void enQueue(Object element) {
		Node newNode = new Node(element);
		if (rear == null) {
			// If the queue is empty, both front and rear will point to the new node
			front = rear = newNode;
		} else {
			// Add the new node at the end of the queue and update rear
			rear.next = newNode;
			rear = newNode;
		}
		size++;
	}

	public Object deQueue() {
		if (isEmpty()) {
			System.out.println("Queue is empty");
			return null;
		}
		Object element = front.element;
		front = front.next;
		if (front == null) {
			rear = null;
		}
		size--;
		return element;
	}

	// Method to check if the queue is empty
	public boolean isEmpty() {
		return front == null;
	}

	public boolean isFull() {
		return false;
	} // Added method to get the size of the queue

	public int getSize() {
		return size;
	}
}
