import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Point implements Comparable<Point> {
        int r, c, num;

        Point(int r, int c, int num) {
            this.r = r;
            this.c = c;
            this.num = num;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.num, o.num);
        }
    }

    static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static int N, map[][];
    static PriorityQueue<Point> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j]!=0) pq.offer(new Point(i, j, map[i][j]));
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        int second = Integer.parseInt(st.nextToken());
        int findR = Integer.parseInt(st.nextToken()) - 1;
        int findC = Integer.parseInt(st.nextToken()) - 1;

        while (second-- > 0) {
            search();
        }

        System.out.println(map[findR][findC]);

    }

    private static void search() {
        List<Point> list = new ArrayList<>();

        while (!pq.isEmpty()) {
            Point curr = pq.poll();
            for (int d = 0; d < 4; d++) {
                int nr = curr.r + dir[d][0];
                int nc = curr.c + dir[d][1];
                if (isOut(nr, nc) || map[nr][nc]!=0) continue;
                map[nr][nc] = curr.num;
                list.add(new Point(nr, nc, curr.num));
            }
        }
        pq = new PriorityQueue<>(list);
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= N;
    }
}