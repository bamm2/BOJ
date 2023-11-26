import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static List<Point>[] list;

    static class Point {
        int v, w;

        public Point(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    static int V;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        list = new ArrayList[V + 1];
        for (int i = 0; i <= V; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < V - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[from].add(new Point(to, w));
            list[to].add(new Point(from, w));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            sb.append(bfs(from, to)).append('\n');
        }

        System.out.println(sb);

    }

    private static int bfs(int start, int dest) {
        boolean[] visited = new boolean[V + 1];
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(start, 0));
        visited[start] = true;
        while (!q.isEmpty()) {
            Point curr = q.poll();
            if (curr.v==dest) {
                return curr.w;
            }
            for (Point next : list[curr.v]) {
                if (visited[next.v]) continue;
                visited[next.v] = true;
                q.offer(new Point(next.v, curr.w + next.w));
            }
        }

        return -1;
    }
}