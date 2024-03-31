import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int V;
    static int[] parents;

    static class Point implements Comparable<Point> {
        int from, to, cost;

        public Point(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        PriorityQueue<Point> pq = new PriorityQueue<>();

        long allCost = 0;
        for (int i = 0; i < E; ++i) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            allCost += w;
            pq.offer(new Point(from, to, w));
        }

        make();

        int pick = 1;
        long cost = 0;
        while (!pq.isEmpty()) {
            Point curr = pq.poll();
            if (union(curr.from, curr.to)) {
                cost += curr.cost;
                pick++;
            }
            if (pick==V) break;
        }

        if (pick!=V) System.out.println(-1);
        else System.out.println(allCost - cost);

        br.close();
    }

    private static void make() {
        parents = new int[V + 1];
        for (int i = 0; i <= V; i++) {
            parents[i] = i;
        }
    }

    private static int find(int v) {
        if (parents[v]==v) return v;
        return parents[v] = find(parents[v]);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot==bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }

}