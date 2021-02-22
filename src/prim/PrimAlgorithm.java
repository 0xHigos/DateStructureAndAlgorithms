package prim;

import java.util.Arrays;

public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000},};
        Graph graph = new Graph(7, data, weight);
        MinTree minTree = new MinTree();
        minTree.createTree(graph);
        minTree.showGraph();
        minTree.prim(0);
    }
}

class MinTree {
    private Graph graph;

    public void createTree(Graph graph) {
        this.graph = graph;
    }

    public void showGraph() {
        for (int[] link : graph.weight)
            System.out.println(Arrays.toString(link));
    }

    public void prim(int v) {
        int[] visited = new int[graph.verxs];
        visited[v] =1;
        int h1=-1, h2=-1;
        int minLength = 100000;
        for (int k = 1; k < graph.verxs; k++) {
            for (int i = 0; i < graph.verxs; i++) {
                for (int j = 0; j < graph.verxs; j++) {
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minLength) {
                        minLength = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值:" + minLength);
            visited[h2] = 1;
            minLength =10000;
        }
    }
}

class Graph {
    int verxs;
    char[] data;
    int[][] weight;

    public Graph(int verxs) {
        this.verxs = verxs;
        this.data = new char[verxs];
        weight = new int[verxs][verxs];
    }

    public Graph(int verxs, char[] data, int[][] weight) {
        this.verxs = verxs;
        this.data = data;
        this.weight = weight;
    }

    public int getVerxs() {
        return verxs;
    }

    public char[] getData() {
        return data;
    }

    public void setData(char[] data) {
        this.data = data;
    }

    public int[][] getWeight() {
        return weight;
    }

    public void setWeight(int[][] weight) {
        this.weight = weight;
    }
}
