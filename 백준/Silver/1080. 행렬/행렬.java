import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static int[][] map, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        answer = new int[R][C];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                answer[i][j] = s.charAt(j) - '0';
            }
        }

        int ans = 0;
        loop:
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j]==answer[i][j]) continue;
                if (!turn(i, j)) {
                    ans = -1;
                    break loop;
                }
                ans++;
            }
        }

        System.out.println(ans);
        br.close();
    }

    private static boolean turn(int r, int c) {
        if (isOut(r + 2, c + 2)) return false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[i + r][j + c]==1) {
                    map[i + r][j + c] = 0;
                } else {
                    map[i + r][j + c] = 1;
                }
            }
        }
        return true;
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C;
    }
}