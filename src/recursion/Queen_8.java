package recursion;

public class Queen_8 {
    final static int max = 8;
    final static int[] array = new int[max];
    int count = 0;
    int judegCount = 0;

    public static void main(String[] args) {
        Queen_8 queen = new Queen_8();
        queen.check(0);
        System.out.println("total result :" + queen.count);
        System.out.println("total compare: " + queen.judegCount);
    }

    private void check(int n) {
        if (n == max) {
            print();
            return;
        }
        for (int i = 0; i < max; i++) {
            array[n] = i;
            if (judge(n))
                check(n + 1);
        }
    }

    private void print() {
        count++;
        for (int i = 0; i < max; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    private boolean judge(int n) {
        judegCount++;
        for (int i = 0; i < n; i++) {
            //array[n] == array[i] 表示在同一列
            //Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示在同一斜线
            if (array[n] == array[i] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }
}
