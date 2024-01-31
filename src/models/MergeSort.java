package models;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {
    /**
     * The main function that executes MergeSort
     * @param array {Array<int>} - The Array to be sorted
     * @param start - {int} Start range: 0
     * @param end - {int} End range: Array Length
     * @param nThreads {int} - The number of Threads to be implemented
     */
    public static void merge(int[] array, int start, int end, int nThreads) {
        List<Interval> intervals = generateIntervals(start, end);

        for (Interval interval : intervals) {
            sort(array, interval.getStart(), interval.getEnd());
        }
    }

    /**
     * This function generates all the intervals for merge sort iteratively, given the range of indices to sort. Algorithm runs in O(n).
     * @param start {int} - Start of range
     * @param end {int} - End of range
     * @return - Returns a list of Interval objects indicating the ranges for merge sort.
     */
    private static List<Interval> generateIntervals(int start, int end) {
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

    /**
     * This function performs the merge operation of merge sort.
     * @param array {Vector<int>} - array to sort
     * @param s {int} - start index of merge
     * @param e {int} - end index (inclusive) of merge
     */
    private static void sort(int[] array, int s, int e) {
        int m = s + (e - s) / 2;
        int[] left = new int[m - s + 1];
        int[] right = new int[e - m];
        int l_ptr = 0, r_ptr = 0;
        for(int i = s; i <= e; i++) {
            if(i <= m) {
                left[l_ptr++] = array[i];
            } else {
                right[r_ptr++] = array[i];
            }
        }
        l_ptr = r_ptr = 0;

        for(int i = s; i <= e; i++) {
            // no more elements on left half
            if(l_ptr == m - s + 1) {
                array[i] = right[r_ptr];
                r_ptr++;

            // no more elements on right half or left element comes first
            } else if(r_ptr == e - m || left[l_ptr] <= right[r_ptr]) {
                array[i] = left[l_ptr];
                l_ptr++;
            } else {
                array[i] = right[r_ptr];
                r_ptr++;
            }
        }
    }
}
