import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static char[][] map;
    static StringBuilder sb =new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            map[i]=s.toCharArray();
        }

        solve(0, 0, N);

        System.out.println(sb);
    }

    private static void solve(int r, int c, int range) {

        if (isOk(r, c, range)) {
            sb.append(map[r][c]);
            return;
        }

        int move = range / 2;

        sb.append("(");
        solve(r, c, move);
        solve(r, c + move, move);
        solve(r + move, c, move);
        solve(r + move, c + move, move);
        sb.append(")");

    }

    private static boolean isOk(int r, int c, int range) {
        char standard = map[r][c];

        for (int i = 0; i < range; i++) {
            for (int j = 0; j < range; j++) {
                if (standard != map[r + i][c + j]) return false;
            }
        }
        return true;
    }

}