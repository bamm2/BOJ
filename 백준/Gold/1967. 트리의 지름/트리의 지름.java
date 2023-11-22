import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static boolean[] isParentNode;
    static int N, sum, idx;

    static class Point {
        int v, w;

        public Point(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    static List<Point>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        isParentNode = new boolean[N + 1];
        list = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int parentNode = Integer.parseInt(st.nextToken());
            int childNode = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list[parentNode].add(new Point(childNode, weight));
            list[childNode].add(new Point(parentNode, weight));
            isParentNode[parentNode] = true;
        }

        bfs(1);

        bfs(idx);

        System.out.println(sum);

    }

    private static void bfs(int start) {
        boolean[] visited = new boolean[N + 1];
        Queue<Point> q = new ArrayDeque<>();
        visited[start] = true;
        q.offer(new Point(start, 0));

        while (!q.isEmpty()) {
            Point curr = q.poll();
            if (sum < curr.w) {
                idx = curr.v;
                sum = curr.w;
            }
            for (Point next : list[curr.v]) {
                if (visited[next.v]) continue;
                visited[next.v] = true;
                q.offer(new Point(next.v, curr.w + next.w));
            }
        }
    }

}