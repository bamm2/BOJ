import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] scoreMap;
    static char[][] colorMap;
    static int[][][][] scores;
    static char[][][][] colors;
    static Point[] selected = new Point[3];
    static int[] dir = new int[3];
    static int ans;

    static class Point {
        int r, c, idx;

        public Point(int r, int c, int idx) {
            this.r = r;
            this.c = c;
            this.idx = idx;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        scores = new int[N][4][4][4];
        colors = new char[N][4][4][4];

        for (int k = 0; k < N; k++) {
            int[][] score = new int[4][4];
            char[][] color = new char[4][4];
            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < 4; j++) {
                    score[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < 4; j++) {
                    color[i][j] = st.nextToken().charAt(0);
                }
            }
            for (int t = 0; t < 4; t++) {
                scores[k][t] = turn(score, t);
                colors[k][t] = turn(color, t);
            }
        }

        ans = Integer.MIN_VALUE;
        perm(0, 0);

        System.out.println(ans);
        br.close();
    }

    private static void perm(int idx, int flag) {
        if (idx==3) {

            for (int i = 0; i < 4; i++) { // 회전
                for (int j = 0; j < 4; j++) { // 회전
                    for (int k = 0; k < 4; k++) { // 회전
                        dir[0] = i;
                        dir[1] = j;
                        dir[2] = k;
                        ans = Math.max(ans, calculate());
                    }
                }
            }
            return;
        }

        for (int r = 0; r < 2; r++) {
            for (int c = 0; c < 2; c++) {
                for (int i = 0; i < N; i++) {
                    if ((flag & (1 << i))!=0) continue;
                    selected[idx] = new Point(r, c, i);
                    perm(idx + 1, flag | (1 << i));
                }
            }
        }
    }

    private static int calculate() {
        scoreMap = new int[5][5];
        colorMap = new char[5][5];

        for (int i = 0; i < 5; i++) {
            Arrays.fill(colorMap[i], 'W');
        }

        for (int k = 0; k < 3; k++) {
            Point curr = selected[k];
            int[][] score = scores[curr.idx][dir[k]];
            char[][] color = colors[curr.idx][dir[k]];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    int mapR = curr.r + i;
                    int mapC = curr.c + j;
                    scoreMap[mapR][mapC] += score[i][j];
                    if (scoreMap[mapR][mapC] > 9) scoreMap[mapR][mapC] = 9;
                    else if (scoreMap[mapR][mapC] < 0) scoreMap[mapR][mapC] = 0;

                    if (color[i][j]!='W') colorMap[mapR][mapC] = color[i][j];
                }
            }
        }

        int R = 0, B = 0, G = 0, Y = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                switch (colorMap[i][j]) {
                    case 'R':
                        R += scoreMap[i][j];
                        break;
                    case 'B':
                        B += scoreMap[i][j];
                        break;
                    case 'G':
                        G += scoreMap[i][j];
                        break;
                    case 'Y':
                        Y += scoreMap[i][j];
                        break;
                }
            }
        }

        return 7 * R + 5 * B + 3 * G + 2 * Y;
    }

    private static int[][] turn(int[][] map, int turnCnt) {
        int[][] result = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                switch (turnCnt) {
                    case 0:
                        result[i][j] = map[i][j];
                        break;
                    case 1:
                        result[i][j] = map[3 - j][i];
                        break;
                    case 2:
                        result[i][j] = map[3 - i][3 - j];
                        break;
                    case 3:
                        result[i][j] = map[j][3 - i];
                        break;
                }
            }
        }

        return result;
    }

    private static char[][] turn(char[][] map, int turnCnt) {
        char[][] result = new char[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                switch (turnCnt) {
                    case 0:
                        result[i][j] = map[i][j];
                        break;
                    case 1:
                        result[i][j] = map[3 - j][i];
                        break;
                    case 2:
                        result[i][j] = map[3 - i][3 - j];
                        break;
                    case 3:
                        result[i][j] = map[j][3 - i];
                        break;
                }
            }
        }

        return result;
    }
}