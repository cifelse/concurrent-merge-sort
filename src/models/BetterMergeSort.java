package models;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class BetterMergeSort {
    /**
     * The main function that executes MergeSort
     * @param array {Array<int>} - The Array to be sorted
     * @param nThreads {int} - The number of Threads to be implemented
     */
    public static void merge(int[] array, int nThreads) {
        // Create a ForkJoinPool with the specified number of threads
        ForkJoinPool pool = new ForkJoinPool(nThreads);

        // Start the MergeSortTask
        pool.invoke(new MergeSortTask(array, 0, array.length - 1));

        // Shutdown the pool
        pool.shutdown();
        
        // pool.close(); // Deprecated
    }

    /**
     * This class extends RecursiveAction and implements the merge sort algorithm
     */
    private static class MergeSortTask extends RecursiveAction {
        // The array to be sorted
        private int[] array;

        // The start index of the array
        private int start;

        // The end index of the array
        private int end;

        /**
         * Constructor for MergeSortTask
         * @param array {Array<int>} - The Array to be sorted
         * @param start - {int} Start range: 0
         * @param end - {int} End range: Array Length - 1
         */
        public MergeSortTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        /**
         * The main computation performed by this task
         */
        @Override
        protected void compute() {
            // If the array has more than one element, split it and sort the halves
            if (start < end) {
                // Find the middle point
                int mid = start + (end - start) / 2;

                // Split the task into two subtasks
                invokeAll(new MergeSortTask(array, start, mid), new MergeSortTask(array, mid + 1, end));

                // Merge the sorted halves
                sort(array, start, mid, end);
            }
        }

        private void sort(int[] array, int start, int mid, int end) {
            int[] left = new int[mid - start + 1];
            int[] right = new int[end - mid];

            // Copy data to temporary arrays left[] and right[]
            for (int i = 0; i < left.length; i++) {
                left[i] = array[start + i];
            }
            for (int i = 0; i < right.length; i++) {
                right[i] = array[mid + 1 + i];
            }

            int i = 0, j = 0, k = start;
            
            // Merge the temporary arrays back into array[start..end]
            while (i < left.length && j < right.length) {
                if (left[i] <= right[j]) {
                    array[k] = left[i];
                    i++;
                } else {
                    array[k] = right[j];
                    j++;
                }
                k++;
            }

            // Copy the remaining elements of left[], if there are any
            while (i < left.length) {
                array[k] = left[i];
                i++;
                k++;
            }

            // Copy the remaining elements of right[], if there are any
            while (j < right.length) {
                array[k] = right[j];
                j++;
                k++;
            }
        }
    }
}
