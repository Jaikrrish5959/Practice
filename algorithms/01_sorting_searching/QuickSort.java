package algorithms.sorting;

import java.util.Arrays;

/**
 * Quick Sort Implementation
 * Time Complexity: O(N log N) Average, O(N^2) Worst
 * Space Complexity: O(log N) stack
 */
public class QuickSort {

    public static void sort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = { 10, 7, 8, 9, 1, 5 };
        System.out.println("Original Array: " + Arrays.toString(arr));

        sort(arr, 0, arr.length - 1);

        System.out.println("Sorted Array:   " + Arrays.toString(arr));
    }
}
