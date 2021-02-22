package sort.Impl;

import sort.Sort;

public class SelectSort implements Sort {
    public static void main(String[] args) {
        SelectSort selectSort = new SelectSort();
        selectSort.testByRandom(80000);
    }

    @Override
    public void sort(int[] array) {
        int minIndex;
        int min;
        for (int i = 0; i < array.length; i++) {
            minIndex = i;
            min = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (min > array[j]) {
                    minIndex = j;
                    min = array[j];
                }
            }
            if (minIndex != i) {
                array[minIndex] = array[i];
                array[i] = min;
            }
        }
    }
}
