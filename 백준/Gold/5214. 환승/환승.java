import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int V, K, M;
    static List<Integer>[] list;
    static boolean[] visited;

    static class Point {
        int num, cnt;

        public Point(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList[V + M + 1];
        visited = new boolean[V + M + 1];

        for (int i = 1; i <= V + M; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < K; j++) {
                int num = Integer.parseInt(st.nextToken());
                list[i + V].add(num);
                list[num].add(i + V);
            }
        }

        System.out.println(bfs());

    }

    private static int bfs() {

        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(1, 1));
        visited[1] = true;

        int count = -1;

        while (!q.isEmpty()) {
            Point curr = q.poll();
            if (curr.num==V) {
                count = curr.cnt;
                return count;
            }
            for (Integer next : list[curr.num]) {
                if (visited[next]) continue;
                ;
                visited[next] = true;
                q.offer(new Point(next, next <= V ? curr.cnt + 1:curr.cnt));
            }
        }
        return count;
    }

}