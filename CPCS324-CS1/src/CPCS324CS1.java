
import java.util.*;
import java.io.*;

public class CPCS324CS1 {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        int choice = 0;
        Scanner ABDO1 = new Scanner(System.in);

        while (choice != 5) {

            System.out.println("Please select an option:");
            System.out.println("1. Comparison between Horspool and Brute force algorithms");
            System.out.println("2. Finding minimum spanning tree using Prim’s algorithm");
            System.out.println("3. Finding minimum spanning tree using Kruskal’s algorithm");
            System.out.println("4. Finding shortest path using Dijkstra’s algorithm");
            System.out.println("5. Quit");
            if (ABDO1.hasNextInt()) {
                choice = ABDO1.nextInt();
            } else {
                System.exit(0);
            }

            switch (choice) {

                case 1:
                    Scanner ABDOSCANING = new Scanner(new File("input.txt"));
                    Scanner ABDOSCAN = new Scanner(System.in);
                    FileWriter patternsFile = new FileWriter("patterns.txt");

                    StringAlgorthim StringAlgorthim = new StringAlgorthim();
                    StringAlgorthim.shiftingTable = new HashMap<>();

                    //String[] patterns;
                    //print and take input from user
                    System.out.print("How many lines do you want to read from the text file?: ");
                    int numOfLines = ABDOSCAN.nextInt();
                    System.out.print("How many patterns do you want to generate?: ");
                    int numOfPatterns = ABDOSCAN.nextInt();
                    System.out.print("What is the length of each pattern?: ");
                    int sizeOfPattern = ABDOSCAN.nextInt();

                    //get lines of text from file and store them in string variable
                    String textToString = StringAlgorthim.readText(ABDOSCANING, numOfLines);

                    // generating patterns and store them in an array to use for algorithms
                    StringAlgorthim.patterns = StringAlgorthim.genPatterns(numOfLines, numOfPatterns, sizeOfPattern, textToString);

                    // print out the patterns in the file
                    for (String pattern : StringAlgorthim.patterns) {
                        patternsFile.write(pattern + "\n");
                    }
                    patternsFile.close();

                    //implement  algorithm and get average time
                    StringAlgorthim.algorithms(StringAlgorthim.patterns, textToString, sizeOfPattern);

                    break;

                case 2:

                    File abc = new File("input2.txt");
                    Scanner inputt = new Scanner(abc);
                    int numberOfVerticess = inputt.nextInt();
                    int numberOfEdgess = inputt.nextInt();
                    int[][] matrixx = Prim.storeEdges(inputt, numberOfVerticess);
                    inputt.close();
                    Graph G = Prim.constructGraph(matrixx, numberOfVerticess, numberOfEdgess);

                    long startTimeForMSTT = System.nanoTime();
                    List<Edge> mstt = Prim.primMSTT(G);
                    long endTimeForMSTT = System.nanoTime();

                    long startTimeForMST = System.nanoTime();
                    List<Edge> mst = Prim.primMST(G);
                    long endTimeForMST = System.nanoTime();

                    float totalWight = 0;

                    for (Edge edge : mstt) {
                        totalWight += (float) edge.getWeight();
                    }
                    System.out.println("Total weight of MST by Prim's algorithm (Using unordered MinPriority queue):" + totalWight);

                    System.out.println("Minimum Spanning Tree:");
                    for (Edge edge : mstt) {
                        System.out.println("Edge from " + edge.getSource().getValue() + " to " + edge.getTarget().getValue() + " has weight " + (float) edge.getWeight());
                    }

                    System.out.println("\nRunning time of Prim’s algorithm using unordered Min-Priority Queue is " + (endTimeForMSTT - startTimeForMSTT) + " Nano seconds\n");

                    totalWight = 0;
                    for (Edge edge : mst) {
                        totalWight += (float) edge.getWeight();
                    }
                    System.out.println("Total weight of MST by Prim's algorithm (Using Min-Heap):" + totalWight);

                    System.out.println("Minimum Spanning Tree:");
                    for (Edge edge : mst) {
                        System.out.println("Edge from " + edge.getSource().getValue() + " to " + edge.getTarget().getValue() + " has weight " + (float) edge.getWeight());
                    }
                    System.out.println("\nRunning time of Prim’s algorithm using Min-Heap is " + (endTimeForMST - startTimeForMST) + " Nano seconds");

                    break;
                case 3:
                    // Code for option3 3
                    File ABDO = new File("input2.txt");
                    Scanner in = new Scanner(ABDO);
                    int numberOfVertices = in.nextInt();
                    int numberOfEdges = in.nextInt();
                    Kruskals[] Array1 = new Kruskals[numberOfEdges];

                    // this for loop will store the edges in a Kruskals array so the array will contain the start,target and weight
                    for (int i = 0; i < numberOfEdges; i++) {
                        Array1[i] = new Kruskals(in.nextInt(), in.nextInt(), in.nextDouble());
                        Array1[i].doubleToStack(Array1[i].getWeight()); // this will take the weights from the array and will put them into a stack for further operations

                    }
                    //Kruskals k = new Kruskals();
                    Kruskals.printKruskal(Array1, numberOfVertices);
                    break;
                case 4:
                    /*
                        -read weighted, directed graph from input file
                        -store into adjacency matrix
                        -then implement Dijkstra
                     */ File inputFile = new File("input1.txt");
                    Scanner input = new Scanner(inputFile);
                    //Dijkstra di=new Dijkstra();
                    // read from file
                    numberOfVertices = input.nextInt(); // first line contains number of vertices
                    numberOfEdges = input.nextInt(); // second line contains number edges
                    int[][] matrix = Dijkstra.storeEdges(input, numberOfVertices);
                    input.close();

                    // print matrix
                    Dijkstra.printMatrix(matrix, numberOfVertices, numberOfEdges);

                    // construct graph after storing it in an adjacency matrix
                    Graph graph = Dijkstra.constructGraph(matrix, numberOfVertices, numberOfEdges);
                    // user inputs source vertex
                    input = new Scanner(System.in);
                    System.out.println("Enter source vertex");
                    int sourceVertex = input.nextInt();
                    long startTime1 = System.nanoTime();
                    double dijkstraUnsortedArrayExecutionTime = Dijkstra.DijkstraUnsortedArray(sourceVertex, numberOfEdges, graph);
                    long stopTime1 = System.nanoTime();
                    //System.out.println(stopTime1 - startTime1);
                    long startTime2 = System.nanoTime();
                    double dijkstraMinHeapExecutionTime = Dijkstra.DijkstraMinHeap(sourceVertex, numberOfEdges, graph);
                    long stopTime2 = System.nanoTime();
                    System.out.print("\nComparison Of the running time :");
                    System.out.println("\nRunning time of Dijkstra using unsorted array is " + (stopTime1 - startTime1) + " ns");
                    System.out.print("Running time of Dijkstra using min-heap array is " + (stopTime2 - startTime2) + " ns");

                    if ((stopTime1 - startTime1) > (stopTime2 - startTime2)) {
                        System.out.println("\nRunning time of Dijkstra using min Heap is better");
                    } else {
                        System.out.println("\nRunning time of Dijkstra using unsorted array is better");
                    }
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;

            }

        }

    }//end of Main 

}//end of class CPCS324CS1

