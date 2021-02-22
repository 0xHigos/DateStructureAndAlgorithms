package tree;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] array = {4, 6, 8, 5, 9};
        HeapSort heap = new HeapSort();
        heap.heapSort(array);
        System.out.println("arrays:" + Arrays.toString(array));

    }

    private void heapSort(int[] array) {
        //首先，创建一个大顶堆
        for (int i = (array.length >> 1) - 1; i >= 0; i--) {
            adjust(array, i, array.length);
        }
        //然后进行堆顶与数组末尾swap的操作，并调整堆
        for (int i = array.length - 1; i >= 0; i--) {
            int temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            adjust(array, 0, i);
        }
    }

    private void adjust(int[] array, int i, int length) {
        int temp = array[i];
        for (int k = (i << 1) + 1; k < length; k = (k << 1) + 1) {
            if (k + 1 < length && array[k] < array[k + 1]) {
                k++;
            }
            if (array[k] > temp) {
                array[i] = array[k];
                i = k;
            } else {
                break;
            }
        }
        array[i] = temp;
    }

}
