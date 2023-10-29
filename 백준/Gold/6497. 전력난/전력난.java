import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int V, E;
    static int[] parents;
    static Edge[] edges;

    static class Edge implements Comparable<Edge> {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            if (isDone(V, E)) break;
            edges = new Edge[E];

            int originCost = 0;
            for(int i=0;i<E;i++){
                st = new StringTokenizer(br.readLine(), " ");
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                edges[i] = new Edge(from, to, weight);
                originCost += weight;
            }

            make();
            Arrays.sort(edges);

            int pick = 0;
            int sum = 0;
            for (int i = 0; i < E; i++) {
                Edge curr = edges[i];
                if (union(curr.from, curr.to)) {
                    sum += curr.cost;
                    if (++pick==V - 1) break;
                }
            }
            sb.append(originCost - sum).append('\n') ;
        }

        System.out.println(sb);

    }

    private static void make() {
        parents = new int[V];
        for (int i = 0; i < V; i++) {
            parents[i] = i;
        }
    }

    private static int find(int a) {
        if (parents[a]==a) return a;
        return parents[a] = find(parents[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot==bRoot) return false;

        parents[bRoot] = aRoot;
        return true;
    }

    private static boolean isDone(int a, int b) {
        return a==0 && b==0;
    }
}