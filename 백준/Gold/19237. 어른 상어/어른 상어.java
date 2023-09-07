import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static Point[][] map;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};// 0,1,2,3 상하좌우
    static int[][][][] sharkDirPriority; // [n][][][] : 상어번호 [][n][][] : 상어 현재방향
    static int cnt, N, M, T;

    static class Point {
        int sharkNum, remainTime;

        Point(int sharkNum, int remainTime) {
            this.sharkNum = sharkNum;
            this.remainTime = remainTime;
        }

        public void setRemainTime(int remainTime) {
            this.remainTime = remainTime;
        }
    }

    static class Shark {
        int r, c, sharkNumber, dir;
        boolean isAlive;

        Shark(int r, int c, int sharkNumber, int dir, boolean isAlive) {
            this.r = r;
            this.c = c;
            this.sharkNumber = sharkNumber;
            this.dir = dir;
            this.isAlive = isAlive;
        }

        public void setDir(int dir) {
            this.dir = dir;
        }

        public void setAlive(boolean alive) {
            isAlive = alive;
        }
    }

    static Shark[] sharkArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new Point[N][N];
        sharkArr = new Shark[M + 1]; // 1~N번 상어
        sharkDirPriority = new int[M + 1][4][4][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 0) {
                    map[i][j] = new Point(0, 0);
                } else {
                    sharkArr[num] = new Shark(i, j, num, 0, true);
                    map[i][j] = new Point(num, T);
                }
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= M; i++) {
            sharkArr[i].setDir(Integer.parseInt(st.nextToken()) - 1);
        }

        for (int i = 0; i < M * 4; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int sharkNum = (i / 4) + 1; // 상어번호 1 ~ M번
            int curDir = i % 4; // 방향 0 ~ 3
            setSharkPriority(sharkNum, curDir, 0, Integer.parseInt(st.nextToken()) - 1);
            setSharkPriority(sharkNum, curDir, 1, Integer.parseInt(st.nextToken()) - 1);
            setSharkPriority(sharkNum, curDir, 2, Integer.parseInt(st.nextToken()) - 1);
            setSharkPriority(sharkNum, curDir, 3, Integer.parseInt(st.nextToken()) - 1);
        }

        gameStart();

        System.out.println(cnt);
    }

    // 상어 번호 , 상어가 바라 보는 방향 , 우선순위 0~3 , dir의 r,c를 매핑
    private static void setSharkPriority(int sharkNum, int direct, int p, int priority) {
        sharkDirPriority[sharkNum][direct][p][0] = dir[priority][0];
        sharkDirPriority[sharkNum][direct][p][1] = dir[priority][1];
    }

    private static void gameStart() {

        while (!isGameOver()) {
            readyMove();
            reduceSmell();
            setShark();
            cnt++;
            if (cnt > 1000) {
                cnt = -1;
                break;
            }
        }

    }

    private static void readyMove() {
        for (int i = 1; i <= M; i++) {
            Shark shark = sharkArr[i];
            if (!shark.isAlive) continue;
            boolean flag = false;

            for (int d = 0; d < 4; d++) {
                int nr = shark.r + sharkDirPriority[i][shark.dir][d][0];
                int nc = shark.c + sharkDirPriority[i][shark.dir][d][1];
                if (isOut(nr, nc)) continue;
                if (map[nr][nc].sharkNum == 0) {
                    int dir = findDir(sharkDirPriority[i][shark.dir][d][0], sharkDirPriority[i][shark.dir][d][1]);
                    sharkArr[i] = new Shark(nr, nc, i, dir, true);
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                for (int d = 0; d < 4; d++) {
                    int nr = shark.r + sharkDirPriority[i][shark.dir][d][0];
                    int nc = shark.c + sharkDirPriority[i][shark.dir][d][1];
                    if (isOut(nr, nc)) continue;
                    if (map[nr][nc].sharkNum == i) {
                        int dir = findDir(sharkDirPriority[i][shark.dir][d][0], sharkDirPriority[i][shark.dir][d][1]);
                        sharkArr[i] = new Shark(nr, nc, i, dir, true);
                        break;
                    }
                }
            }
        }
    }

    private static int findDir(int r, int c) {
        if (r == -1 && c == 0) return 0;
        else if (r == 1 && c == 0) return 1;
        else if (r == 0 && c == -1) return 2;
        else return 3;
    }

    private static void reduceSmell() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Point point = map[i][j];
                if (point.sharkNum == 0) continue;
                if (point.remainTime == 1) {
                    map[i][j] = new Point(0, 0);
                } else {
                    point.setRemainTime(point.remainTime - 1);
                }
            }
        }
    }

    private static void setShark() {
        for (int i = 1; i <= M; i++) {
            Shark shark = sharkArr[i];
            if (!shark.isAlive) continue;
            if (map[shark.r][shark.c].sharkNum != shark.sharkNumber && map[shark.r][shark.c].sharkNum != 0) {
                shark.setAlive(false);
                continue;
            }
            map[shark.r][shark.c] = new Point(shark.sharkNumber, T);
        }
    }

    private static boolean isGameOver() {
        for (int i = 2; i <= M; i++) {
            if (sharkArr[i].isAlive) return false;
        }
        return true;
    }


    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= N;
    }
}