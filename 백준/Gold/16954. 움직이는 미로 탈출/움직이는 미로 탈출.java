import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class Main {

    static char[][] map;
    static int[][] dir = {{0, 0}, {-1, 0}, {1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    static int ans;
    static boolean[][] visited ;
    static Queue<Point> q = new ArrayDeque<>();
    static Deque<Point> wall = new ArrayDeque<>();

    static class Point {
        int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[8][8];
        visited = new boolean[8][8];

        for (int i = 0; i < 8; i++) {
            String s = br.readLine();
            for (int j = 0; j < 8; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == '#') wall.offer(new Point(i, j));
            }
        }

        q.offer(new Point(7, 0));
        solve();

        System.out.println(ans);

    }

    private static void solve() {
        while ( q.size() != 0 && ans != 1) {
            move();
            downWall();
            if(wall.size()==0){
                ans=1;
                break;
            }
        }
    }

    private static void move() {
        int size = q.size();
        while (size-- > 0) {
            Point curr = q.poll();
            if (curr.r == 0 && curr.c == 7) {
                ans = 1;
                break;
            }
            for (int d = 0; d < 9; d++) {
                int nr = curr.r + dir[d][0];
                int nc = curr.c + dir[d][1];
                if (isOut(nr, nc)) continue;
                if (map[nr][nc] == '#') continue;
                if (visited[nr][nc]) continue;
                visited[nr][nc] = true;
                q.offer(new Point(nr, nc));
                map[nr][nc]='x';
                visited[nr][nc]=false;

            }
        }
    }

    private static void downWall() {

        int size = wall.size();
        while (size-- > 0) {
            Point curr = wall.pollLast();
            int moveR = curr.r + 1;
            int moveC = curr.c;
            map[curr.r][curr.c] = '.';
            if (moveR == 8) continue;
            wall.offerFirst(new Point(moveR, moveC));
            map[moveR][moveC] = '#';

            int humanPos = q.size();
            while (humanPos-- > 0) {
                Point human = q.poll();
                if (human.r == moveR && human.c == moveC) continue;
                q.offer(human);
            }
        }
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= 8 || c >= 8;
    }
}