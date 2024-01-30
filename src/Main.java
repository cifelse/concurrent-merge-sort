import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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

    public static void main(String[] args) {
        // Seed your randomizer
        Random random = new Random(SEED);

        // Get array size and thread count from user
        Scanner sc = new Scanner(System.in);

        int nSize =  getUserInput(sc, "Enter size of array: ");

        int nThreads = getUserInput(sc, "Enter number of threads: ");

        // Close the Scanner
        sc.close();

        // Generate a random array of given size
        int[] randomArray = new int[nSize];

        // TODO: Call the generate_intervals method to generate the merge 
        // sequence

        // TODO: Call merge on each interval in sequence

        // Once you get the single-threaded version to work, it's time to 
        // implement the concurrent version. Good luck :)
    }
}