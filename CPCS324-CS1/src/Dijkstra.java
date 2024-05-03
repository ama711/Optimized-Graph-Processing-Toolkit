
import java.util.*;

public class Dijkstra {

    public static int[][] storeEdges(Scanner input, int numberOfVertices) {

        int[][] matrix = new int[numberOfVertices][numberOfVertices]; // initialize and create adjacenct matrix
        // make objects of unsorted list and min heap priority queues

        // start storing into adjacent matrix
        while (input.hasNext()) {
            int i = input.nextInt(); // source vertex 
            int j = input.nextInt(); // target vertex 
            matrix[i][j] = input.nextInt(); // edge weight
        }

        return matrix;
    }

    public static Graph constructGraph(int[][] matrix, int numberOfVertices, int numberOfEdges) {
        Graph graph = new Graph(numberOfVertices, numberOfEdges);

        for (int i = 0; i < matrix.length; i++) {
            int numberOfNeighbors = 0;
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] != 0) {
                    numberOfNeighbors++;
                }
            }
            graph.addVertices(new Vertex(i, numberOfNeighbors)); // add new node to graph
        }
        // right now there are nodes/vertices without edges
        // add edges to vertices
        Vertex[] vertices = graph.getVertices(); // list of all vertices of the graph 
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] != 0) {	// add new edge with target being vertices[j] and weight is matrix[i][j]
                    vertices[i].addNeighbor(new Edge(vertices[i], vertices[j], matrix[i][j]));

                }
            }
        }
        return graph;

    }

    public static void printMatrix(int[][] matrix, int numberOfVertices, int numberOfEdges) {
        // print first row 
        System.out.print("Weight Matrix:\n  0");
        for (int i = 1; i < matrix.length; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        System.out.print("\nNumber of vertices is " + numberOfVertices + "\n");
        System.out.print("Number of edges is " + numberOfEdges + "\n\n");

        for (int i = 0; i < matrix.length; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] != 0) {
                    System.out.print(i + "-" + j + " " + matrix[i][j] + "  ");
                }
            }
            System.out.println();
        }

    }

    public static double DijkstraUnsortedArray(int sourceVertex, int numberOfEdges, Graph graph) {
         

        Vertex[] vertices = graph.getVertices().clone();
        unsortedArrayMPQ queue = new unsortedArrayMPQ(numberOfEdges);
        ArrayList<Vertex> visited = new ArrayList<Vertex>();
        // get source vertex
        Vertex source = null;
        for (Vertex v : vertices) {
            if (v.getValue() == sourceVertex) {
                source = v;
                visited.add(v);
                v.setShortestDistance(0); // set shortest path of source to 0
                // source.previous is already null
                v.getNeighbors().forEach((e) -> {
                    queue.enqueue(e);
                }); // enqueue all neighbors of source vertex
            }
        }

        while (!queue.isEmpty()) {
            Edge e = queue.dequeue();
            Vertex previous = e.getSource(); // source of the edge
            Vertex target = e.getTarget(); // target of the edge
            if (!visited.contains(target)) { // if the target is not yet visited
                int shortestDistance = previous.getShortestDistance() + e.getWeight();
                // set shortest distance and previous of target 
                target.setShortestDistance(shortestDistance);
                target.setPrevious(previous);
                visited.add(target);
                target.getNeighbors().forEach((neighbor) -> {
                    queue.enqueue(neighbor);
                });
            } else { // if the target is alreay visited
                if (target.getShortestDistance() > e.getWeight() + previous.getShortestDistance()) {
                    target.setShortestDistance(e.getWeight() + previous.getShortestDistance());
                    target.setPrevious(previous);
                } // if the current shortest distance is less, dont change anything
            }
        }
         

        // print out everything
        System.out.println("\nDijkstra's Algorithm using unsorted array as min PQ");
        System.out.println("Shortest path from vertex " + source.getValue() + " are: ");
        for (Vertex v : vertices) {
            System.out.println("A path from " + source.getValue() + " to " + v.getValue() + ": " + getPath(v) + "(Length " + (double) v.getShortestDistance() + ")");
        }

        return  0;
    }

    public static double DijkstraMinHeap(int sourceVertex, int numberOfEdges, Graph graph) {
         

        Vertex[] vertices = graph.getVertices().clone();
        minHeapMPQ queue = new minHeapMPQ(numberOfEdges);
        ArrayList<Vertex> visited = new ArrayList<Vertex>();
        // get source vertex
        Vertex source = null;
        for (Vertex v : vertices) {
            if (v.getValue() == sourceVertex) {
                source = v;
                visited.add(v);
                v.setShortestDistance(0); // set shortest path of source to 0
                // source.previous is already null
                v.getNeighbors().forEach((e) -> {
                    queue.insert(e);
                }); // enqueue all neighbors of source vertex
            }
        }

        while (!queue.isEmpty()) {
            Edge e = queue.delete();
            Vertex previous = e.getSource(); // source of the edge
            Vertex target = e.getTarget(); // target of the edge
            if (!visited.contains(target)) { // if the target is not yet visited
                int shortestDistance = previous.getShortestDistance() + e.getWeight();
                // set shortest distance and previous of target 
                target.setShortestDistance(shortestDistance);
                target.setPrevious(previous);
                visited.add(target);
                target.getNeighbors().forEach((neighbor) -> {
                    queue.insert(neighbor);
                });
            } else { // if the target is already visited
                if (target.getShortestDistance() > e.getWeight() + previous.getShortestDistance()) {
                    target.setShortestDistance(e.getWeight() + previous.getShortestDistance());
                    target.setPrevious(previous);
                } // if the current shortest distance is less, dont change anything
            }
        }

        

        // print out everything
        System.out.println("\nDijkstra's Algorithm using min-heap as min PQ");
        System.out.println("Shortest path from vertex " + source.getValue() + " are: ");
        for (Vertex v : vertices) {
            System.out.println("A path from " + source.getValue() + " to " + v.getValue() + ": " + getPath(v) + "(Length " + (double) v.getShortestDistance() + ")");
        }
        return 0;
    }

    public static String getPath(Vertex v) {
        if (v == null) {
            return "";
        } else {
            return getPath(v.getPrevious()) + v.getValue() + " ";
        }
    }
}
