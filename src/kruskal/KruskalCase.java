package kruskal;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KruskalCase {

    private static int INF = Integer.MAX_VALUE;
    private int edgeNum;
    private char[] vertexs;
    private int[][] matrix;
    private static int count = 0;

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //克鲁斯卡尔算法的邻接矩阵
        int matrix[][] = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {0, 12, INF, INF, INF, 16, 14},
                /*B*/ {12, 0, 10, INF, INF, 7, INF},
                /*C*/ {INF, 10, 0, 3, 5, 6, INF},
                /*D*/ {INF, INF, 3, 0, 4, INF, INF},
                /*E*/ {INF, INF, 5, 4, 0, 2, 8},
                /*F*/ {16, 7, 6, INF, 2, 0, 9},
                /*G*/ {14, INF, INF, INF, 8, 9, 0}};

        //创建KruskalCase 对象实例
        KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);
        kruskalCase.showMatrix();
        /*EData[] edges = kruskalCase.getEdges();
        System.out.println(Arrays.toString(edges));
        kruskalCase.sortMatrix(edges);
        System.out.println("排序后的矩阵: " + Arrays.toString(edges));*/
        kruskalCase.kruskal();
    }

    private KruskalCase(char[] vertexs, int[][] matrix) {
        int vlen = vertexs.length;
        this.vertexs = new char[vlen];
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        for (int i = 0; i < vlen; i++) {
            for (int j = i + 1; j < vlen; j++) {
                if (this.matrix[i][j] != INF)
                    edgeNum++;
            }
        }
    }

    private void showMatrix() {
        System.out.println("邻接矩阵为：\n");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%-12d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    //获取边的位置
    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch)
                return i;
        }
        return -1;
    }

    //排序所有的边，从小到大
    private void sortMatrix(EData[] edges) {
        boolean flag = false;
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].getWeight() > edges[j + 1].getWeight()) {
                    EData temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag)
                break;
            else flag = false;
        }
    }

    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                    count++;
                }
            }
        }
        return edges;
    }

    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    private void kruskal() {
        int[] ends = new int[edgeNum];
        List<EData> list = new ArrayList<>();
        EData[] edges = getEdges();
        sortMatrix(edges);
        System.out.println("排序后的结果值：" + Arrays.toString(edges));
        for (int i = 0; i < edgeNum; i++) {
            int h1 = getPosition(edges[i].getStart());
            int h2 = getPosition(edges[i].getEnd());
            int m = getEnd(ends, h1);
            int n = getEnd(ends, h2);
            if (m != n) {
                ends[m] = n;
                list.add(edges[i]);
            }
        }
        System.out.println("最小生成树：");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }


}

class EData {
    private char start;
    private char end;
    private int weight;

    public char getStart() {
        return start;
    }

    public void setStart(char start) {
        this.start = start;
    }

    public char getEnd() {
        return end;
    }

    public void setEnd(char end) {
        this.end = end;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}
