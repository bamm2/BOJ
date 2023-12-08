import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static int ans;
    static char[][] map;
    static final Integer MAX = 11;
    static final Integer START = 4;
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {0, 0}}; // 하 상 우 좌 스타트

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public void setR(int r) {
            this.r = r;
        }

        public void setC(int c) {
            this.c = c;
        }
    }

    static class Red extends Point {

        public Red(int r, int c) {
            super(r, c);
        }
    }

    static class Blue extends Point {

        public Blue(int r, int c) {
            super(r, c);
        }
    }

    static Red red;
    static Blue blue;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j]=='R') red = new Red(i, j);
                if (map[i][j]=='B') blue = new Blue(i, j);
            }
        }

        ans = MAX;
        solve(red, blue, 0, START);

        if (ans==MAX) ans = -1;
        System.out.println(ans);
    }

    private static void solve(Red red, Blue blue, int cnt, int d) {
        if (cnt >= ans) {
            return;
        }

        Red moveRed = new Red(red.r, red.c);
        Blue moveBlue = new Blue(blue.r, blue.c);
        boolean redGoalIn = false;
        boolean blueGoalIn = false;
        int nrPlus = dir[d][0];
        int ncPlus = dir[d][1];
        if (d!=START) {
            while (true) {
                if (!isMove(moveRed.r + nrPlus, moveRed.c + ncPlus)) break;
                if (goalIn(moveRed.r + nrPlus, moveRed.c + ncPlus)) {
                    redGoalIn = true;
                    break;
                }
                moveRed.setR(moveRed.r + nrPlus);
                moveRed.setC(moveRed.c + ncPlus);
            }
            while (true) {
                if (!isMove(moveBlue.r + nrPlus, moveBlue.c + ncPlus)) break;
                if (goalIn(moveBlue.r + nrPlus, moveBlue.c + ncPlus)) {
                    blueGoalIn = true;
                    break;
                }
                moveBlue.setR(moveBlue.r + nrPlus);
                moveBlue.setC(moveBlue.c + ncPlus);
            }
        }
        if (blueGoalIn) return; // 파란색 공이 들어간 경우
        if (redGoalIn) { // 파란색 공이 안들어가고 빨간색 공이 들어간 경우
            if (ans > cnt) ans = cnt;
            return;
        }
        if (moveRed.r==moveBlue.r && moveRed.c==moveBlue.c) {
            switch (d) {
                case 0: // 하
                    if (red.r > blue.r) moveBlue.setR(moveBlue.r - 1);
                    else moveRed.setR(moveRed.r - 1);
                    break;
                case 1: // 상
                    if (red.r > blue.r) moveRed.setR(moveRed.r + 1);
                    else moveBlue.setR(moveBlue.r + 1);
                    break;
                case 2: // 우
                    if (red.c > blue.c) moveBlue.setC(moveBlue.c - 1);
                    else moveRed.setC(moveRed.c - 1);
                    break;
                case 3: // 좌
                    if (red.c > blue.c) moveRed.setC(moveRed.c + 1);
                    else moveBlue.setC(moveBlue.c + 1);
                    break;
            }
        }


        solve(moveRed, moveBlue, cnt + 1, 0);
        solve(moveRed, moveBlue, cnt + 1, 1);
        solve(moveRed, moveBlue, cnt + 1, 2);
        solve(moveRed, moveBlue, cnt + 1, 3);

    }

    private static boolean goalIn(int r, int c) {
        return map[r][c]=='O';
    }

    private static boolean isMove(int r, int c) {
        return map[r][c]!='#';
    }

}