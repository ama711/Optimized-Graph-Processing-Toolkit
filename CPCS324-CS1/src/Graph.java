
import java.util.ArrayList;

public class Graph {

    private Vertex[] vertices;
    private int currentNumberOfVertices;
    private int totalVertices;
    private int numberOfEdges;

    public Graph() {
    }

    public Graph(int numberOfVertices, int numberOfEdges) {
        this.vertices = new Vertex[numberOfVertices];
        this.currentNumberOfVertices = 0;
        this.totalVertices = numberOfVertices;
        this.numberOfEdges = numberOfEdges;
    }

    public Vertex[] getVertices() {
        return vertices;
    }

    public Edge[] getEdges() {
        Edge[] edges = new Edge[numberOfEdges];
        int i = 0;
        for (Vertex v : vertices) {
            ArrayList<Edge> neighbors = v.getNeighbors();
            for (Edge e : neighbors) {
                edges[i] = e;
                i++;
            }

        }
        return edges;
    }

    public void addVertices(Vertex vertex) {
        if (currentNumberOfVertices != totalVertices) {
            this.vertices[currentNumberOfVertices] = vertex;
            currentNumberOfVertices++;
        }
    }

    public void printGraph() {
        for (Vertex i : vertices) {
            System.out.print("Node " + i.getValue() + ": ");
            i.getNeighbors().forEach((j) -> {
                System.out.print(j.getTarget().getValue() + ", " + j.getWeight() + "  ");
            });
            System.out.println();
        }
    }

    public int getNumberOfEdges() {
        return numberOfEdges;
    }

    public int getTotalVertices() {
        return totalVertices;
    }

}

class Vertex {

    private int value;
    private boolean visited;
    private ArrayList<Edge> neighbors;
    private int shortestDistance; // shortest path found by the algorithm so far (Dijkstra)
    private Vertex previous;

    public Vertex(int value, int neighbors) {
        this.value = value;
        this.visited = false;
        this.neighbors = new ArrayList<>();
        this.shortestDistance = Integer.MAX_VALUE;

    }

    public Vertex(int value) {
        this.value = value;
    }

    public void addNeighbor(Edge edge) {
        // add an edge to the neighbor
        this.neighbors.add(edge);
    }

    public ArrayList<Edge> getNeighbors() {
        return this.neighbors;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getShortestDistance() {
        return shortestDistance;
    }

    public void setShortestDistance(int shortestDistance) {
        this.shortestDistance = shortestDistance;
    }

    public Vertex getPrevious() {
        return previous;
    }

    public void setPrevious(Vertex previous) {
        this.previous = previous;
    }
}

class Edge {

    private Vertex source;
    private Vertex target;
    private int weight;

    public Edge(Vertex source, Vertex target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    public Edge(int weight) {
        this.weight = weight;
    }

    public Vertex getSource() {
        return this.source;
    }

    public void setSource(Vertex source) {
        this.source = source;
    }

    public Vertex getTarget() {
        return target;
    }

    public void setTarget(Vertex target) {
        this.target = target;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}
