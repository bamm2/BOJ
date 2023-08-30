import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int R, C, T, map[][];
    static int topCleanerR, topCleanerC, btmCleanerR, btmCleanerC;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    if (topCleanerR == 0) {
                        topCleanerR = i;
                        topCleanerC = j;
                    } else {
                        btmCleanerR = i;
                        btmCleanerC = j;
                    }
                }
            }
        }

        while (T-- > 0) {
            moveDust();
            topCleanerOperating();
            btmCleanerOperating();
        }

        System.out.println(findAns());

    }

    private static void moveDust() {

        int[][] dividedMap = divideDust();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] += dividedMap[i][j];
            }
        }
    }

    private static int[][] divideDust() {
        int[][] tmp = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] != 0 && map[i][j] != -1) {
                    if (map[i][j] / 5 == 0) continue;
                    int give = map[i][j] / 5;
                    int cnt = 0;
                    for (int d = 0; d < 4; d++) {
                        int nr = i + dir[d][0];
                        int nc = j + dir[d][1];
                        if (isOut(nr, nc) || map[nr][nc] == -1) continue;
                        tmp[nr][nc] += give;
                        cnt++;
                    }
                    map[i][j] -= (cnt * give);
                }
            }
        }

        return tmp;
    }

    private static void topCleanerOperating() {
        int r = topCleanerR;
        int c = topCleanerC;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(0);
        while (++c < C) q.offer(map[r][c]);
        c--;
        while (--r >= 0) q.offer(map[r][c]);
        r++;
        while (--c >= 0) q.offer(map[r][c]);
        c++;
        while (++r < topCleanerR) q.offer(map[r][c]);
// ==================
        while (++c < C) map[r][c] = q.poll();
        c--;
        while (--r >= 0) map[r][c] = q.poll();
        r++;
        while (--c >= 0) map[r][c] = q.poll();
        c++;
        while (++r < topCleanerR) map[r][c] = q.poll();

        map[topCleanerR][topCleanerC] = -1;

    }

    private static void btmCleanerOperating() {
        int r = btmCleanerR;
        int c = btmCleanerC;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(0);
        while (++c < C) q.offer(map[r][c]);
        c--;
        while (++r < R) q.offer(map[r][c]);
        r--;
        while (--c >= 0) q.offer(map[r][c]);
        c++;
        while (--r > btmCleanerR) q.offer(map[r][c]);
// ==================
        while (++c < C) map[r][c] = q.poll();
        c--;
        while (++r < R) map[r][c] = q.poll();
        r--;
        while (--c >= 0) map[r][c] = q.poll();
        c++;
        while (--r > btmCleanerR) map[r][c] = q.poll();

        map[btmCleanerR][btmCleanerC] = -1;

    }

    private static int findAns() {
        int sum = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 0 || map[i][j] == -1) continue;
                sum += map[i][j];
            }
        }

        return sum;
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C;
    }
    
}