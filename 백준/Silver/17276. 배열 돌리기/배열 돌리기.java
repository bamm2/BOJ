import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int[][] map;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            result(d);
        }
        System.out.println(sb);
        br.close();
    }

    private static void result(int d) {
        if (d < 0) d += 360;
        int moveDistance = d / 45;
        int half = N / 2;
        int[][] result = copy();
        int[][] startPos = {{0, half}, {0, N - 1}, {half, N - 1}, {N - 1, N - 1}, {N - 1, half}, {N - 1, 0}, {half, 0}, {0, 0}};
        int[][] dir = {{1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}};

        for (int i = 0; i < 8; i++) { // 현재 위치
            int cnt = half;
            int movePos = (i + moveDistance) % 8; // 이동위치

            int originR = startPos[i][0]; // 현재 위치
            int originC = startPos[i][1];
            int moveR = startPos[movePos][0]; // 이동 위치
            int moveC = startPos[movePos][1];
            while (cnt-- > 0) {
                result[moveR][moveC] = map[originR][originC];
                moveR += dir[movePos][0];
                moveC += dir[movePos][1];
                originR += dir[i][0];
                originC += dir[i][1];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(result[i][j]).append(" ");
            }
            sb.append('\n');
        }

    }

    private static int[][] copy() {
        int[][] copy = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copy[i][j] = map[i][j];
            }
        }
        return copy;
    }
}