import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Point {
        int from, to, w;

        public Point(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }
    }

    static List<Point> list;
    static int V, dist[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            list = new ArrayList<>();

            for (int i = 0; i < E + W; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                if (i < E) {
                    list.add(new Point(from, to, w));
                    list.add(new Point(to, from, w));
                } else {
                    list.add(new Point(from, to, -w));
                }
            }

            boolean flag = false;
            dist = new int[V + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            for (int i = 1; i <= V; i++) {
                if (bellmanFord(i)) {
                    flag = true;
                    sb.append("YES").append('\n');
                    break;
                }
            }

            if (!flag) sb.append("NO").append('\n');

        }

        System.out.println(sb);
    }

    private static boolean bellmanFord(int start) {
        dist[start] = 0;

        for (int i = 1; i <= V; i++) {
            boolean sign = false;
            for (Point point : list) {
                if (dist[point.from]==Integer.MAX_VALUE) continue;
                if (dist[point.to] > dist[point.from] + point.w) {
                    dist[point.to] = dist[point.from] + point.w;
                    sign= true;
                }
            }
            if (!sign) break;
        }

        for (Point point : list) {
            if (dist[point.from] == Integer.MAX_VALUE) continue;
            if (dist[point.to] > dist[point.from] + point.w) {
                return true;
            }
        }

        return false;
    }

}