package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Graph {
    private List<String> vertexList;
    private int[][] edgs;
    private int numOfEdgs;
    private boolean[] visited;

    public Graph(int n) {
        vertexList = new ArrayList<>(n);
        edgs = new int[n][n];
        numOfEdgs = 0;
    }

    public void insertVertex(int v1, int v2, int weight) {
        edgs[v1][v2] = weight;
        edgs[v2][v1] = weight;
        numOfEdgs++;
    }

    public int getNumofVertex() {
        return vertexList.size();
    }

    public int getNumOfEdgs() {
        return numOfEdgs;
    }

    public int getWeight(int v1, int v2) {
        return edgs[v1][v2];
    }

    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }
    
    public void showGraph() {
        for (int[] edg : edgs) {
            System.out.println(Arrays.toString(edg));
        }
    }

    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edgs[index][i] > 0)
                return i;
        }
        return -1;
    }

    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edgs[v1][i] > 0)
                return i;
        }
        return -1;
    }
    
    private void dfs() {
        visited = new boolean[vertexList.size()];
        for (int i = 0; i < vertexList.size(); i++) {
            if(!visited[i])
                dfs(i);
        }
    }

    private void dfs(int index) {
        System.out.print(vertexList.get(index) + "->");
        visited[index] = true;
        int w = getFirstNeighbor(index);
        while (w != -1){
            if(!visited[w])
                dfs(w);
            w = getNextNeighbor(index, w);
        }
    }

    private void bfs(int index) {
        int u,w;
        LinkedList<Integer> queue = new LinkedList<>();
        visited[index] = true;
        queue.addLast(index);
        System.out.print(vertexList.get(index)+"=>");
        while (!queue.isEmpty()) {
            u = queue.removeFirst();
            w = getFirstNeighbor(u);
            while (w != -1) {
                if (!visited[w]) {
                    System.out.print(getValueByIndex(w)+"=>");
                    queue.addLast(w);
                    visited[w] = true;
                }
                w = getNextNeighbor(u, w);
            }
        }
    }

    private void bfs() {
        visited = new boolean[getNumofVertex()];
        for (int i = 0; i < getNumofVertex(); i++) {
           if(!visited[i])
               bfs(i);
        }
    }

    public static void main(String[] args) {
        int n = 8;
        //String[] vertexs = {"1", "2", "3", "4", "5", "6", "7", "8"};
        String[] vertexs = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(5);
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }
        graph.insertVertex(0, 1, 1);
        graph.insertVertex(0, 2, 1);
        graph.insertVertex(1, 2, 1);
        graph.insertVertex(1, 3, 1);
        graph.insertVertex(1, 4, 1);
        graph.showGraph();

        graph.dfs();
        System.out.println("\n================================");
        graph.bfs();

    }


}
