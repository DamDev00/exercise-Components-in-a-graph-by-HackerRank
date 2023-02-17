/*
***
  https://www.hackerrank.com
***
*/

import java.math.BigInteger;
import java.util.ArrayList;  

public class ComponentsInAGraph {
      static final int len_final_result = 2;
      public static void main(String[] args) {
          int[][] graph = {{1, 6}, {2, 7}, {3, 8}, {4, 9}, {2, 6}};
          final int nodes = 9;
          int[] final_result = new int[ComponentsInAGraph.len_final_result];
          final int min = 0, max = 1, row = 5, col = 2;
          int[][] adj = new int[nodes][nodes];
          int[] nodesAdjacent = new int[nodes];
          int[] values = new int[nodes];

          adj = getAdjacentGraph(graph, nodes, row, col);
          nodesAdjacent = getNodesAdjacent(nodes, adj);

          for(int i = 0; nodes > i; i++){
              values[i] = getLinkedNodes(i, adj, nodes);
          }
  
          final_result[min] = Integer.MAX_VALUE;
          final_result[max] = Integer.MIN_VALUE;

          for(int i = 0; nodes > i; i++){
              if(values[i] != 1 && values[i] > final_result[max]){
                  final_result[max] = values[i];
              }
              if(values[i] != 1 && values[i] < final_result[min]){
                  final_result[min] = values[i];
              }
          }

          System.out.println("min: " + final_result[min]);
          System.out.println("Max: " + final_result[max]);

      }

      public static int getLinkedNodes(int start, int[][] adj, int n){

          java.util.ArrayList<Integer> list = new ArrayList<>();

          boolean[] visited = new boolean[n];

          for(int i = 0; n > i; i++){
              visited[i] = false;
          }

          list.add(start);
          int cont = 0, tmp = 0, price = 1;

          while(list.size() > cont){
              tmp = list.get(cont);
              for(int j = 0; n > j; j++){
                  if(adj[tmp][j] == 1 && !visited[j]){
                      list.add(j);
                      price += 1;
                  }
              }
              visited[tmp] = true;
              list.remove(0);
              if(list.size() > 0){
                  cont = 0;
              }
          }

          return price;
      }

      public static int[] getNodesAdjacent(int n, int adj[][]){
          int nodes;
          int[] results = new int[n];
          for(int i = 0; n > i; i++){
              nodes = 0;
              for(int j = 0; n > j; j++){
                  if(adj[i][j] == 1){
                      nodes += 1;
                  }
              }
              results[i] = nodes;
          }
          return results;
      }

      public static int[][] getAdjacentGraph(int[][] graph, int n, int row, int col){

          int[][] adj = new int[n][n];
          int cont;
          for(int i = 0; n > i; i++) {
              int current = i + 1;
              cont = 0;
              adj[i][i] = 0;
              for (int rows = 0; row > rows; rows++) {
                  for (int columns = 0; col > columns; columns++) {
                      if (graph[rows][columns] == current) {
                          for (int columns2 = 0; col > columns2; columns2++) {
                              if (current != graph[rows][columns2]) {
                                  adj[current - 1][graph[rows][columns2] - 1] = 1;
                              }
                          }
                          break;
                      }
                  }
              }
              for (int v = 0; n > v; v++) {
                  if (adj[i][v] != 1) {
                      adj[i][v] = 0;
                  }
              }
          }
          return adj;
      }
    }
}
