package search;

public class InsertSearch {
    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        System.out.println(insertSearch(array, 1, 0, array.length - 1));
        System.out.println(BinarySearch.binarySearchByRecursion(array, 1, 0, array.length - 1));
    }

    private static int insertSearch(int[] array, int value, int left, int right) {
        System.out.println("插值查找法~~~");
        if (left > right || value < array[left] || value > array[right]) {
            return -1;
        }
        int mid = left + (right - left) * (value - array[left]) / (array[right] - array[left]);
        int midValue = array[mid];
        if (value > midValue) {
            return insertSearch(array, value, mid+1, right);
        } else if (value < midValue) {
            return insertSearch(array, value, left, mid-1);
        }else{
            return mid;
        }
    }
}
