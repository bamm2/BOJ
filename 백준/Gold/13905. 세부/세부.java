import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int V, E, start, goal;
    static int[] parent;

    static class Point implements Comparable<Point> {
        int from, to, w;

        public Point(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(o.w, this.w);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        start = Integer.parseInt(st.nextToken());
        goal = Integer.parseInt(st.nextToken());

        PriorityQueue<Point> pq = new PriorityQueue<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.offer(new Point(from, to, w));
        }

        make();

        int ans = 0;
        while (!pq.isEmpty()) {
            Point curr = pq.poll();
            if (union(curr.from, curr.to)) {
                ans = curr.w;
                if (find(start)==find(goal)) {
                    System.out.println(ans);
                    return;
                }
            }
        }

        System.out.println(0);

    }

    private static void make() {
        parent = new int[V + 1];
        for (int i = 0; i <= V; i++) {
            parent[i] = i;
        }
    }

    private static int find(int a) {
        if (parent[a]==a) return a;
        return parent[a] = find(parent[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot==bRoot) return false;
        if (aRoot > bRoot) {
            int tmp = aRoot;
            aRoot = bRoot;
            bRoot = tmp;
        }
        parent[bRoot] = aRoot;
        return true;
    }
}