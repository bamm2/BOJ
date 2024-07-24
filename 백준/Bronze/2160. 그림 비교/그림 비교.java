import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        char[][][] map = new char[T][5][7];

        for (int k = 0; k < T; k++) {
            for (int i = 0; i < 5; i++) {
                map[k][i] = br.readLine().toCharArray();
            }
        }

        int resultA = 0;
        int resultB = 0;
        for (int i = 0; i < T - 1; i++) {
            for (int j = i + 1; j < T; j++) {
                if (isMin(map[i], map[j])) {
                    resultA = i + 1;
                    resultB = j + 1;
                }
            }
        }

        System.out.println(resultA + " " + resultB);
        br.close();
    }

    private static boolean isMin(char[][] mapA, char[][] mapB) {
        int cnt = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                if (mapA[i][j]!=mapB[i][j]) cnt++;
            }
        }
        if (cnt < min) {
            min = cnt;
            return true;
        }

        return false;
    }
}
