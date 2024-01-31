import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import models.Interval;
import models.MergeSort;

public class Main {
    // Seed
    private static final long SEED = 18;

    /**
     * The main function for getting an input from the user
     * @param scanner - the Scanner Object
     * @param prompt - question to prompt to the user
     * @return the input of the user
     */
    public static int getUserInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextInt();
    }

    /**
     * This function generates all the intervals for merge sort iteratively, given the range of indices to sort. Algorithm runs in O(n).
     * @param start {Integer} - Start of range
     * @param end {Integer} - End of range
     * @return - Returns a list of Interval objects indicating the ranges for merge sort.
     */
    public static List<Interval> generateIntervals(int start, int end) {
        List<Interval> frontier = new ArrayList<>();
        frontier.add(new Interval(start, end));

        int i = 0;
        while(i < frontier.size()){
            int s = frontier.get(i).getStart();
            int e = frontier.get(i).getEnd();

            i++;

            // if base case
            if(s == e){
                continue;
            }

            // compute midpoint
            int m = s + (e - s) / 2;

            // add prerequisite intervals
            frontier.add(new Interval(m + 1, e));
            frontier.add(new Interval(s, m));
        }

        List<Interval> retval = new ArrayList<>();
        for(i = frontier.size() - 1; i >= 0; i--) {
            retval.add(frontier.get(i));
        }

        return retval;
    }

    public static void main(String[] args) {
        // Seed your randomizer
        Random random = new Random(SEED);

        // Get array size and thread count from user
        Scanner sc = new Scanner(System.in);

        int nSize =  getUserInput(sc, "Enter size of array: ");

        int nThreads = getUserInput(sc, "Enter number of threads: ");

        // Close the Scanner
        sc.close();

        // TODO: Generate a random array of given size
        int[] randomArray = new int[nSize];
        
        for (int i = 0; i < nSize; i++) {
            randomArray[i] = random.nextInt(nSize);
        }

        // TODO: Call the generate_intervals method to generate the merge sequence
        List<Interval> sequence = generateIntervals(0, nSize - 1);

        // TODO: Call merge on each interval in sequence
        for (Interval interval: sequence)
            MergeSort.merge(randomArray, interval.getStart(), interval.getEnd());

        //For Checking:
        for(int x :randomArray)
            System.out.println(x);
        // Once you get the single-threaded version to work, it's time to 
        // implement the concurrent version. Good luck :)
    }
}