package Graph;

public class ChainForwardStar {
    int[] head;
    int[] next;
    int[] to;
    int[] weight;
    int cnt;

    public ChainForwardStar(int n, int m){
        cnt = 1;
        head = new int[n + 1];
        next = new int[m + 1];
        to = new int[m + 1];
        weight = new int[m + 1];
    }

    public void addEdge(int u, int v, int w){
        next[cnt] = head[u];
        to[cnt] = v;
        weight[cnt] = w;
        head[u] = cnt++;
    }

    public void addEdge(int u, int v){
        next[cnt] = head[u];
        to[cnt] = v;
        head[u] = cnt++;
    }

    public void createDirectedGraph(int[][] edges){
        for(int[] edge : edges){
            if(edge.length == 3){
                addEdge(edge[0], edge[1], edge[2]);
            }
            else if(edge.length == 2){
                addEdge(edge[0], edge[1]);
            }
        }
    }

    public void createUndirectedGraph(int[][] edges){
        for(int[] edge : edges){
            if(edge.length == 3){
                addEdge(edge[0], edge[1], edge[2]);
                addEdge(edge[1], edge[0], edge[2]);
            }
            else if(edge.length == 2){
                addEdge(edge[0], edge[1]);
                addEdge(edge[1], edge[0]);
            }
        }
    }

    public void traversal(int n){
        for(int i = 1; i <= n; i++){
            System.out.print(i + " （邻居、边权）：");
            for(int ei = head[i]; ei > 0; ei = next[ei]){
                System.out.print("（" + to[ei] + "、" + weight[ei] + "）");
            }
            System.out.println();
        }
    }

}
