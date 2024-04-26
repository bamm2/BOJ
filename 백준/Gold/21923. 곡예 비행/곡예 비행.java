import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        int[][] up = new int[R][C];
        int[][] down = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        up[R - 1][0] = map[R - 1][0];
        for (int c = 0; c < C; c++) {
            for (int r = R - 1; r >= 0; r--) {
                if (c==0) {
                    if (r==R - 1) continue;
                    up[r][c] = up[r + 1][c] + map[r][c];
                } else {
                    if (r==R - 1) {
                        up[r][c] = up[r][c - 1] + map[r][c];
                    } else {
                        up[r][c] = Math.max(up[r + 1][c], up[r][c - 1]) + map[r][c];
                    }
                }
            }
        }

        down[R - 1][C - 1] = map[R - 1][C - 1];
        for (int c = C - 1; c >= 0; c--) {
            for (int r = R - 1; r >= 0; r--) {
                if (c==C - 1) {
                    if (r==R - 1) continue;
                    down[r][c] = down[r + 1][c] + map[r][c];
                } else {
                    if (r==R - 1) down[r][c] = down[r][c + 1] + map[r][c];
                    else {
                        down[r][c] = Math.max(down[r + 1][c], down[r][c + 1]) + map[r][c];
                    }
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                max = Math.max(max, up[i][j] + down[i][j]);
            }
        }

        System.out.println(max);
        br.close();
    }

}
