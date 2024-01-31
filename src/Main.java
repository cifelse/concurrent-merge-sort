import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import models.MergeSort;

public class Main {
    // Seed for the Randomizer
    private static final long SEED = 18;

    /**
     * The main function for getting an input from the user
     * @param scanner {Scanner} - the Scanner Object
     * @param prompt {String} - question to prompt to the user
     * @return the input of the user
     */
    public static int getUserInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextInt();
    }

    /**
     * Shuffles an Array randomly
     * @param array {Array<int>} - the array of integers to be shuffled
     * @param seed {long} - Randomizer seed
     */
    public static void shuffle(int[] array, long seed) {
        Random random = new Random(SEED);

        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);

            // Swap Elements
            int temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
    }

    public static void main(String[] args) {
        // Get array size and thread count from user
        Scanner sc = new Scanner(System.in);

        int nSize =  getUserInput(sc, "Enter size of array: ");

        int nThreads = getUserInput(sc, "Enter number of threads: ");

        // Close the Scanner
        sc.close();

        // Generate a random array of given size
        int[] randomArray = java.util.stream.IntStream.range(1, nSize + 1).toArray();

        // Shuffle Array
        shuffle(randomArray, SEED);

        // Display Before Array
        System.out.println("\nBEFORE: " + Arrays.toString(randomArray));

        // Start Timer
        long startTime = System.nanoTime();
        
        // Call MergeSort
        MergeSort.merge(randomArray, 0, randomArray.length - 1, nThreads);
        
        // End Timer
        long endTime = System.nanoTime();

        // Display After Array
        System.out.println("AFTER: " + Arrays.toString(randomArray));

        // Display Time and Results
        System.out.printf("\nTotal Runtime: %d milliseconds.\n", (long) TimeUnit.NANOSECONDS.toMillis(endTime - startTime));

        // Once you get the single-threaded version to work, it's time to 
        // implement the concurrent version. Good luck :)
    }
}