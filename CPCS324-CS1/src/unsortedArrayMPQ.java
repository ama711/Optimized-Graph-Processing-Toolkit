
public class unsortedArrayMPQ {

    /*
	  Use an unsorted array to store the weights of 
	  the edges.
     */
    private Edge[] edges;
    private int currentElement; // store the index of the last element
    private int numberOfElements; // store the number of elements (edges)

    public unsortedArrayMPQ(int numberOfEdges) {
        this.edges = new Edge[numberOfEdges];
        this.currentElement = -1;
        this.numberOfElements = 0;
    }

    public void enqueue(Edge edge) {
        // since its an unsorted array, insert at the last index
        edges[++currentElement] = edge;
//		currentElement++; // increment index
        numberOfElements++;
    }

    public Edge dequeue() {
        // since the array is unsorted, search for minimum edge
        int minEdgeIndex = -1; // used to swap minimum edge with most recently added edge
        int maxEdgeWeight = Integer.MAX_VALUE; // used to compare edge weight
        for (int i = 0; i <= currentElement; i++) {
            if (edges[i].getWeight() < maxEdgeWeight) {
                minEdgeIndex = i;
                maxEdgeWeight = edges[i].getWeight();
            }
        }
        // after finding minimum edge's index, return it
        // to return it, minimum edge must be swapped with most recently added edge
        // i.e this.currentElement
        swapElements(minEdgeIndex);
        currentElement--; // decrement current element index 
        numberOfElements--; // decrement the number of elements
        return edges[currentElement + 1]; // since the minimum edge was swapped to the last index, add one
    }

    public void swapElements(int minEdgeIndex) {
        Edge temp = edges[minEdgeIndex];
        edges[minEdgeIndex] = edges[currentElement];
        edges[currentElement] = temp;
    }

    public boolean isEmpty() {
        return numberOfElements == 0;
    }

    public void printQueue() {
        while (!isEmpty()) {
            System.out.println(dequeue().getWeight());
        }
    }

    public void decreasePriority(Edge source, int weight) {
        return;
    }
}
