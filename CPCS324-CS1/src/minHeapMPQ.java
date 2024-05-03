
public class minHeapMPQ {

    Edge[] edges;
    int numberOfElements; // number of elements (edges)
    int currentElement; // store index of current element

    public minHeapMPQ(int numberOfEdges) {
        this.edges = new Edge[numberOfEdges];
        this.numberOfElements = 0;
        this.currentElement = 0;
    }

    public void insert(Edge edge) {
        if (numberOfElements == 0) {
            edges[0] = edge;
            numberOfElements++;
            currentElement++;
            return;
        }
        edges[currentElement] = edge;
        // check parent node
        int tempCurrent = currentElement;
        int parentIndex = (tempCurrent - 1) / 2;
        while (edges[parentIndex].getWeight() > edges[tempCurrent].getWeight()) {
            swap(parentIndex, tempCurrent);
            tempCurrent = parentIndex;
            parentIndex = (tempCurrent - 1) / 2;
            if (parentIndex < 0) {
                break;
            }

        }
        numberOfElements++;
        currentElement++;
    }

    public Edge delete() {
        Edge temp = edges[0]; // root 
        edges[0] = edges[currentElement - 1]; // root = last element
        heapify(0); // adjust tree
        numberOfElements--;
        currentElement--;
        return temp;

    }

    public void heapify(int index) {
        int smallest = index;
        int leftChildIndex = this.getLeftChild(index);
        int rightChildIndex = this.getRightChild(index);

        if (leftChildIndex < numberOfElements && edges[leftChildIndex].getWeight() < edges[index].getWeight()) {
            smallest = leftChildIndex;
        }
        if (rightChildIndex < numberOfElements && edges[rightChildIndex].getWeight() < edges[smallest].getWeight()) {
            smallest = rightChildIndex;
        }
        if (smallest != index) {
            swap(smallest, index);
            heapify(smallest);
        }
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private int getLeftChild(int index) {
        return 2 * index + 1;
    }

    private int getRightChild(int index) {
        return 2 * index + 2;
    }

    public void printQueue() {
        while (!isEmpty()) {
            System.out.println(delete().getWeight());
        }
    }

    private void swap(int a, int b) {
        Edge temp = edges[a];
        edges[a] = edges[b];
        edges[b] = temp;
    }

    public boolean isEmpty() {
        return numberOfElements == 0;
    }
}
