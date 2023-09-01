import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int R, C, map[][], curR, curC;
    static int[][] dir = {{0, 0}, {0, 1}, {0, -1}, {-1, 0}, {1, 0}}; //0 X  1 동  2 서 3 북 4 남
    static class Dice {
        int topNum, crossNum, e, w, s, n;

        public Dice(int topNum, int crossNum, int e, int w, int s, int n) {
            this.topNum = topNum;
            this.crossNum = crossNum;
            this.e = e;
            this.w = w;
            this.s = s;
            this.n = n;
        }
    }
    static Dice dice;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        curR = Integer.parseInt(st.nextToken());
        curC = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dice =new Dice(0,0,0,0,0,0);

        st = new StringTokenizer(br.readLine(), " ");
        while (K-- > 0) {
            int direct = Integer.parseInt(st.nextToken());

            if (isOut(curR + dir[direct][0], curC + dir[direct][1])) continue; // 바깥 나간 경우

            curR += dir[direct][0];
            curC += dir[direct][1];

            movePos(direct);
        }

        System.out.println(sb);

    }

    private static void movePos(int dir) {
        if (dir == 1) { // 동
            int tmp = dice.topNum;
            dice.topNum = dice.w;
            dice.w=dice.crossNum;
            dice.crossNum =dice.e;
            dice.e=tmp;
        } else if (dir == 2) { // 서
            int tmp = dice.topNum;
            dice.topNum = dice.e;
            dice.e=dice.crossNum;
            dice.crossNum =dice.w;
            dice.w=tmp;
        } else if (dir == 3) { // 북
            int tmp = dice.topNum;
            dice.topNum = dice.s;
            dice.s=dice.crossNum;
            dice.crossNum =dice.n;
            dice.n=tmp;
        } else if (dir == 4) { // 남
            int tmp = dice.topNum;
            dice.topNum = dice.n;
            dice.n=dice.crossNum;
            dice.crossNum =dice.s;
            dice.s=tmp;
        }
        changeNum();
        sb.append(dice.topNum).append('\n');
    }

    private static void changeNum() {
        if (map[curR][curC] == 0) {
            map[curR][curC] = dice.crossNum;
        } else {
            dice.crossNum = map[curR][curC];
            map[curR][curC] = 0;
        }
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C;
    }

}