public class MST
{
    private static final int V = 8;

    int minKey(int key[], Boolean mstSet[]) {

        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }


    void printMST(int parent[], int graph[][]) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++)
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
    }


    void primMST(int graph[][]) {

        int parent[] = new int[V];
        int key[] = new int[V];
        Boolean mstSet[] = new Boolean[V];
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }
        key[0] = 0;
        parent[0] = 0;

        for (int count = 0; count < V - 1; count++) {
            int u = minKey(key, mstSet);

            mstSet[u] = true;

            for (int v = 0; v < V; v++)
                if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
        }

        printMST(parent, graph);
    }

    public static void main(String[] args) {
        MST t = new MST();
        int graph[][] = new int[][]{{0, 2, 19, 0, 6, 15, 0, 0},   // 1
                {2, 0,  3, 12, 0, 0, 0, 8},   // 2
                {19, 3, 0, 16, 0, 0, 0, 7},   // 3
                {0, 12, 16, 0, 0, 0, 20, 0},   // 4
                {6, 0, 0, 0, 0, 2, 8, 17},    // 5
                {15, 0, 0, 0, 2, 0, 11, 0},   // 6
                {0, 0, 0, 20, 8, 11, 0, 8 },   // 7
                {0, 8, 7, 0, 17, 0, 8, 0 }};  // 8

        t.primMST(graph);
    }
}
