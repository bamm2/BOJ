import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, map[][];

    static int[] bombCnt = {0, 0, 0, 0};
    static int[][] dir = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    static int[][] magicDir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static Queue<Integer> q = new ArrayDeque<>();

    static class Point {
        int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            magic(d, s);
            setNumbers();
            while (searchBomb()) {}
            grouping();
        }

        int ans =0;
        for(int i=1;i<=3;i++){
            ans+= i*bombCnt[i];
        }
        System.out.println(ans);

    }

    private static void magic(int d, int s) {
        int sharkR = N / 2, sharkC = N / 2;

        int power = 0;
        while (power++ != s) {
            int bombR = sharkR + magicDir[d][0] * power;
            int bombC = sharkC + magicDir[d][1] * power;
            map[bombR][bombC] = 0;
        }
    }

    private static void setNumbers() {
        int curR = N / 2, curC = N / 2;
        q = new ArrayDeque<>();

        int power = 1;
        int d = 0;
        loop:
        while (true) {
            for (int i = 0; i < 2; i++) {
                int powerTmp = 0;
                while (powerTmp++ != power) {
                    curR = curR + dir[d][0];
                    curC = curC + dir[d][1];
                    if (curR == 0 && curC == -1) break loop;
                    if (map[curR][curC] != 0) q.offer(map[curR][curC]);
                }
                d = (d + 1) % 4;
            }
            power++;
        }

        moveForward();

    }

    private static boolean searchBomb() {
        int curR = N / 2, curC = N / 2;
        Queue<Point> q = new ArrayDeque<>();
        int power = 1;
        int d = 0;

        int cur = 0;

        boolean flag = false;

        loop:
        while (true) {
            for (int i = 0; i < 2; i++) {
                int powerTmp = 0;
                while (powerTmp++ != power) {
                    curR = curR + dir[d][0];
                    curC = curC + dir[d][1];
                    if (curR == 0 && curC == -1) break loop;
                    if (map[curR][curC] != cur) {
                        if (q.size() >= 4) {
                            bombCnt[cur] += q.size();
                            flag = true;
                            while (!q.isEmpty()) {
                                Point poll = q.poll();
                                map[poll.r][poll.c] = 0;
                            }
                        } else {
                            q.clear();
                        }
                        cur = map[curR][curC];
                    }
                    q.offer(new Point(curR, curC));
                }
                d = (d + 1) % 4;
            }
            power++;
        }

        if (flag) setNumbers();
        return flag;
    }

    private static void moveForward() {
        int curR = N / 2;
        int curC = N / 2;

        int power = 1;
        int d = 0;
        loop:
        while (true) {
            for (int i = 0; i < 2; i++) {
                int powerTmp = 0;
                while (powerTmp++ != power) {
                    curR = curR + dir[d][0];
                    curC = curC + dir[d][1];
                    if (curR == 0 && curC == -1) break loop;
                    if (!q.isEmpty()) map[curR][curC] = q.poll();
                    else map[curR][curC] = 0;
                }
                d = (d + 1) % 4;
            }
            power++;
        }
    }

    private static void grouping() {
        int curR = N / 2, curC = N / 2;
        Queue<Integer> tmpQ = new ArrayDeque<>();
        int power = 1;
        int d = 0;

        int cur = 0;

        loop:
        while (true) {
            for (int i = 0; i < 2; i++) {
                int powerTmp = 0;
                while (powerTmp++ != power) {
                    curR = curR + dir[d][0];
                    curC = curC + dir[d][1];
                    if (curR == 0 && curC == -1) break loop;
                    if (map[curR][curC] != cur) {
                        if (!tmpQ.isEmpty()) {
                            q.offer(tmpQ.size());
                            q.offer(cur);
                        }
                        tmpQ.clear();
                        cur = map[curR][curC];
                    }
                    if (!tmpQ.isEmpty() && tmpQ.peek() == 0) break loop;
                    tmpQ.offer(cur);
                }
                d = (d + 1) % 4;
            }
            power++;
        }

        moveForward();
    }

}