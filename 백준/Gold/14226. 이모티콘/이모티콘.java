import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    static int N;
    static boolean[][] visited;

    static class Point {
        int v, cnt, saved;

        public Point(int v, int cnt, int saved) {
            this.v = v;
            this.cnt = cnt;
            this.saved = saved;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        visited = new boolean[2 * N + 1][2 * N + 1]; // 위치 , 현재 클립보드 값 

        int ans = bfs(); // 2<=N<=1000

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }

    private static int bfs() {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(1, 0, 0));

        while (!q.isEmpty()) {
            Point curr = q.poll();
            if (curr.v==N) {
                return curr.cnt;
            }
            int[] np = {curr.v, curr.v + curr.saved, curr.v - 1};
            for (int d = 0; d < 3; d++) {
                int movedPos = np[d]; // 이동한 위치
                if (isOut(movedPos)) continue;
                switch (d) {
                    case 0: // 복사
                        q.offer(new Point(curr.v, curr.cnt + 1, curr.v));
                        break;
                    case 1: // 클립보드에서 붙여넣기
                    case 2: // 하나 삭제하기
                        if (visited[movedPos][curr.saved]) continue;
                        visited[movedPos][curr.saved] = true;
                        q.offer(new Point(movedPos, curr.cnt + 1, curr.saved));
                        break;
                }
            }
        }
        return -1;
    }

    private static boolean isOut(int v) { // 커져있다가 줄이는 경우가 최선일 수 있어 2배 범위까지 체크
        return v < 0 || v > 2 * N;
    }
}