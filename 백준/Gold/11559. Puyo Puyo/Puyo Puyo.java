import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int ans;
    static char[][] map;
    static boolean[][] visited;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static List<Point> removeItems;

    static class Point {
        int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object obj) {
            Point comp = (Point) obj;
            if(this.r == comp.r && this.c == comp.c) return true;
            else return false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[12][6];

        for (int i = 0; i < 12; i++) {
            String s = br.readLine();
            for (int j = 0; j < 6; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        solve();

        System.out.println(ans);
    }

    private static void solve() {

        while (removePuyo()) {
            ans++;
            reMakeMap();
        }

    }

    private static boolean removePuyo() {
        removeItems = new ArrayList<>();
        visited = new boolean[12][6];

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if (visited[i][j] || map[i][j] == '.') continue;
                bfs(i, j);
            }
        }

        if (removeItems.size() == 0) return false;
        else return true;

    }

    private static void bfs(int r, int c) {
        List<Point> tmp = new ArrayList<>();

        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(r, c));
        tmp.add(new Point(r, c));
        visited[r][c] = true;
        char compChar = map[r][c];

        while (!q.isEmpty()) {
            Point curr = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = curr.r + dir[d][0];
                int nc = curr.c + dir[d][1];
                if (isOut(nr, nc) || visited[nr][nc] || map[nr][nc] != compChar) continue;
                visited[nr][nc] = true;
                tmp.add(new Point(nr, nc));
                q.offer(new Point(nr, nc));
            }
        }

        if (tmp.size() >= 4) {
            for (Point removeItem : tmp) {
                removeItems.add(removeItem);
            }
        }

    }

    private static void reMakeMap() {
        for (int i = 0; i < 6; i++) {
            Deque<Character> deque = new ArrayDeque<>();
            for (int j = 0; j < 12; j++) {
                if (!removeItems.contains(new Point(j,i))) deque.offerFirst(map[j][i]);
                else deque.offer('.');
            }
            int idx = 0;
            while (!deque.isEmpty()) {
                map[idx++][i] = deque.pollLast();
            }
        }
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= 12 || c >= 6;
    }
}