package sort.Impl;

import sort.Sort;

public class InsertSort implements Sort {
    public static void main(String[] args) {
        InsertSort insertSort = new InsertSort();
        insertSort.testByRandom(80000);
    }

    @Override
    public void sort(int[] array) {
        int insertIndex;  //表示要插入数字的前一个坐标
        int insertValue;
        for (int i = 1; i < array.length; i++) {
            insertIndex = i - 1;
            insertValue = array[i];
            while (insertIndex >= 0 && array[insertIndex] > insertValue) {
                array[insertIndex + 1] = array[insertIndex];
                insertIndex--;
            }
            if (insertIndex != i) {
                array[insertIndex + 1] = insertValue;
            }

        }
    }
}
