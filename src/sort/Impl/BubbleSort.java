package sort.Impl;

import sort.Sort;

public class BubbleSort implements Sort {
    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.testByRandom(80000);
    }

    @Override
    public void sort(int[] array) {
        boolean flag = false;
        int temp;
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array.length - i; j++) {
                if (array[j] < array[j - 1]) {
                    flag = true;
                    temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
            if (!flag)
                break;
            else flag = false;
        }
    }
}
