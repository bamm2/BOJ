import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int V, max,idx;
    static List<Point>[] list;

    static class Point {
        int v, w;

        Point(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        V = Integer.parseInt(br.readLine());
        list = new ArrayList[V + 1];
        for (int i = 0; i <= V; i++) {
            list[i] = new ArrayList<>();
        }

        List<Integer> leafNode = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int cnt = 0;
            while (true) {
                int to = Integer.parseInt(st.nextToken());
                if (to==-1) break;
                int w = Integer.parseInt(st.nextToken());
                list[from].add(new Point(to, w));
                cnt++;
            }
            if (cnt==1) leafNode.add(from);
        }

        bfs(1);
        bfs(idx);

        System.out.println(max);

    }

    private static void bfs(Integer nodeNumber) {
        Queue<Point> q = new ArrayDeque<>();
        boolean[] visited = new boolean[V + 1];
        q.offer(new Point(nodeNumber, 0));
        visited[nodeNumber] = true;

        while (!q.isEmpty()) {
            Point curr = q.poll();
            if (max < curr.w) {
                max = curr.w;
                idx= curr.v;
            }
            for (Point next : list[curr.v]) {
                if (visited[next.v]) continue;
                visited[next.v] = true;
                q.offer(new Point(next.v, curr.w + next.w));
            }
        }
    }

}