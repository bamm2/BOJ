import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static boolean[][] greenMap, blueMap;
    static int score;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        greenMap = new boolean[6][4];
        blueMap = new boolean[6][4];

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int command = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            go(command, r, c);
        }

        sb.append(score).append('\n').append(count());

        System.out.println(sb);
        br.close();
    }

    private static void go(int command, int r, int c) {
        putBlock(command, r, c);
        searchRemoveElements();
        searchDownPosElements();
    }

    private static void putBlock(int command, int r, int c) {
        int curR = 0;
        int curC = 0;
        if (command==1) {
            while (curR!=6 && !greenMap[curR][c]) ++curR;
            greenMap[--curR][c] = true;
            while (curC!=6 && !blueMap[curC][r]) ++curC;
            blueMap[--curC][r] = true;
        } else if (command==2) {
            while (curR!=6 && (!greenMap[curR][c + 1] && !greenMap[curR][c])) ++curR;
            greenMap[--curR][c] = true;
            greenMap[curR][c + 1] = true;
            while (curC!=6 && !blueMap[curC][r]) ++curC;
            blueMap[--curC][r] = true;
            blueMap[curC - 1][r] = true;
        } else { // 3
            while (curR!=6 && !greenMap[curR][c]) ++curR;
            greenMap[--curR][c] = true;
            greenMap[curR - 1][c] = true;
            while (curC!=6 && (!blueMap[curC][r] && !blueMap[curC][r + 1])) ++curC;
            blueMap[--curC][r + 1] = true;
            blueMap[curC][r] = true;
        }
    }

    private static void searchRemoveElements() {
        int r = 5;
        while (true) {
            if (isRemoveAvail(r, true)) {
                score++;
                remove(r, true);
                down(r, true);
                r++;
            }
            --r;
            if (r==1) break;
        }
        r = 5;
        while (true) {
            if (isRemoveAvail(r, false)) {
                score++;
                remove(r, false);
                down(r, false);
                r++;
            }
            --r;
            if (r==1) break;
        }
    }

    private static boolean isRemoveAvail(int r, boolean isGreen) {
        for (int i = 0; i < 4; i++) {
            if (isGreen) {
                if (!greenMap[r][i]) return false;
            } else {
                if (!blueMap[r][i]) return false;
            }
        }
        return true;
    }

    private static void remove(int r, boolean isGreen) {
        for (int i = 0; i < 4; i++) {
            if (isGreen) greenMap[r][i] = false;
            else blueMap[r][i] = false;
        }
    }

    private static void down(int R, boolean isGreen) {
        for (int i = 0; i < 4; i++) {
            int r = R;
            if (isGreen) while (r > 0) greenMap[r][i] = greenMap[--r][i];
            else while (r > 0) {
                blueMap[r][i] = blueMap[--r][i];
            }
        }
        for (int i = 0; i < 4; i++) {
            if (isGreen) greenMap[0][i] = false;
            else blueMap[0][i] = false;
        }
    }

    private static void searchDownPosElements() {
        int greenCnt = 0;
        int blueCnt = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (greenMap[i][j]) {
                    greenCnt++;
                    break;
                }
            }
            for (int j = 0; j < 4; j++) {
                if (blueMap[i][j]) {
                    blueCnt++;
                    break;
                }
            }
        }
        if (greenCnt > 0) downBottom(greenCnt, true);
        if (blueCnt > 0) downBottom(blueCnt, false);
    }

    private static void downBottom(int cnt, boolean isGreen) {
        for (int i = 0; i < 4; i++) {
            int idx = 5 - cnt;
            if (isGreen) while (idx >= 0) greenMap[idx + cnt][i] = greenMap[idx--][i];
            else while (idx >= 0) blueMap[idx + cnt][i] = blueMap[idx--][i];
        }

        for (int i = 0; i < cnt; i++) {
            for (int j = 0; j < 4; j++) {
                if (isGreen) greenMap[i][j] = false;
                else blueMap[i][j] = false;
            }
        }
    }

    private static int count() {
        int cnt = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (blueMap[i][j]) cnt++;
                if (greenMap[i][j]) cnt++;
            }
        }
        return cnt;
    }

}
