import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static int R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");

        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());
        int[][] map = new int[R][C];

        int num = 1;
        int r = 0;
        int c = 0;
        int direct = 0;
        map[0][0] = 1;

        int y = 0; // 결과행
        int x = 0; // 결과열
        boolean flag=false;
        while (num != C * R) {
            int nr = r + dir[direct][0];
            int nc = c + dir[direct][1];
            if (isIn(nr, nc) && map[nr][nc] == 0) {
                r = nr;
                c = nc;
                map[r][c] = ++num;
                if (K == map[r][c]) {
                    y = r + 1;
                    x = c + 1;
                    flag=true;
                    break;
                }
            } else {
                r = nr - dir[direct][0];
                c = nc - dir[direct][1];
                direct = (direct + 1) % 4;
            }
        }

        if (K==1) System.out.println(1+" "+1);
        else if(!flag) System.out.println(0);
        else System.out.println(x + " " + y);

    } // main

    private static boolean isIn(int r, int c) {
        if (r >= 0 && c >= 0 && r < R && c < C) return true;
        return false;
    }
}