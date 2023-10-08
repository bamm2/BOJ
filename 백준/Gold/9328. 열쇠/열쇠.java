import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static char[][] map;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static int R, C, max;
    static boolean[] keys;

    static class Point {
        int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            max=0;
            st = new StringTokenizer(br.readLine(), " ");
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            map = new char[R][C];
            keys = new boolean[26];
            for (int i = 0; i < R; i++) {
                String s = br.readLine();
                for (int j = 0; j < C; j++) {
                    map[i][j] = s.charAt(j);
                }
            }
            String s = br.readLine();
            if (!s.equals("0")) {
                for (int i = 0; i < s.length(); i++) {
                    keys[s.charAt(i) - 'a'] = true;
                }
            }

            solve();
            sb.append(max).append('\n');

        }

        System.out.println(sb);

    }

    private static void solve() {
        findKey();
        searchDocument();
    }

    private static void findKey() {
        boolean[][] visited = new boolean[R][C];
        Queue<Point> q = initQueue(visited);
        Queue<Point> waitQ = new ArrayDeque<>();

        while (true) {
            while (!q.isEmpty()) {
                Point curr = q.poll();
                if(isSmall(map[curr.r][curr.c])){
                    keys[map[curr.r][curr.c]-'a']=true;
                }
                if (!avail(curr.r, curr.c)) {
                    waitQ.offer(new Point(curr.r, curr.c));
                    continue;
                }
                for (int d = 0; d < 4; d++) {
                    int nr = curr.r + dir[d][0];
                    int nc = curr.c + dir[d][1];
                    if (isOut(nr, nc) || visited[nr][nc] || map[nr][nc] == '*') continue;
                    if (isBig(map[nr][nc]) && !keys[map[nr][nc] - 'A']) waitQ.offer(new Point(nr, nc));
                    else {
                        visited[nr][nc] = true;
                        if (isSmall(map[nr][nc])) {
                            keys[map[nr][nc] - 'a'] = true;
                        }
                        q.offer(new Point(nr, nc));
                    }
                }
            }
            boolean flag = false;
            while (!waitQ.isEmpty()) {
                Point poll = waitQ.poll();
                q.offer(poll);
                if (keys[map[poll.r][poll.c] - 'A']) flag = true;
            }
            if (!flag) break;
        }
    }

    private static void searchDocument() {
        boolean[][] visited = new boolean[R][C];
        Queue<Point> q = initQueue(visited);

        while (!q.isEmpty()) {
            Point curr = q.poll();
            if(isBig(map[curr.r][curr.c]) && !keys[map[curr.r][curr.c]-'A']) continue;
            if(map[curr.r][curr.c]=='$'){
                max++;
            }
            for (int d = 0; d < 4; d++) {
                int nr = curr.r + dir[d][0];
                int nc = curr.c + dir[d][1];
                if (isOut(nr, nc) || visited[nr][nc] || map[nr][nc] == '*') continue;
                if (avail(nr, nc)) {
                    visited[nr][nc] = true;
                    q.offer(new Point(nr, nc));
                }
            }
        }
    }

    private static Queue<Point> initQueue(boolean[][] visited) {
        Queue<Point> tmp = new ArrayDeque<>();
        for (int i = 0; i < R; i++) {
            if (map[i][0]!='*') {
                visited[i][0]=true;
                tmp.offer(new Point(i, 0));
            }
            if (map[i][C-1]!='*' && !visited[i][C-1]){
                visited[i][C-1]=true;
                tmp.offer(new Point(i, C - 1));
            }
        }
        for (int i = 0; i < C; i++) {
            if (map[0][i]!='*'  && !visited[0][i] ) {
                visited[0][i]=true;
                tmp.offer(new Point(0, i));
            }
            if (map[R-1][i]!='*'  && !visited[R-1][i] ){
                visited[R-1][i]=true;
                tmp.offer(new Point(R - 1, i));
            }
        }
        return tmp;
    }

    private static boolean avail(int r, int c) {
        return (map[r][c] == '.' || map[r][c] == '$' ||
                isSmall(map[r][c]) || (isBig(map[r][c]) && keys[map[r][c] - 'A']));
    }
    private static boolean isSmall(char c){
        return 'a'<= c && c<= 'z';
    }
    private static boolean isBig (char c){
        return 'A'<=c && c<= 'Z';
    }
    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C;
    }
}
