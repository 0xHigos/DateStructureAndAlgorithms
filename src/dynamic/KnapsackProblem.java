package dynamic;

import java.util.Arrays;

public class KnapsackProblem {

    public static void main(String[] args) {
        int[] weight = {1, 4, 3};
        int[] value = {1500, 3000, 2000};
        int M = 4;
        int N = value.length;
        int[][] table = new int[N + 1][M + 1];
        int[][] path = new int[N + 1][M + 1];
        int max = 0;
        for (int i = 0; i < table.length; i++) {
            table[i][0] = 0;
        }
        for (int i = 0; i < table[0].length; i++) {
            table[0][i] = 0;
        }

        for (int i = 1; i < table.length; i++) {
            for (int j = 1; j < table[0].length; j++) {
                if (weight[i - 1] > j)
                    table[i][j] = table[i - 1][j];
                else {
                    //table[i][j] = Math.max(table[i - 1][j], value[i - 1] + table[i - 1][j - weight[i - 1]]);
                    if (table[i - 1][j] < (value[i - 1] + table[i - 1][j - weight[i - 1]])) {
                        table[i][j] = value[i - 1] + table[i - 1][j - weight[i - 1]];
                        path[i][j] = 1;
                    }else{
                        table[i][j] = table[i - 1][j];
                    }
                }
            }
        }
        for (int[] values : table) {
            System.out.println(Arrays.toString(values));
        }

        int i = path.length -1;
        int j =path[0].length  -1;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入背包\n", i);
                j -= weight[i - 1];
            }
            i--;
        }

    }


}
