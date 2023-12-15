import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int V, E, K;

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

    static PriorityQueue<Point> pq;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        pq = new PriorityQueue<>();

        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            pq.offer(new Point(from, to, i));
        }

        boolean flag = false;
        while (K-- > 0) {
            make();
            PriorityQueue<Point> tmp = new PriorityQueue<>(pq);
            if (flag) {
                sb.append("0").append(" ");
                continue;
            }
            int pick = 1;
            int sum = 0;

            while (!tmp.isEmpty()) {
                Point curr = tmp.poll();
                if (union(curr.from, curr.to)) {
                    pick++;
                    sum += curr.w;
                }
                if (pick==V) {
                    pq.poll();
                    sb.append(sum).append(" ");
                    break;
                }
            }
            if (pick!=V) {
                flag = true;
                sb.append("0").append(" ");
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