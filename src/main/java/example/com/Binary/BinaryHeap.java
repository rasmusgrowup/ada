package example.com.Binary;

// BinaryHeap class
//
// CONSTRUCTION: with optional capacity (that defaults to 100)
//               or an array containing initial items
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// Comparable deleteMin( )--> Return and remove smallest item
// Comparable findMin( )  --> Return smallest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements a binary heap.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinaryHeap<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the binary heap.
     */
    public BinaryHeap( )
    {
        this( DEFAULT_CAPACITY );
    }

    /**
     * Construct the binary heap.
     * @param capacity the capacity of the binary heap.
     */
    public BinaryHeap( int capacity )
    {
        currentSize = 0;
        array = (AnyType[]) new Comparable[ capacity + 1 ];
    }

    /**
     * Construct the binary heap given an array of items.
     */
    public BinaryHeap( AnyType [ ] items )
    {
        currentSize = items.length;
        array = (AnyType[]) new Comparable[ ( currentSize + 2 ) * 11 / 10 ];

        int i = 1;
        for( AnyType item : items )
            array[ i++ ] = item;
        buildHeap( );
    }

    /**
     * Insert into the priority queue, maintaining heap order.
     * Duplicates are allowed.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        if( currentSize == array.length - 1 )
            enlargeArray( array.length * 2 + 1 );

        // Percolate up
        int hole = ++currentSize;
        for( array[ 0 ] = x; x.compareTo( array[ hole / 2 ] ) < 0; hole /= 2 )
            array[ hole ] = array[ hole / 2 ];
        array[ hole ] = x;
    }


    private void enlargeArray( int newSize )
    {
        AnyType [] old = array;
        array = (AnyType []) new Comparable[ newSize ];
        for( int i = 0; i < old.length; i++ )
            array[ i ] = old[ i ];
    }

    /**
     * Find the smallest item in the priority queue.
     * @return the smallest item, or throw an UnderflowException if empty.
     */
    public AnyType findMin( ){
        if( isEmpty( ) )
            System.out.println("UnderflowException");
        return array[ 1 ];
    }

    /**
     * Find the largest item in the priority queue.
     * @return the largest item, or throw an UnderflowException if empty.
     */
    public AnyType findMax() {
        if (isEmpty())
            throw new RuntimeException("UnderflowException");
        return array[1]; // Root contains the maximum element in a max-heap
    }

    /**
     * Remove the smallest item from the priority queue.
     * @return the smallest item, or throw an UnderflowException if empty.
     */
    public AnyType deleteMin( ) {
        if( isEmpty( ) )
            System.out.println("UnderflowException");

        AnyType minItem = findMin( );
        array[ 1 ] = array[ currentSize-- ];
        percolateDown( 1 );

        return minItem;
    }

    /**
     * Remove the largest item from the priority queue.
     * @return the largest item, or throw an UnderflowException if empty.
     */
    public AnyType deleteMax() {
        if (isEmpty())
            throw new RuntimeException("UnderflowException");

        AnyType maxItem = findMax(); // Retrieve the maximum element (root)
        array[1] = array[currentSize--]; // Replace root with the last element
        percolateDown(1); // Restore the heap order property from the root down

        return maxItem; // Return the removed maximum element
    }

    /**
     * Establish heap order property from an arbitrary
     * arrangement of items. Runs in linear time.
     */
    private void buildHeap( )
    {
        for( int i = currentSize / 2; i > 0; i-- )
            percolateDown( i );
    }

    /**
     * Test if the priority queue is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return currentSize == 0;
    }

    /**
     * Make the priority queue logically empty.
     */
    public void makeEmpty( )
    {
        currentSize = 0;
    }

    private static final int DEFAULT_CAPACITY = 10;

    public int getCurrentSize() {
        return currentSize;
    }

    private int currentSize;      // Number of elements in heap
    private AnyType [ ] array; // The heap array

    /**
     * Internal method to percolate down in the heap.
     * @param hole the index at which percolate begins.
     */
    private void percolateDown( int hole )
    {
        int child;
        AnyType tmp = array[ hole ];

        for( ; hole * 2 <= currentSize; hole = child )
        {
            child = hole * 2;
            if( child != currentSize &&
                    array[ child + 1 ].compareTo( array[ child ] ) < 0 )
                child++;
            if( array[ child ].compareTo( tmp ) < 0 )
                array[ hole ] = array[ child ];
            else
                break;
        }
        array[ hole ] = tmp;
    }

    /**
     * Internal method to percolate up in the heap.
     * @param hole the index at which percolate begins.
     */
    private void percolateUp(int hole) {
        AnyType tmp = array[hole]; // Save the inserted element
        // Traverse upwards as long as the parent's value is greater
        for (; hole > 1 && tmp.compareTo(array[hole / 2]) < 0; hole /= 2) {
            array[hole] = array[hole / 2]; // Move parent down
        }
        array[hole] = tmp; // Place the element in its correct position
    }

    public static void main( String [ ] args )
    {
        /*
        int numItems = 10000;
        BinaryHeap<Integer> h = new BinaryHeap<>( );
        int i = 37;

        for( i = 37; i != 0; i = ( i + 37 ) % numItems )
            h.insert( i );
        for( i = 1; i < numItems; i++ )
            if( h.deleteMin( ) != i )
                System.out.println( "Oops! " + i );

         */

        // Initialize an empty BinaryHeap
        BinaryHeap<Integer> heap = new BinaryHeap<>(10);

        // Manually populate the array
        heap.insert(10); // Root
        heap.insert(20); // Left child
        heap.insert(30); // Right child
        heap.insert(40); // Left child of node 2
        heap.insert(50); // Right child of node 2
        //heap.currentSize = 5; // Set current size to the number of elements

        // Call buildHeap to enforce heap property
        heap.buildHeap();

        // Test percolateUp
        heap.insert(25);
        heap.buildHeap();
    }
}

