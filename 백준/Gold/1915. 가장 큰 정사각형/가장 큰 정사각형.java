import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] dir = {{0, -1}, {-1, 0}, {-1, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[][] map = new int[R + 1][C + 1];

        int ans = 0;
        for (int i = 1; i <= R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                if (str.charAt(j)=='1') {
                    map[i][j + 1] = 1;
                    ans = 1;
                }
            }
        }

        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (map[i][j]==0) continue;
                boolean isZero = false;
                int min = 1_001;
                for (int d = 0; d < 3; d++) {
                    int nr = i + dir[d][0];
                    int nc = j + dir[d][1];
                    if (map[nr][nc]==0) {
                        isZero = true;
                        break;
                    }
                    min = Math.min(min, map[nr][nc]);
                }
                if (isZero) continue;
                map[i][j] = min + 1;
                ans = Math.max(map[i][j], ans);
            }
        }

        System.out.println(ans * ans);
        br.close();
    }
}