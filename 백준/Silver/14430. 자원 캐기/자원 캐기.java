import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[][] map = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i < C; i++) {
            map[0][i] += map[0][i - 1];
        }

        for (int i = 1; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (j==0) {
                    map[i][j] = map[i - 1][j] + map[i][j];
                } else {
                    map[i][j] = Math.max(map[i - 1][j], map[i][j - 1]) + map[i][j];
                }
            }
        }

        System.out.println(map[R - 1][C - 1]);
        br.close();
    }
}
