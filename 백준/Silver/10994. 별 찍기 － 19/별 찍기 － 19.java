import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int size;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        size = 4 * N - 3;
        map = new char[size][size];
        paintingStart(0, 0, size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append(map[i][j]!='*' ? ' ':'*');
            }
            sb.append('\n');
        }
        System.out.println(sb);
        br.close();
    }

    private static void paintingStart(int r, int c, int range) {

        if (range < 0) return;

        for (int i = c; i < c + range; i++) {
            map[r][i] = '*';
            map[r + range - 1][i] = '*';
        }
        for (int i = r + 1; i < r + range - 1; i++) {
            map[i][c] = '*';
            map[i][c + range - 1] = '*';
        }

        paintingStart(r + 2, c + 2, range - 4);
    }

}