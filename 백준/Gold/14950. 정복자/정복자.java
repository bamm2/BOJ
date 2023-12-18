import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int V, E, sum;
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
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());


        sum = (V - 1) * ((V - 2) * t) / 2; // 등차수열의 합 ( n(a1 +  a1 +(n-1)d) / 2 )  ,여기서 첫항은 0

        PriorityQueue<Point> pq = new PriorityQueue<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.offer(new Point(from, to, w));
        }

        make();

        int pick = 1;
        while (!pq.isEmpty()) {
            Point curr = pq.poll();
            if (union(curr.from, curr.to)) {
                pick++;
                sum += curr.w;
            }
            if (pick==V) break;
        }

        System.out.println(sum);

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