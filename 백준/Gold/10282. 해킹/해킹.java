import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static List<Point>[] list;

    static class Point {
        int v, cost;

        public Point(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken()) - 1;

            list = new ArrayList[N];
            for (int i = 0; i < N; i++) {
                list[i] = new ArrayList<>();
            }

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int to = Integer.parseInt(st.nextToken()) - 1;
                int from = Integer.parseInt(st.nextToken()) - 1;
                int second = Integer.parseInt(st.nextToken());
                list[from].add(new Point(to, second));
            }


            PriorityQueue<Point> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
            pq.offer(new Point(c, 0));
            int[] dist = new int[N];
            boolean[] visited = new boolean[N];
            Arrays.fill(dist, 987654321);
            dist[c] = 0;
            visited[c] = true;

            while (!pq.isEmpty()) {
                Point curr = pq.poll();
                for (Point next : list[curr.v]) {
                    if (dist[next.v] > dist[curr.v] + next.cost) {
                        visited[next.v] = true;
                        dist[next.v] = dist[curr.v] + next.cost;
                        pq.offer(new Point(next.v, dist[next.v]));
                    }
                }
            }

            int max = 0;
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                if (visited[i]) {
                    cnt++;
                    max = Math.max(max, dist[i]);
                }
            }

            if (cnt==1) sb.append(1).append(" ").append(0).append('\n');
            else sb.append(cnt).append(" ").append(max).append('\n');
        }

        System.out.println(sb);
        br.close();
    }

}
