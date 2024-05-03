
import java.util.*;

public class Prim {

    public static int[][] storeEdges(Scanner input, int numberOfVertices) {

        int[][] matrix = new int[numberOfVertices][numberOfVertices]; // initialize and create adjacenct matrix
        // make objects of unsorted list and min heap priority queues

        // start storing into adjacent matrix
        while (input.hasNext()) {
            int i = input.nextInt(); // source vertex 
            int j = input.nextInt(); // target vertex 
            
            int x = input.nextInt();
            matrix[j][i] = x; // edge weight
            matrix[i][j] = x; // edge weight
            
        }

        return matrix;
    }

    public static Graph constructGraph(int[][] matrix, int numVertices, int numEdges) {
        Graph graph = new Graph(numVertices, numEdges);

        // Add vertices to graph
        for (int vertexId = 0; vertexId < numVertices; vertexId++) {
            int numNeighbors = 0;
            for (int weight : matrix[vertexId]) {
                if (weight != 0) {
                    numNeighbors++;
                }
            }
            graph.addVertices(new Vertex(vertexId, numNeighbors));
        }

        // Add edges to vertices
        Vertex[] vertices = graph.getVertices();
        for (int srcId = 0; srcId < numVertices; srcId++) {
            Vertex srcVertex = vertices[srcId];
            for (int tgtId = 0; tgtId < numVertices; tgtId++) {
                int weight = matrix[srcId][tgtId];
                if (weight != 0) {
                    Vertex tgtVertex = vertices[tgtId];
                    Edge edge = new Edge(srcVertex, tgtVertex, weight);
                    srcVertex.addNeighbor(edge);
                }
            }
        }

        return graph;
    }

    public static List<Edge> primMST(Graph graph) {
        List<Edge> mst = new ArrayList<>();

        // Initialize the heap with all edges connected to the first vertex
        minHeapMPQ heap = new minHeapMPQ(graph.getNumberOfEdges());
        Vertex startVertex = graph.getVertices()[0];
        for (Edge edge : startVertex.getNeighbors()) {
            heap.insert(edge);
        }

        // Initialize a set to keep track of visited vertices
        Set<Vertex> visited = new HashSet<>();
        visited.add(startVertex);

        // Main loop: while there are still edges in the heap
        while (!heap.isEmpty()) {
            // Extract the minimum-weight edge from the heap
            Edge currEdge = heap.delete();
            Vertex currVertex = currEdge.getTarget();

            // If the target vertex has not been visited, add the edge to the MST and mark the vertex as visited
            if (!visited.contains(currVertex)) {
                mst.add(currEdge);
                visited.add(currVertex);

                // Add all edges connected to the new vertex to the heap
                for (Edge edge : currVertex.getNeighbors()) {
                    if (!visited.contains(edge.getTarget())) {
                        heap.insert(edge);
                    }
                }
            }
        }

        return mst;
    }

    public static List<Edge> primMSTT(Graph graph) {
        List<Edge> mst = new ArrayList<>();

        // Initialize the queue with all edges connected to the first vertex
        unsortedArrayMPQ queue = new unsortedArrayMPQ(graph.getNumberOfEdges());
        Vertex startVertex = graph.getVertices()[0];
        for (Edge edge : startVertex.getNeighbors()) {
            queue.enqueue(edge);
        }

        // Initialize a set to keep track of visited vertices
        Set<Vertex> visited = new HashSet<>();
        visited.add(startVertex);

        // Main loop: while there are still edges in the queue
        while (!queue.isEmpty()) {
            // Extract the minimum-weight edge from the queue
            Edge currEdge = queue.dequeue();
            Vertex currVertex = currEdge.getTarget();

            // If the target vertex has not been visited, add the edge to the MST and mark the vertex as visited
            if (!visited.contains(currVertex)) {
                mst.add(currEdge);
                visited.add(currVertex);

                // Add all edges connected to the new vertex to the queue
                for (Edge edge : currVertex.getNeighbors()) {
                    if (!visited.contains(edge.getTarget())) {
                        queue.enqueue(edge);
                    }
                }
            }
        }

        return mst;
    }

}
