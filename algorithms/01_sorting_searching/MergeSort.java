package algorithms.sorting;

import java.util.Arrays;

/**
 * Merge Sort Implementation
 * Time Complexity: O(N log N) - Best, Average, Worst
 * Space Complexity: O(N)
 */
public class MergeSort {

    public static void sort(int[] arr) {
        if (arr.length < 2)
            return;

        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        sort(left);
        sort(right);

        merge(arr, left, right);
    }

    private static void merge(int[] target, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                target[k++] = left[i++];
            } else {
                target[k++] = right[j++];
            }
        }

        while (i < left.length) {
            target[k++] = left[i++];
        }

        while (j < right.length) {
            target[k++] = right[j++];
        }
    }

    public static void main(String[] args) {
        int[] arr = { 12, 11, 13, 5, 6, 7 };
        System.out.println("Original Array: " + Arrays.toString(arr));

        sort(arr);

        System.out.println("Sorted Array:   " + Arrays.toString(arr));
    }
}
