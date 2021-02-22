package search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
        int[] array = {1, 8, 10, 89,1000, 1000, 1000,1000,1000,1000,1234};
        System.out.println(binarySearchList(array, 1000, 0, array.length - 1));
    }

    public static int binarySearch(int[] array, int value) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (array[mid] < value)
                left = mid + 1;
            else if (array[mid] > value)
                right = mid - 1;
            else if (array[mid] == value)
                return mid;
        }
        return -1;
    }

    public static int binarySearchByRecursion(int[] array, int value, int left, int right) {
        System.out.println("二分查找法");
        if (left > right) {
            return -1;
        }
        int mid = left + ((right - left) >> 1);
        int midValue = array[mid];
        if (value > midValue) {
            return binarySearchByRecursion(array, value, mid + 1, right);
        } else if (value < midValue) {
            return binarySearchByRecursion(array, value, left, mid - 1);
        } else {
            return mid;
        }
    }

    private static List<Integer> binarySearchList(int[] array, int value, int left, int right) {
        if (left > right)
            return null;
        int mid = left + ((right - left) >> 1);
        int midValue = array[mid];
        if (value > midValue)
            return binarySearchList(array, value, mid + 1, right);
        else if (value < midValue)
            return binarySearchList(array, value, left, mid - 1);
        else {
                List<Integer> list = new ArrayList<>();
                int temp = mid - 1;
                while (temp > 0 && array[temp] == value) {
                    list.add(temp--);
                }
                temp = mid + 1;
                while (temp < array.length-1 && array[temp] == value) {
                    list.add(temp++);
                }
            return list;
        }
    }


}
