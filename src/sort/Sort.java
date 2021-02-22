package sort;

import java.util.Arrays;

public interface Sort {

    void sort(int[] array);

    default  void testByArray(int[] array){
        long start = System.currentTimeMillis();
        sort(array);   //调用排序方法
        long end =System.currentTimeMillis();
        System.out.println("排序后的结果为：" + Arrays.toString(array));
        System.out.println("time :" +(end -start));
    }

    default void testByRandom(int n) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = (int) (Math.random()*n);
        }
        testByArray(array);
    }




}
