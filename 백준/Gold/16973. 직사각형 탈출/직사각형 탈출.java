import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C, goalR, goalC;
    static int ans = -1;
    static int[][] map;
    static int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static Square square;
    static boolean[][] visited;

    static class Square {
        int r, c, rSize, cSize, cnt;

        public Square(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }

        public Square(int r, int c, int rSize, int cSize) {
            this.r = r;
            this.c = c;
            this.rSize = rSize;
            this.cSize = cSize;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        visited = new boolean[R][C];
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        String[] s = br.readLine().split(" ");
        square = new Square(
                Integer.parseInt(s[2]) - 1, Integer.parseInt(s[3]) - 1,
                Integer.parseInt(s[0]), Integer.parseInt(s[1]));
        goalR = Integer.parseInt(s[4]) - 1;
        goalC = Integer.parseInt(s[5]) - 1;

        bfs();

        System.out.println(ans);
    }

    private static void bfs() {
        Queue<Square> q = new ArrayDeque<>();
        q.offer(new Square(square.r, square.c, 0));
        visited[square.r][square.c] = true;

        while (!q.isEmpty()) {
            Square curr = q.poll();
            if (curr.r==goalR && curr.c==goalC) {
                ans = curr.cnt;
                break;
            }
            for (int d = 0; d < 4; d++) {
                if (availMove(curr.r, curr.c, d)) {
                    int nr = curr.r + dir[d][0];
                    int nc = curr.c + dir[d][1];
                    if (visited[nr][nc]) continue;
                    visited[nr][nc] = true;
                    q.offer(new Square(nr, nc, curr.cnt + 1));
                }
            }
        }

    }

    private static boolean availMove(int r, int c, int d) {
        int nr = r, nc = c;
        int loopSize;
        switch (d) {
            case 0: // 동
                nc = c + square.cSize; // 동쪽으로 한칸 이동 가능 체크
                loopSize = square.rSize;
                while (loopSize-- > 0) {
                    if (isOut(nr, nc)) return false;
                    if (map[nr][nc]==1) return false;
                    nr += dir[2][0]; // 아래로 이동
                }
                break;
            case 1: // 서
                nc = c - 1; // 서쪽으로 한칸 이동 가능 체크
                loopSize = square.rSize;
                while (loopSize-- > 0) {
                    if (isOut(nr, nc)) return false;
                    if (map[nr][nc]==1) return false;
                    nr += dir[2][0]; // 아래로 이동
                }
                break;
            case 2: // 남
                nr = r + square.rSize; // 남쪽으로 한칸 이동 가능 체크
                loopSize = square.cSize;
                while (loopSize-- > 0) {
                    if (isOut(nr, nc)) return false;
                    if (map[nr][nc]==1) return false;
                    nc += dir[0][1]; // 동쪽으로 이동
                }
                break;
            case 3: // 북
                nr = r - 1; // 북쪽으로 한칸 이동 가능 체크
                loopSize = square.cSize;
                while (loopSize-- > 0) {
                    if (isOut(nr, nc)) return false;
                    if (map[nr][nc]==1) return false;
                    nc += dir[0][1]; // 동쪽으로 이동
                }
                break;
        }
        return true;
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C;
    }

}