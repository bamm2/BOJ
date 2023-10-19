import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int R, C;
    static char[][] map;
    static int[][] dir = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    static Map<String, Integer> hm = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                solve(i, j, map[i][j] + "");
            }
        }

        for (int i = 0; i < K; i++) {
            String likeStr = br.readLine();
            if(hm.containsKey(likeStr)) sb.append(hm.get(likeStr)).append('\n');
            else sb.append(0).append('\n');
        }

        System.out.println(sb);

    }

    private static void solve(int r, int c, String findStr) {

        hm.put(findStr, hm.getOrDefault(findStr, 0) + 1);

        if (findStr.length() == 5) return;

        for (int d = 0; d < dir.length; d++) {
            int nr = (r + dir[d][0] + R) % R;
            int nc = (c + dir[d][1] + C) % C;
            solve(nr, nc, findStr + map[nr][nc]);
        }

    }
}