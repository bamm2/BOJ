import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int T;
    static int[][] circles;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        circles = new int[T][8];

        for (int i = 0; i < T; i++) {
            char[] state = br.readLine().toCharArray();
            for (int j = 0; j < 8; j++) {
                circles[i][j] = state[j] - '0'; // 0 N극 , 1 S극
            }
        }

        int circleNumber, dir;
        int K = Integer.parseInt(br.readLine());

        while (K-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            circleNumber = Integer.parseInt(st.nextToken()) - 1;
            dir = Integer.parseInt(st.nextToken()); // 1 시계 , -1 반시계
            findTurnCandidate(circleNumber, dir);
        }

        int cnt = 0;
        for (int i = 0; i < T; i++) {
            if (circles[i][0]==1) cnt++;
        }

        System.out.println(cnt);
        br.close();
    }

    private static void findTurnCandidate(int circleNumber, int dir) {
        int[] moveDir = new int[T];
        moveDir[circleNumber] = dir;

        int standard = circleNumber;
        int d = dir;
        int right = circleNumber + 1;
        int matching = 2;
        while (right < T) {
            if (circles[standard][(matching % 8)]!=circles[right][(matching + 4) % 8]) {
                d *= -1;
                moveDir[right] = d;
                standard = right;
                right++;
            } else {
                break;
            }
        }

        standard = circleNumber;
        d = dir;
        int left = circleNumber - 1;
        matching = 6;
        while (left >= 0) {
            if (circles[standard][(matching) % 8]!=circles[left][(matching + 4) % 8]) {
                d *= -1;
                moveDir[left] = d;
                standard = left;
                left--;
            } else {
                break;
            }
        }
        turn(moveDir);
    }

    private static void turn(int[] moveDir) {
        for (int i = 0; i < moveDir.length; i++) {
            if (moveDir[i]==0) continue;
            int[] clone = circles[i].clone();
            if (moveDir[i]==1) { // 시계 방향
                for (int j = 0; j < 8; j++) {
                    circles[i][j] = clone[(j + 7) % 8];
                }
            } else { // 반시계 방향
                for (int j = 0; j < 8; j++) {
                    circles[i][j] = clone[(j + 1) % 8];
                }
            }
        }
    }

}