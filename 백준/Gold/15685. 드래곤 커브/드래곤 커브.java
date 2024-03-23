import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static boolean[][] visited = new boolean[101][101];
    static int[][] dir = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}}; // 우 상 좌 하
    static List<Integer> directions;
    static int g;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int c = Integer.parseInt(st.nextToken()); // 0 ~ 100
            int r = Integer.parseInt(st.nextToken()); // 0 ~ 100
            int d = Integer.parseInt(st.nextToken()); // 우 상 좌 하
            g = Integer.parseInt(st.nextToken()); // 0 ~ 10
            directions = new ArrayList<>();
            directions.add(d);
            makeDirections(1);
            drawing(r, c);
        }

        System.out.println(result());
    }

    private static void makeDirections(int generation) {
        if (generation > g) return;
        for (int i = directions.size() - 1; i >= 0; i--) {
            directions.add((directions.get(i) + 1) % 4);
        }
        makeDirections(generation + 1);
    }

    private static void drawing(int r, int c) {
        visited[r][c] = true;

        int curR = r;
        int curC = c;

        for (Integer d : directions) {
            curR += dir[d][0];
            curC += dir[d][1];
            visited[curR][curC] = true;
        }

    }

    private static int result() {
        int result = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (isSatisfied(i, j)) result++;
            }
        }
        return result;
    }

    private static boolean isSatisfied(int r, int c) {
        return visited[r][c] && visited[r][c + 1] && visited[r + 1][c] && visited[r + 1][c + 1];
    }
}