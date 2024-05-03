
//package com.mycompany.mavenproject1;
import java.util.Stack;
public class Kruskals {
    static Stack<Double> stack = new Stack<>();
    int start;
    int end;
    double weight;

    public Kruskals() {
    }
    
    
    public Kruskals(int start, int end, double weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
    public Stack<Double> doubleToStack(double weight) {
        stack.push(weight);
        return stack;
    } // takes the weights from the array and stores them in the stack unsorted
    public static void sortStack(Stack<Double> stack) {
        Stack<Double> tempStack = new Stack<>();
        while (!stack.isEmpty()) {
            Double temp = stack.pop();
            while (!tempStack.isEmpty() && tempStack.peek() > temp) {
                stack.push(tempStack.pop());
            }
            tempStack.push(temp);
        }
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
    } // sorts the stack

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void printSortedStack() {

        sortStack(stack);
        System.out.print("Sorted stack: ");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();

    } // prints the sorted stack not needed in our project but I used it to test

    private static int[] makeSet(int size) {
        int[] id = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
        return id;
    } //given in the PDF

    static boolean findSet(int[] id, int p, int q) {
        return id[p] == id[q];
    } //given in the PDF

    static void union(int[] id, int p, int q) {
        int pid = id[p];
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = id[q];
            }
        }
    } //given in the PDF

    public  Kruskals[] kruskalMST(Kruskals[] edges, int numVertices) {

        int[] id = makeSet(numVertices);

        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - i - 1; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    Kruskals temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }

        Kruskals[] mst = new Kruskals[numVertices - 1];
        int mstIndex = 0;
        for (Kruskals edge : edges) {
            int u = edge.start;
            int v = edge.end;
            if (!findSet(id, u, v)) {
                union(id, u, v);
                mst[mstIndex++] = edge;
                if (mstIndex == numVertices - 1) {
                    break;
                }
            }
        }

        return mst;
    } // this will create the MST using the methods above
public static void printKruskal(Kruskals[] Array1, int vertices) { // 
        long startTime = System.nanoTime();
        Kruskals[] edges = new Kruskals[Array1.length];

        edges = Array1[1].kruskalMST(Array1, vertices);
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        double weight = 0;
        for (Kruskals edge : edges) {
            weight += edge.getWeight();
        } 
        System.out.println("Total weight of MST by Kruskals algorithm: " + weight);
        System.out.println("The edges in the MST are");
        for (Kruskals edge : edges) {
            System.out.println("Edge from " + edge.getStart() + " to " + edge.getEnd() + " has weight " + edge.getWeight());
        }
        System.out.println("Running Time of Kruskal’s algorithm using Union-Find approach is " + totalTime + " Nano seconds");
      
    } // this method will print output, time, mst 

}
