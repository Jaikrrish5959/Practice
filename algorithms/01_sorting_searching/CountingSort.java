package algorithms.sorting;

import java.util.Arrays;

/**
 * Counting Sort Implementation
 * Time Complexity: O(N + K) where K is the range of input
 * Space Complexity: O(K)
 */
public class CountingSort {

    public static void sort(int[] arr) {
        int n = arr.length;
        if (n == 0)
            return;

        // Find max element
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        int range = max - min + 1;

        int[] count = new int[range];
        int[] output = new int[n];

        // Store count of each character
        for (int i = 0; i < n; i++) {
            count[arr[i] - min]++;
        }

        // Change count[i] so that count[i] now contains actual
        // position of this character in output array
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // Build the output character array
        for (int i = n - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }

        // Copy the output array to arr, so that arr now
        // contains sorted characters
        System.arraycopy(output, 0, arr, 0, n);
    }

    public static void main(String[] args) {
        int[] arr = { 4, 2, 2, 8, 3, 3, 1 };
        System.out.println("Original Array: " + Arrays.toString(arr));

        sort(arr);

        System.out.println("Sorted Array:   " + Arrays.toString(arr));
    }
}
