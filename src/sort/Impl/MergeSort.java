package sort.Impl;

import sort.Sort;

public class MergeSort implements Sort {

    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        MergeSort mergeSort = new MergeSort();
        mergeSort.testByRandom(8000000);
    }

    @Override
    public void sort(int[] array) {
        sort(array, 0, array.length - 1, new int[array.length]);
    }

    public void sort(int[] array, int left, int right, int[] temp) {
        if (left < right) {
            int mid = left + ((right - left) >> 1);
            sort(array, left, mid, temp);
            sort(array, mid + 1, right, temp);
            merge(array, left, mid, right, temp);
        }
    }

    private void merge(int[] array, int left, int mid, int right, int[] temp) {
        int i = left, j = mid + 1, t = 0;
        while (i <= mid && j <= right) {
            if (array[i] < array[j]) {
                temp[t++] = array[i++];
            }else {
                temp[t++] = array[j++];
            }
        }
        while (i <= mid) {
            temp[t++] = array[i++];
        }
        while (j <= right) {
            temp[t++] = array[j++];
        }

        t = 0;
        for (int tempLeft = left; tempLeft <= right; tempLeft++) {
            array[tempLeft] = temp[t++];
        }
    }


}
