package Project3;

import java.util.List;

public class MaxHeap {
	private Martyr[] Heap;
	private int heapSize;
	private int maxsize;

	// Constructor to initialize the max heap
	public MaxHeap(int maxsize) {
		this.maxsize = maxsize;
		this.heapSize = 0;
		this.Heap = new Martyr[this.maxsize + 1]; // Heap[0] will not be used for easier child/parent calculation
	}

	// Helper functions to get parent and children indices
	private int parent(int pos) {
		return pos / 2;
	}

	private int leftChild(int pos) {
		return 2 * pos;
	}

	private int rightChild(int pos) {
		return 2 * pos + 1;
	}

	// Check if a node is a leaf
	private boolean isLeaf(int pos) {
		return pos >= (heapSize / 2) && pos <= heapSize;
	}

	// Swap two nodes of the heap
	private void swap(int fpos, int spos) {
		Martyr tmp = Heap[fpos];
		Heap[fpos] = Heap[spos];
		Heap[spos] = tmp;
	}

	// Function to heapify the node at pos
	private void maxHeapify(int pos) {
		if (!isLeaf(pos)) {
			if ((leftChild(pos) <= heapSize && Heap[pos].getAge() < Heap[leftChild(pos)].getAge())
					|| (rightChild(pos) <= heapSize && Heap[pos].getAge() < Heap[rightChild(pos)].getAge())) {

				if (rightChild(pos) > heapSize || Heap[leftChild(pos)].getAge() > Heap[rightChild(pos)].getAge()) {
					swap(pos, leftChild(pos));
					maxHeapify(leftChild(pos));
				} else {
					swap(pos, rightChild(pos));
					maxHeapify(rightChild(pos));
				}
			}
		}
	}

	public void populateHeap(List<Martyr> martyrs) {
		for (Martyr martyr : martyrs) {
			insert(martyr);
		}
		buildMaxHeap();
	}

	// Insert a new element into the heap
	public void insert(Martyr element) {
		if (heapSize == maxsize) {
			return; // Optionally handle the resizing of the heap here
		}
		Heap[++heapSize] = element;
		int current = heapSize;

		while (current > 1 && Heap[current].getAge() > Heap[parent(current)].getAge()) {
			swap(current, parent(current));
			current = parent(current);
		}
	}

	// Build the heap by maxHeapifying all the elements from the bottom to the top
	public void buildMaxHeap() {
		for (int pos = (heapSize / 2); pos >= 1; pos--) {
			maxHeapify(pos);
		}
	}

	// Remove and return the maximum element from the heap
	public Martyr extractMax() {
		if (heapSize == 0) {
			return null; // Or throw an exception as appropriate
		}
		Martyr popped = Heap[1];
		Heap[1] = Heap[heapSize--];
		maxHeapify(1);
		return popped;
	}

	public boolean isEmpty() {
		return heapSize == 0;
	}
}
