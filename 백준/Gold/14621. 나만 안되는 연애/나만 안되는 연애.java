import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int V;
    static char[] gender;

    static class Point implements Comparable<Point> {
        int from, to, dist;

        Point(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.dist, o.dist);
        }
    }

    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        gender = new char[V];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < V; i++) {
            gender[i] = st.nextToken().charAt(0);
        }

        PriorityQueue<Point> pq = new PriorityQueue<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            pq.offer(new Point(from, to, w));
        }

        make();

        int pick = 1;
        int sum = 0;
        while (!pq.isEmpty()) {
            Point curr = pq.poll();
            if (gender[curr.from]!=gender[curr.to] && union(curr.from, curr.to)) {
                sum += curr.dist;
                pick++;
            }
            if(pick == V) break;
        }

        if(pick!=V) System.out.println(-1);
        else System.out.println(sum);

    }

    private static void make() {
        parents = new int[V];
        for (int i = 0; i < V; i++) {
            parents[i] = i;
        }
    }

    private static int find(int a) {
        if (a==parents[a]) return a;
        return parents[a] = find(parents[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot==bRoot) return false;

        parents[bRoot] = aRoot;
        return true;
    }

}