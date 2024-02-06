import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import models.BetterMergeSort;

public class Main {
    // Seed for the Randomizer
    private static final long SEED = 18;

    private static final int SIZE = 8388608;

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
        Random random = new Random(seed);

        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);

            // Swap Elements
            int temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
    }

    public static boolean isSorted(int[] arr){
        int temp = arr[0];
        for (int i = 1; i < arr.length; i++){
            if(arr[i] < temp){
                System.out.println("\nVAL 1: " + arr[i] + " VAL 2: " + temp);
                System.out.println("Found at Index: " + i + " and Index: " + (i-1)); 
                return false;
            }
            temp = arr[i];
        }
        return true;
    }

    public static void main(String[] args) {
        // Get array size and thread count from user
        Scanner sc = new Scanner(System.in);

        // int nSize =  getUserInput(sc, "Enter size of array: ");
        int nSize = SIZE;

        // int nThreads = getUserInput(sc, "Enter number of THREADS: ");

        // Close the Scanner
        sc.close();

        // Generate a random array of given size
        int[] randomArray = java.util.stream.IntStream.range(1, nSize + 1).toArray();

        // Shuffle Array
        shuffle(randomArray, SEED);

        // Display Before Array
        // System.out.println("\nBEFORE: " + Arrays.toString(randomArray));

        int[] threads = { 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024 };
        float ave = 0;
        long startTime, endTime;

        // Run MergeSort
        for (int thread : threads) {
            // Display Thread Count
            System.out.println("Running at Thread Count: " + thread);

            // Caching (Run 3 times)
            for (int i = 0; i < 3; i++) {
                BetterMergeSort.merge(randomArray.clone(), thread);
            }

            // Get the Average of 5 runs
            for (int i = 0; i < 5; i++) {
                int[] copy = randomArray.clone();

                // Start Timer
                startTime = System.nanoTime();
                
                // Call MergeSort
                BetterMergeSort.merge(copy, thread);
                
                // End Timer
                endTime = System.nanoTime();

                ave += (long) TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

                // Check if sorted
                System.out.println("Iteration: " + (i + 1) + ", Sorted: " + isSorted(copy));
            }
            ave /= 5;

            // Display Time and Results
            System.out.printf("Average Runtime: %d milliseconds.\n\n", (int) ave);

            // Set Average to 0 again
            ave = 0;

            // Garbage Collection
            System.gc();
        }
    }
}