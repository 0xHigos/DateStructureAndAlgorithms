package sort.Impl;

import sort.Sort;

public class RadixSort implements Sort {
    public static void main(String[] args) {
        RadixSort radixSort = new RadixSort();
        radixSort.testByArray(new int[]{53, 3, 542, 748, 14, 214});
        radixSort.testByRandom(800000);
    }

    @Override
    public void sort(int[] array) {
        int max = array[0];
        for (int i : array) {
            if (max < i)
                max = i;
        }
        //获取最大数字的长度
        int maxLength = (max + "").length();

        int[][] buckets = new int[10][array.length];
        //bucketElementCounts 记录的是每个bucket桶的个数
        int[] bucketElementCounts = new int[10];

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //存入桶中
            for (int j = 0; j < array.length; j++) {
                int digitCount = array[j] / n % 10;
                buckets[digitCount][bucketElementCounts[digitCount]++] = array[j];
            }
            //取出并放回array中
            int index = 0;
            for (int j = 0; j < 10; j++) {
                if (bucketElementCounts[j] != 0)
                    for (int k = 0; k < bucketElementCounts[j]; k++)
                        array[index++] = buckets[j][k];

                bucketElementCounts[j] = 0;
            }
        }
    }
}
