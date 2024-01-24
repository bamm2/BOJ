import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    static char[][] map;
    static int max, N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // {GG,PP} 와 같이 되어있을때는 초기 행렬에서 아무것도 변경하지 않았을 때의 경우는 2 이지만
        // 같은 색상은 변경할 수 없으므로 최대값이 1이여서 이러한 상황을 고려해서 N이 2일 경우는 제외하여 진행한다.
        if (N!=2) {
            for (int i = 0; i < N; i++) {
                max = Math.max(max, colCheck(i));
                max = Math.max(max, rowCheck(i));
                if (max==N) break;
            }
        }

        if (max!=N) {
            loop:
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i + 1!=N) { // col 체크
                        if (map[i][j]!=map[i + 1][j]) { // 아래와 변경
                            changeRow(i, j); // 아래와 위치 변경
                            max = Math.max(max, rowCheck(j)); // 세로  체크
                            max = Math.max(max, colCheck(i)); // 가로 체크
                            max = Math.max(max, colCheck(i + 1)); // 가로+1 체크
                            changeRow(i, j); // 원상태로 복구
                        }
                    }
                    if (max==N) break loop;
                    if (j + 1!=N) { // row 체크
                        if (map[i][j]!=map[i][j + 1]) { // 오른쪽과 변경
                            changeCol(i, j); // 오른쪽과 위치 변경
                            max = Math.max(max, colCheck(i)); // 가로 체크
                            max = Math.max(max, rowCheck(j)); // 세로 체크
                            max = Math.max(max, rowCheck(j + 1)); // 세로+1 체크
                            changeCol(i, j); // 원상태로 북구
                        }
                    }
                    if (max==N) break loop;
                }
            }
        }

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
        br.close();
    }

    private static void changeCol(int row, int col) {
        char tmp = map[row][col];
        map[row][col] = map[row][col + 1];
        map[row][col + 1] = tmp;
    }

    private static void changeRow(int row, int col) {
        char tmp = map[row][col];
        map[row][col] = map[row + 1][col];
        map[row + 1][col] = tmp;
    }

    private static int colCheck(int row) {
        char standard = map[row][0];
        int maxCnt = 1;
        int cnt = 1;
        for (int i = 1; i < N; i++) {
            if (standard==map[row][i]) {
                cnt++;
            } else {
                if (maxCnt < cnt) maxCnt = cnt;
                standard = map[row][i];
                cnt = 1;
            }
        }
        if (maxCnt < cnt) maxCnt = cnt;

        return maxCnt;
    }

    private static int rowCheck(int col) {
        char standard = map[0][col];
        int maxCnt = 1;
        int cnt = 1;
        for (int i = 1; i < N; i++) {
            if (standard==map[i][col]) {
                cnt++;
            } else {
                if (maxCnt < cnt) maxCnt = cnt;
                standard = map[i][col];
                cnt = 1;
            }
        }
        if (maxCnt < cnt) maxCnt = cnt;

        return maxCnt;
    }
}