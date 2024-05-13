import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, max;
    static int[][] arr;
    static int[] pos;
    static boolean[] baseStatus;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N][9];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 9; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        pos = new int[9];

        perm(0, 0);

        System.out.println(max);
        br.close();
    }

    private static void perm(int idx, int flag) {
        if (idx==9) {
            max = Math.max(max, gameStart());
            return;
        }

        if (idx==3) {
            pos[3] = 0;
            perm(idx + 1, flag);
        }

        for (int i = 1; i < 9; i++) {
            if ((flag & (1 << i))!=0) continue;
            pos[idx] = i;
            perm(idx + 1, flag | (1 << i));
        }
    }

    private static int gameStart() {
        int totalScore = 0;

        int curPos = 0;
        int curRound = 0;
        while (curRound!=N) {
            int outCount = 0;
            baseStatus = new boolean[4];
            while (outCount!=3) {
                int betIdx = pos[curPos];
                int status = arr[curRound][betIdx];
                if (status==0) {
                    outCount++;
                } else if (status==1) {
                    totalScore += hit(1);
                } else if (status==2) {
                    totalScore += hit(2);
                } else if (status==3) {
                    totalScore += hit(3);
                } else { // 4
                    totalScore += hit(4);
                }
                curPos = (curPos + 1) % 9;
            }
            curRound++;
        }

        return totalScore;
    }

    private static int hit(int moveCnt) {
        int addScore = 0;
        for (int i = 3; i >= 1; i--) {
            if (baseStatus[i]) {
                baseStatus[i] = false;
                if (i + moveCnt >= 4) addScore++;
                else {
                    baseStatus[i + moveCnt] = true;
                }
            }
        }

        if (moveCnt==4) return ++addScore;

        baseStatus[moveCnt] = true;
        return addScore;
    }
}
