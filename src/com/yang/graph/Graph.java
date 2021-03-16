package com.yang.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graph {

    List<String> vertexList; //存放图的顶点的集合
    int[][] edges;  //存放图的边的二维数组
    int numOfEdges;  //图的边的数量
    boolean[] visited;

    public static void main(String[] args) {
        int n = 5;
        String[] vertexes = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(n);
        for (String vertex :
                vertexes) {
            graph.insertVertex(vertex);
        }
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);

        graph.printGraph();
    }



    public Graph(int n) {
        this.vertexList = new ArrayList<>(n);
        this.edges = new int[n][n];
        this.numOfEdges = 0;
        this.visited=new boolean[n];
    }

    public int getFirstNeighbor(int index){
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j]>0){
                return j;
            }
        }
        return -1;
    }

    /**
     * 返回当前节点邻接点的邻接点
     * @param index 当前节点在顶点集合中的索引
     * @param j 离当前节点最近的邻接点在的下一个邻接点在顶点集合的索引
     * @return 找到的节点索引
     */
    public int getNextNeighbor(int index,int j){
        for (int i = j+1; i < vertexList.size(); i++) {
            if (edges[index][i]>0){
                return i;
            }
        }
        return -1;
    }

    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    public int getWeight(int i, int j) {
        return edges[i][j];
    }

    public void printGraph() {
        for (int[] link :
                edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 插入边
     *
     * @param i      边的一个顶点在vertexList中的位置
     * @param j      边的另一个顶点在vertexList中的位置
     * @param weight 边的权重
     */
    public void insertEdge(int i, int j, int weight) {
        edges[i][j] = weight;
        edges[j][i] = weight;
        //边的数量+1；
        numOfEdges++;
    }

    public int getNumOfEdges() {
        return numOfEdges;
    }

    public int getNumOfVertex() {
        return vertexList.size();
    }

    public String getVertex(int i) {
        return vertexList.get(i);
    }
}
