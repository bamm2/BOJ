import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int R, C, K, ans;
    static int[][] map, copy;
    static List<int[]> list;
    static int[] arr;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // 하 우 상 좌

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[K];

        copy = new int[R][C];
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        list = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            list.add(new int[]{r, c, s});
        }

        ans = Integer.MAX_VALUE;
        perm(0, 0);

        System.out.println(ans);
        br.close();
    }

    private static void perm(int idx, int flag) {
        if (idx==K) {
            copy();
            for (int i = 0; i < K; i++) {
                int[] curr = list.get(arr[i]);
                turn(curr[0], curr[1], curr[2]);
            }
            ans = Math.min(ans, calculate());
            return;
        }

        for (int i = 0; i < K; i++) {
            if ((flag & (1 << i))!=0) continue;
            arr[idx] = i;
            perm(idx + 1, flag | 1 << i);
        }
    }

    private static void copy() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                copy[i][j] = map[i][j];
            }
        }
    }

    private static void turn(int r, int c, int s) {
        do {
            int curR = r - s;
            int curC = c - s;
            int d = 0;
            int tmp;
            do {
                int cnt = 2 * s;
                if (d==3) --cnt;
                while (cnt!=0) {
                    int nr = curR + dir[d][0];
                    int nc = curC + dir[d][1];
                    tmp = copy[curR][curC];
                    copy[curR][curC] = copy[nr][nc];
                    copy[nr][nc] = tmp;

                    curR = nr;
                    curC = nc;
                    --cnt;
                }
                ++d;
            } while (d!=4);
        } while (--s > 0);
    }

    private static int calculate() {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < R; i++) {
            int sum = 0;
            for (int j = 0; j < C; j++) {
                sum += copy[i][j];
            }
            min = Math.min(min, sum);
        }

        return min;
    }

}