import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int N, min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][2];

        min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            map[i][0] = Integer.parseInt(st.nextToken());
            map[i][1] = Integer.parseInt(st.nextToken());
            min = Math.min(min, Math.abs(map[i][0] - map[i][1]));
        }

        for (int i = 2; i <= N; i++) {
            solve(0, 0, 1, 0, i);
        }

        System.out.println(min);
        br.close();
    }

    private static void solve(int cnt, int start, int sSum, int bSum, int goal) {
        if (goal==cnt) {
            min = Math.min(min, Math.abs(sSum - bSum));
            return;
        }

        for (int i = start; i < N; i++) {
            solve(cnt + 1, i + 1, map[i][0] * sSum, map[i][1] + bSum, goal);
        }
    }
}