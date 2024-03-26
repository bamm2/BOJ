import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C, result;
    static int[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 동 남 서 북

    static class Dice {
        int e, w, s, n, b, t;

        public Dice() {
            this.e = 3;
            this.w = 4;
            this.s = 5;
            this.n = 2;
            this.b = 6;
            this.t = 1;
        }
    }

    static boolean[][] visited;
    static int[] pos;
    static Dice dice = new Dice();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        pos = new int[]{0, 0, 0};

        while (K-- > 0) {
            move();
            result += getPoint();
            turn();
        }

        System.out.println(result);
        br.close();
    }

    private static void move() {
        int d = pos[2];
        int nr = pos[0] + dir[d][0];
        int nc = pos[1] + dir[d][1];
        if (isOut(nr, nc)) {
            d = (d + 2) % 4;
            nr = pos[0] + dir[d][0];
            nc = pos[1] + dir[d][1];
        }
        setDicePos(d);
        pos = new int[]{nr, nc, d};
    }

    private static void setDicePos(int d) {
        int tmp = dice.t;
        switch (d) {
            case 0: // 동
                dice.t = dice.w;
                dice.w = dice.b;
                dice.b = dice.e;
                dice.e = tmp;
                return;
            case 1: // 남
                dice.t = dice.n;
                dice.n = dice.b;
                dice.b = dice.s;
                dice.s = tmp;
                return;
            case 2: // 서
                dice.t = dice.e;
                dice.e = dice.b;
                dice.b = dice.w;
                dice.w = tmp;
                return;
            case 3: // 북
                dice.t = dice.s;
                dice.s = dice.b;
                dice.b = dice.n;
                dice.n = tmp;
        }
    }

    private static int getPoint() {
        int number = map[pos[0]][pos[1]];
        visited = new boolean[R][C];
        visited[pos[0]][pos[1]] = true;
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{pos[0], pos[1]});

        int cnt = 1;
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = curr[0] + dir[d][0];
                int nc = curr[1] + dir[d][1];
                if (isOut(nr, nc) || visited[nr][nc] || map[nr][nc]!=number) continue;
                visited[nr][nc] = true;
                q.offer(new int[]{nr, nc});
                cnt++;
            }
        }

        return number * cnt;
    }

    private static void turn() {
        int mapNumber = map[pos[0]][pos[1]];
        int diceNumber = dice.b;

        if (mapNumber==diceNumber) return;
        if (mapNumber > diceNumber) { // 반시계 90도
            pos[2] = (pos[2] + 4 - 1) % 4;
            return;
        }
        //시계 90도
        pos[2] = (pos[2] + 1) % 4;
    }

    public static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C;
    }
}