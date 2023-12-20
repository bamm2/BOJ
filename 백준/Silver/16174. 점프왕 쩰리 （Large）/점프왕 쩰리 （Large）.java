import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, map[][];
    static int[][] dir = {{0, 1}, {1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        visited[0][0] = true;
        q.offer(new int[]{0, 0});

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            if (curr[0]==N - 1 && curr[1]==N - 1) {
                System.out.println("HaruHaru");
                return;
            }
            int move = map[curr[0]][curr[1]];
            for (int d = 0; d < 2; d++) {
                int nr = curr[0] + dir[d][0] * move;
                int nc = curr[1] + dir[d][1] * move;
                if (isOut(nr, nc) || visited[nr][nc]) continue;
                ;
                visited[nr][nc] = true;
                q.offer(new int[]{nr, nc});
            }
        }

        System.out.println("Hing");


    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= N;
    }
}