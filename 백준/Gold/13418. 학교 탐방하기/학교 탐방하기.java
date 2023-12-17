import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int V, E;
    static boolean plus = false;
    static int[] parents;

    static class Point implements Comparable<Point> {
        int from, to, dir;

        public Point(int from, int to, int dir) {
            this.from = from;
            this.to = to;
            this.dir = dir;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(o.dir, this.dir);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        PriorityQueue<Point> pq = new PriorityQueue<>(Collections.reverseOrder());
        List<Point> list = new ArrayList<>();
        for (int i = 0; i <= E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int upOrDown = Integer.parseInt(st.nextToken());
            if (i==0 && upOrDown==0) plus = true;
            else list.add(new Point(from, to, upOrDown));
        }

        pq.addAll(list);
        int max = kru(pq);
        pq = new PriorityQueue<>(list);
        int min = kru(pq);
        System.out.println(max - min);
    }

    private static int kru(PriorityQueue<Point> pq) {
        make();
        int pick = 1;
        int up = plus ? 1:0;
        while (!pq.isEmpty()) {
            Point curr = pq.poll();
            if (union(curr.from, curr.to)) {
                if (curr.dir==0) up++;
                pick++;
            }
            if (pick==V) break;
        }

        return (int) Math.pow(up, 2);
    }

    private static void make() {
        parents = new int[V + 1];
        parents[1] = 0;
        for (int i = 2; i <= V; i++) {
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