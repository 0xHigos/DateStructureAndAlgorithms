package sort.Impl;

import sort.Sort;

import java.util.Arrays;

public class QuickSort implements Sort {
    public static void main(String[] args) {
        int[] array = {-9, 78, 0, 23, -567, 70};
        QuickSort quickSort = new QuickSort();
        quickSort.testByRandom(8000000);
    }

    @Override
    public void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    private void sort(int[] array, int left, int right) {
        int l = left;
        int r = right;
        int pivot = array[l + ((r - l) >> 1)];
        int temp;
        while (l < r) {
            while (array[l] < pivot)
                l++;
            while (array[r] > pivot)
                r--;
            if (l >= r)
                break;
            temp = array[l];
            array[l] = array[r];
            array[r] = temp;
            if (array[l] == pivot)
                r--;
            if (array[r] == pivot)
                l++;
        }
        if (l == r) {
            r--;
            l++;
        }
        if (left < r)
            sort(array, left, r);
        if (right > l)
            sort(array, l, right);
    }

    public void quickSort(int[] array, int left, int right) {
        if (left >= right)
            return;
        int pivot = array[left];
        int temp ;
        int i = left;
        int j = right;
        while (i < j) {
            while (i < j && array[i] < pivot)
                i++;
            while (i < j && array[j] > pivot)
                j--;
            if (i < j) {
                temp =array[i];
                array[i] =array[j];
                array[j] =temp;
            }
        }
        array[left] = array[j];
        array[j] = pivot;

        quickSort(array, left, j - 1);
        quickSort(array, j + 1, right);
    }

}

