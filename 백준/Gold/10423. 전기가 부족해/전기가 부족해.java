import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int V, E, K;
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
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        make();

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < K; i++) {
            int num = Integer.parseInt(st.nextToken()) - 1;
            parents[num] = -1;
        }

        PriorityQueue<Point> pq = new PriorityQueue<>();
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            pq.offer(new Point(from, to, w));
        }

        int sum = 0;
        while (!pq.isEmpty()) {
            Point curr = pq.poll();
            if (union(curr.from, curr.to)) {
                sum += curr.cost;
            }
            if (isSatisfied()) break;
        }

        bw.write(String.valueOf(sum));
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean isSatisfied() {
        for (int i = 0; i < V; i++) {
            if (parents[i]!=-1) return false;
        }
        return true;
    }

    private static void make() {
        parents = new int[V];
        for (int i = 0; i < V; i++) {
            parents[i] = i;
        }
    }

    private static int find(int a) {
        if (parents[a]==-1) return -1;
        if (parents[a]==a) return a;
        return parents[a] = find(parents[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot==bRoot) return false;

        if (aRoot==-1) {
            parents[bRoot] = -1;
        } else if (bRoot==-1) {
            parents[aRoot] = -1;
        } else {
            parents[bRoot] = aRoot;
        }
        return true;
    }

}