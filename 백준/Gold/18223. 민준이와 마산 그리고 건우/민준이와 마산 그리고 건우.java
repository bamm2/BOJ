import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int V, E, G;
    static long dist[];
    static long comp, gunwoTime;
    static List<Point>[] list;
    static boolean[] visited;

    static class Point implements Comparable<Point>{
        int v;
        long w;

        Point(int v, long w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Point o) {
            return Long.compare(this.w,o.w);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());

        list = new ArrayList[V + 1];
        visited = new boolean[V + 1];
        dist = new long[V + 1];

        for (int i = 1; i <= V; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list[from].add(new Point(to, weight));
            list[to].add(new Point(from, weight));
        }

        dijkstra(1, V);
        comp = dist[V];

        dijkstra(1, G);
        gunwoTime += dist[G];
        dijkstra(G, V);
        gunwoTime += dist[V];

        if (comp == gunwoTime) System.out.println("SAVE HIM");
        else System.out.println("GOOD BYE");
    }

    private static void dijkstra(int st, int goal) {
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[st] = 0;
        visited = new boolean[V + 1];
        PriorityQueue<Point> q = new PriorityQueue<>();
        q.offer(new Point(st, 0));

        while (!q.isEmpty()) {
            Point curr = q.poll();
            if (curr.v == goal) break;

            if (visited[curr.v]) continue;
            visited[curr.v] = true;

            for (Point next : list[curr.v]) {
                if (!visited[next.v] && dist[next.v] > dist[curr.v] + next.w) {
                    dist[next.v] = dist[curr.v] + next.w;
                    q.offer(new Point(next.v, dist[next.v]));
                }
            }
        }
    }

}