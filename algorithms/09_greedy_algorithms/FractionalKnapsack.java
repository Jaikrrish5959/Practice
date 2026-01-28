package algorithms.greedy_algorithms;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Fractional Knapsack Problem (Greedy)
 * Time Complexity: O(N log N) (due to sorting)
 */
public class FractionalKnapsack {

    static class Item {
        int weight, value;

        public Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
    }

    public static double getMaxValue(Item[] items, int capacity) {
        // Sort items by value/weight ratio in descending order
        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                double r1 = (double) o1.value / o1.weight;
                double r2 = (double) o2.value / o2.weight;
                return Double.compare(r2, r1); // Descending
            }
        });

        double totalValue = 0d;

        for (Item i : items) {
            int curWt = i.weight;
            int curVal = i.value;

            if (capacity - curWt >= 0) {
                // Take whole item
                capacity -= curWt;
                totalValue += curVal;
            } else {
                // Take fraction
                double fraction = (double) capacity / curWt;
                totalValue += (curVal * fraction);
                capacity = 0;
                break;
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        Item[] items = {
                new Item(60, 10),
                new Item(100, 20),
                new Item(120, 30)
        };
        int capacity = 50;

        double maxValue = getMaxValue(items, capacity);
        System.out.println("Maximum value in Knapsack = " + maxValue);
    }
}
