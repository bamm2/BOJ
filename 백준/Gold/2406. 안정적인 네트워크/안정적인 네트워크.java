import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int V, E;
    static int[] parents;

    static class Point implements Comparable<Point> {
        int from, to, w;

        public Point(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.w, o.w);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        make();

        int pick = 0;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            if (union(from, to)) pick++;
        }

        PriorityQueue<Point> pq = new PriorityQueue<>();

        for (int i = 1; i <= V; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= V; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (i >= j || i==1 || j==1) continue;
                pq.offer(new Point(i, j, num));
            }
        }

        List<Point> list = new ArrayList<>();
        int sum = 0;
        while (!pq.isEmpty()) {
            if (pick >= V - 2) break;
            Point curr = pq.poll();
            if (union(curr.from, curr.to)) {
                pick++;
                list.add(curr);
                sum += curr.w;
            }
        }

        if (sum==0) sb.append(0).append(" ").append(0);
        else {
            sb.append(sum).append(" ").append(list.size()).append('\n');
            for (int i = 0; i < list.size(); i++) {
                Point point = list.get(i);
                sb.append(point.from).append(" ").append(point.to).append('\n');
            }
        }

        System.out.println(sb);

    }

    private static void make() {
        parents = new int[V + 1];
        for (int i = 0; i <= V; i++) {
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
        if (aRoot > bRoot) {
            int tmp = aRoot;
            aRoot = bRoot;
            bRoot = tmp;
        }

        parents[bRoot] = aRoot;
        return true;
    }
}