import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static char[][] map;
    static final char STAR = '*';

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        map = new char[N][2 * N - 1];

        recur(0, N - 1, N);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                sb.append(map[i][j] !=STAR ? ' ' : map[i][j]);
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    private static void recur(int r, int c, int n) {
        if (n==3) {
            map[r][c] = STAR;
            map[r + 1][c + 1] = map[r + 1][c - 1] = STAR;
            map[r + 2][c - 2] = map[r + 2][c - 1] = map[r + 2][c] = map[r + 2][c + 1] = map[r + 2][c + 2] = STAR;
            return;
        }

        int next = n / 2;
        recur(r, c, next);
        recur(r + next, c + next, next);
        recur(r + next, c - next, next);
    }
}