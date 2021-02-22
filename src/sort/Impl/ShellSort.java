package sort.Impl;

import sort.Sort;

public class ShellSort implements Sort {

    public static void main(String[] args) {
        ShellSort shellSort = new ShellSort();
        shellSort.testByArray(new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0});
        shellSort.testByRandom(80000);
    }

    /*
    @Override
    public void sort(int[] array) {
        int temp ;
        for (int gap = (array.length >> 1); gap > 0; gap /= 2) //控制步长
            for (int i = gap; i < array.length; i++)
                for (int j = i - gap; j >= 0; j -= gap)
                    if (array[j] > array[j + gap]) {
                        temp = array[j];
                        array[j] = array[j + gap];
                        array[j + gap] = temp;
                    }
    }*/

    public void sort(int[] array) {
        int temp;
        for (int gap = (array.length >> 1); gap > 0; gap = (gap >> 1))
            for (int i = gap; i < array.length; i++) {
                int j = i;
                temp = array[j];
                if (temp < array[j - gap]) {
                    while (j - gap >= 0 && temp < array[j - gap]) {
                        array[j] = array[j - gap];
                        j -= gap;
                    }
                    array[j] = temp;
                }
            }
    }
}
