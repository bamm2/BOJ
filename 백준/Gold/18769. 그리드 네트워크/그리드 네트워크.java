import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int R, C, parents[], size;

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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            size = R * C;
            pq = new PriorityQueue<>();

            for (int i = 0; i < R; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < C - 1; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    int number = i * C + j;
                    pq.offer(new Point(number, number + 1, num));
                }
            }

            for (int i = 0; i < R - 1; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < C; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    int number = i * C + j;
                    pq.offer(new Point(number, number + C, num));
                }
            }

            make();

            int pick = 1;
            int sum = 0;
            while (!pq.isEmpty()) {
                Point curr = pq.poll();
                if (union(curr.from, curr.to)) {
                    pick++;
                    sum += curr.w;
                }
                if (pick==size) break;
            }
            sb.append(sum).append('\n');
        }

        System.out.println(sb);

    }

    private static void make() {
        parents = new int[size + 1];
        for (int i = 0; i <= size; i++) {
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