
import java.util.*;
 
public class StringAlgorthim {

    public HashMap<Character, Integer> shiftingTable;
    String[] patterns;
 

    public String readText(Scanner ABDOSCAN, int numberOfLines) {
        StringBuilder textFromFile = new StringBuilder();
        // read lines from text file
        for (int i = 0; i < numberOfLines; i++) {
            if (ABDOSCAN.hasNextLine()) {
                textFromFile.append(ABDOSCAN.nextLine());
            }
        }
        return textFromFile.toString().toLowerCase();
    }

    public String[] genPatterns(int numOfLines, int numOfPatterns, int sizeOfPatterns, String theText) {

        String[] patterns = new String[numOfPatterns];

        // create patterns from lines of text file randomly
        Random genreatRandom = new Random();
        for (int i = 0; i < numOfPatterns; i++) {
            int randNum = genreatRandom.nextInt(theText.length() - sizeOfPatterns);
            patterns[i] = theText.substring(randNum, randNum + sizeOfPatterns);
        }
        System.out.printf("%d Patterns, each of length %d have been generated in a file pattern.txt\n", numOfPatterns, sizeOfPatterns);
        return patterns;
    }

    public void genrateShiftingTable(String pattern) {
        //implement algorithm
        int lengthOfPattern = pattern.length();
        for (int i = 0; i < lengthOfPattern - 1; i++) {
            shiftingTable.put(pattern.charAt(i), lengthOfPattern - 1 - i);
        }
        System.out.println(shiftingTable);
    }

    public int horspool(String pattern, String theText, int A) {
        int i = A - 1;
        for (; i < theText.length();) {
            int patternIndex = 0;
            while ((patternIndex < A) && (pattern.charAt(A - 1 - patternIndex) == (theText.charAt(i - patternIndex)))) {
                patternIndex++;
            }
            if (patternIndex == A) {
                return i - A + 1;
            } else {
                i = i + shiftingTable.getOrDefault(theText.charAt(i), A);
            }
        }
        return -1;
    }

    public int bruteForce(String pattern, String theText, int size) {
        for (int i = 0; i <= theText.length() - size; i++) {
            int j = 0;
            while ((j < size) && (pattern.charAt(j) == theText.charAt(i + j))) {
                j++;
            }
            if (j == size) {
                return i;
            }
        }
        return -1;
    }

    public void algorithms(String[] patterns, String theText ,int size) {
        ArrayList<Long> horspoolTime = new ArrayList<>();
        ArrayList<Long> bruteForceTime = new ArrayList<>();
        long startTime, endTime, timeTaken;

        for (String pattern : patterns) {
            shiftingTable.clear();
            System.out.printf("The shift table for \"%s\" is:\n", pattern);
            startTime = System.nanoTime();
            genrateShiftingTable(pattern);
            horspool(pattern, theText, size);
            endTime = System.nanoTime();
            timeTaken = endTime - startTime;
            horspoolTime.add(timeTaken);

            startTime = System.nanoTime();
            bruteForce(pattern, theText, size);
            endTime = System.nanoTime();
            timeTaken = endTime - startTime;
            bruteForceTime.add(timeTaken);
            System.out.println();
        }
        System.out.printf("Average time of search in Brute Force approach: %,d ns \n", calculateAvg(bruteForceTime));
        System.out.printf("Average time of search in Horspool approach: %,d ns \n", calculateAvg(horspoolTime));

        if (calculateAvg(bruteForceTime) < calculateAvg(horspoolTime)) {
            System.out.println("For this instance, Brute Force approach is better than Horspool approach.");
        } else {
            System.out.println("For this instance, Horspool approach is better than Brute Force approach.");
        }
    }

    public long calculateAvg(ArrayList<Long> time) {

        long sum = 0;
        for (int i = 0; i < time.size(); i++) {
            sum += time.get(i);
        }
        return sum / time.size();
    }
}
