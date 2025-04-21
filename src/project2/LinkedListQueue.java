package project2;

public class LinkedListQueue {

    private Node front, rear;
    private int size;  // To keep track of the number of elements in the queue

    // Node inner class for the LinkedList
    private static class Node {
        Object element;
        Node next;

        public Node(Object element) {
            this.element = element;
            this.next = null;
        }
    }

    // Constructor for initializing the queue
    public LinkedListQueue() {
        front = rear = null;
        size = 0;
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

    public Object peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }
        return front.element;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }

    // Additional method to clear the queue
    public void clear() {
        while (!isEmpty()) {
            deQueue();
        }
    }
}
