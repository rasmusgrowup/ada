package example.com.Binary;

import java.util.Arrays;

public class BinaryHeapGeeks {

    // Array representation of the heap
    private int[] heap;

    // Number of elements currently in the heap
    private int size;

    // Maximum number of elements the heap can hold
    private int capacity;

    // Flag to determine whether the heap is a min-heap or max-heap
    private boolean isMinHeap;

    // Constructor to initialize the heap with a given capacity and type
    public BinaryHeapGeeks(int capacity, boolean isMinHeap) {
        this.capacity = capacity;
        this.heap = new int[capacity];
        this.size = 0;
        this.isMinHeap = isMinHeap;
    }

    // Method to get the index of the parent of a given node
    private int parent(int i) {
        return (i - 1) / 2;
    }

    // Method to get the index of the left child of a given node
    private int leftChild(int i) {
        return 2 * i + 1;
    }

    // Method to get the index of the right child of a given node
    private int rightChild(int i) {
        return 2 * i + 2;
    }

    // Method to swap two elements in the heap array
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Method to resize the heap array when capacity is reached
    private void resize() {
        capacity *= 2;
        heap = Arrays.copyOf(heap, capacity);
    }

    // Method to compare two elements based on heap type
    private boolean compare(int a, int b) {
        return isMinHeap ? a < b : a > b;
    }

    // Method to insert a new value into the heap
    public void insert(int value) {
        if (size >= capacity) {
            resize();
        }

        heap[size] = value;
        int current = size;
        size++;

        // Restore the heap property by "heapifying up"
        while (current > 0 && compare(heap[current], heap[parent(current)])) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    // Method to restore the heap property by "heapifying down" from a given index
    private void heapifyDown(int i) {
        int extreme = i;
        int left = leftChild(i);
        int right = rightChild(i);

        if (left < size && compare(heap[left], heap[extreme])) {
            extreme = left;
        }

        if (right < size && compare(heap[right], heap[extreme])) {
            extreme = right;
        }

        if (extreme != i) {
            swap(i, extreme);
            heapifyDown(extreme);
        }
    }

    // Method to extract the root element (minimum or maximum) from the heap
    public int extractRoot() {
        if (size == 0) {
            throw new RuntimeException("Heap is empty!");
        }

        int root = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);

        return root;
    }

    // Method to print the current state of the heap
    public void printHeap() {
        System.out.println(Arrays.toString(Arrays.copyOf(heap, size)));
    }

    // Main method to demonstrate the usage of the BinaryHeap class
    public static void main(String[] args) {
        // Create a min-heap with an initial capacity of 10
        BinaryHeapGeeks minHeap = new BinaryHeapGeeks(10, true);

        // Insert elements into the min-heap
        minHeap.insert(10);
        minHeap.insert(5);
        minHeap.insert(30);
        minHeap.insert(3);
        minHeap.insert(8);

        // Print the min-heap after inserting elements
        System.out.println("Min-Heap after inserting elements:");
        minHeap.printHeap();

        // Extract the root element and print it
        System.out.println("Extracted root (min): " + minHeap.extractRoot());
        System.out.println("Min-Heap after extracting the root:");
        minHeap.printHeap();

        // Create a max-heap with an initial capacity of 10
        BinaryHeapGeeks maxHeap = new BinaryHeapGeeks(10, false);

        // Insert elements into the max-heap
        maxHeap.insert(10);
        maxHeap.insert(5);
        maxHeap.insert(30);
        maxHeap.insert(3);
        maxHeap.insert(8);

        // Print the max-heap after inserting elements
        System.out.println("Max-Heap after inserting elements:");
        maxHeap.printHeap();

        // Extract the root element and print it
        System.out.println("Extracted root (max): " + maxHeap.extractRoot());
        System.out.println("Max-Heap after extracting the root:");
        maxHeap.printHeap();
    }
}
