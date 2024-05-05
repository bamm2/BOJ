import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    static int N;
    static int[] parents;

    static class Point {
        int from, to, cost;

        public Point(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int costTotal = 0;
        PriorityQueue<Point> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                int cost = convertToInt(s.charAt(j));
                costTotal += cost;
                if (i!=j && cost!=0) pq.offer(new Point(i, j, cost));
            }
        }

        make();

        int pick = 1;
        while (!pq.isEmpty()) {
            Point curr = pq.poll();
            if (union(curr.from, curr.to)) {
                pick++;
                costTotal -= curr.cost;
            }
            if (pick==N) break;
        }

        System.out.println(pick!=N ? -1:costTotal);
        br.close();
    }

    private static int convertToInt(char c) {
        if (c=='0') return 0;
        if (c - 'a' >= 0) return c - 'a' + 1;
        return c - 'A' + 27;
    }

    private static void make() {
        parents = new int[N];
        for (int i = 0; i < N; i++) {
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
}
