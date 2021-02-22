package search;

import java.util.Arrays;

public class FibonacciSearch {

    private static int maxSize = 20;

    public static void main(String[] args) {
        int[] array = {1, 8, 10, 89,1000, 1234};
        System.out.println("index =:"+fibonacciSearch(array,18));
    }

    /*返回Fibonacci数组*/
    private static int[] fib() {
        int[] array = new int[maxSize];
        array[0] = 1;
        array[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }
        return array;
    }

    public static int fibonacciSearch(int[] array, int key) {
        int[] f = fib();
        int k = 0, low = 0, high = array.length - 1;
        int mid;
        while (high > f[k] - 1)
            k++;
        int[] temp = Arrays.copyOf(array, f[k]);
        for (int i = high + 1; i < f[k]; i++)
            temp[i] = array[high];
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) {
                high = mid - 1;
                k--;
            } else if (key > temp[mid]) {
                low = mid + 1;
                k -= 2;
            } else {
                if (mid <= high)
                    return mid;
                else
                    return high;
            }
        }
        return -1;
    }
}

