import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int R, C, map[][], ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                typeOne(i, j);
                typeTwo(i, j);
                typeThree(i, j);
                typeFour(i, j);
                typeFive(i, j);
            }
        }
        System.out.println(ans);
    }

    private static void typeOne(int r, int c) {
        int sum = 0;
        if (!isOut(r, c + 3)) {
            for (int i = 0; i < 4; i++) sum += map[r][c + i];
            if (sum > ans) ans = sum;
        }
        if (!isOut(r + 3, c)) {
            sum = 0;
            for (int i = 0; i < 4; i++) sum += map[r + i][c];
            if (sum > ans) ans = sum;
        }
    }

    private static void typeTwo(int r, int c) {
        int sum = 0;
        if (!isOut(r + 1, c + 1)) {
            sum += map[r][c] + map[r + 1][c] + map[r][c + 1] + map[r + 1][c + 1];
            if (sum > ans) ans = sum;
        }
    }

    private static void typeThree(int r, int c) {
        int sum = 0;
        if (!isOut(r - 2, c + 1)) {
            sum += map[r - 2][c] + map[r - 1][c] + map[r][c] + map[r][c + 1];
            if (sum > ans) ans = sum;
        }
        if (!isOut(r + 1, c + 2)) {
            sum = 0;
            sum += map[r][c] + map[r][c + 1] + map[r][c + 2] + map[r + 1][c];
            if (sum > ans) ans = sum;
        }
        if (!isOut(r + 2, c - 1)) {
            sum = 0;
            sum += map[r][c - 1] + map[r][c] + map[r + 1][c] + map[r + 2][c];
            if (sum > ans) ans = sum;
        }
        if (!isOut(r - 1, c - 2)) {
            sum = 0;
            sum += map[r - 1][c] + map[r][c] + map[r][c - 1] + map[r][c - 2];
            if (sum > ans) ans = sum;
        }
//  === 대칭
        if (!isOut(r - 2, c - 1)) {
            sum = 0;
            sum += map[r - 2][c] + map[r - 1][c] + map[r][c] + map[r][c - 1];
            if (sum > ans) ans = sum;
        }
        if (!isOut(r - 1, c + 2)) {
            sum = 0;
            sum += map[r][c] + map[r][c + 1] + map[r][c + 2] + map[r - 1][c];
            if (sum > ans) ans = sum;
        }
        if (!isOut(r + 2, c + 1)) {
            sum = 0;
            sum += map[r][c + 1] + map[r][c] + map[r + 1][c] + map[r + 2][c];
            if (sum > ans) ans = sum;
        }
        if (!isOut(r + 1, c - 2)) {
            sum = 0;
            sum += map[r + 1][c] + map[r][c] + map[r][c - 1] + map[r][c - 2];
            if (sum > ans) ans = sum;
        }

    }

    private static void typeFour(int r, int c) {
        int sum = 0;
        if (!isOut(r + 2, c + 1)) {
            sum += map[r][c] + map[r + 1][c] + map[r + 1][c + 1] + map[r + 2][c + 1];
            if (sum > ans) ans = sum;
        }
        if (!isOut(r + 1, c - 2)) {
            sum = 0;
            sum += map[r][c] + map[r][c - 1] + map[r + 1][c - 1] + map[r + 1][c - 2];
            if (sum > ans) ans = sum;
        }
//        === 대칭
        if (!isOut(r + 2, c - 1)) {
            sum = 0;
            sum += map[r][c] + map[r + 1][c] + map[r + 1][c - 1] + map[r + 2][c - 1];
            if (sum > ans) ans = sum;
        }
        if (!isOut(r - 1, c - 2)) {
            sum = 0;
            sum += map[r][c] + map[r][c - 1] + map[r - 1][c - 1] + map[r - 1][c - 2];
            if (sum > ans) ans = sum;
        }
    }

    private static void typeFive(int r, int c) {
        int sum = 0;
        if (!isOut(r - 1, c) && !isOut(r + 1, c + 1)) {
            sum += map[r][c] + map[r - 1][c] + map[r + 1][c] + map[r][c + 1];
            if (sum > ans) ans = sum;
        }
        if (!isOut(r, c - 1) && !isOut(r + 1, c + 1)) {
            sum = 0;
            sum += map[r][c] + map[r + 1][c] + map[r][c - 1] + map[r][c + 1];
            if (sum > ans) ans = sum;
        }
        if (!isOut(r - 1, c) && !isOut(r + 1, c - 1)) {
            sum = 0;
            sum += map[r][c] + map[r - 1][c] + map[r + 1][c] + map[r][c - 1];
            if (sum > ans) ans = sum;
        }

        if (!isOut(r - 1, c - 1) && !isOut(r, c + 1)) {
            sum = 0;
            sum += map[r][c] + map[r - 1][c] + map[r][c + 1] + map[r][c - 1];
            if (sum > ans) ans = sum;
        }

    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C;
    }

}