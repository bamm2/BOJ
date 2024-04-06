import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static int[][] map;
    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 상 우 하 좌

    static class Pos {
        int r, c, dir, hp, ap;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static Pos ari, boss, prevAriPos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j]==2) {
                    ari = new Pos(i, j);
                    prevAriPos = new Pos(i, j);
                } else if (map[i][j]==3) {
                    map[i][j] = 0;
                    boss = new Pos(i, j);
                    ariAndBossInitDir();
                }
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        ari.hp = Integer.parseInt(st.nextToken());
        ari.ap = Integer.parseInt(st.nextToken());
        boss.hp = Integer.parseInt(st.nextToken());
        boss.ap = Integer.parseInt(st.nextToken());

        gameStart();

        if (boss.hp <= 0) System.out.println("VICTORY!");
        else System.out.println("CAVELIFE...");
        br.close();

    }

    private static void ariAndBossInitDir() {
        for (int d = 0; d < 4; d++) {
            int nr = boss.r + dir[d][0];
            int nc = boss.c + dir[d][1];
            if (isOut(nr, nc)) continue;
            if (map[nr][nc]==2) {
                map[nr][nc] = 0;
                ari.dir = d;
                boss.dir = d;
                return;
            }
        }
    }

    private static void gameStart() {
        while (true) {
            boss.hp -= ari.ap; // 아리 공격
            if (boss.hp <= 0) return; // 보스 체력 체크
            ari.hp -= ariMove(); // 아리 이동
            if (ari.hp <= 0) return;
            findAttacker();
            if (ari.hp <= 0) return;
        }
    }

    private static int ariMove() {
        int minus = -1;
        int d = ari.dir;
        do {
            int nr = ari.r + dir[d][0];
            int nc = ari.c + dir[d][1];
            ++minus;
            if (isOut(nr, nc) || (nr==boss.r && nc==boss.c)) {
                d = (d + 1) % 4;
                continue;
            }
            if (map[nr][nc]==0) {
                ari.dir = d;
                prevAriPos.r = ari.r;
                prevAriPos.c = ari.c;
                ari.r = nr;
                ari.c = nc;
                return minus;
            }
            d = (d + 1) % 4;
        } while (d!=ari.dir);

        return minus + 1; // 방향 원점으로 돌아온 경우
    }

    private static void findAttacker() { // 부하 찾기
        boolean increaseFlag = false;
        int moveDistance = 1;

        int r = boss.r;
        int c = boss.c;
        int d = boss.dir;

        loop:
        do {
            int availMoveCnt = moveDistance;

            while (availMoveCnt-- > 0) {
                r += dir[d][0];
                c += dir[d][1];
                if (!isOut(r, c)) {
                    if (map[r][c]==1) {
                        attack(r, c); // 공격
                        break loop;
                    }
                }
            }
            if (increaseFlag) {  // true 일때만 거리 +1
                moveDistance++;
            }
            d = (d + 1) % 4;
            increaseFlag = !increaseFlag;
        } while (moveDistance <= 123);

        // 아리 이동 안했을 경우
        if (prevAriPos.r==boss.r && prevAriPos.c==boss.c) return;

        boss.r = prevAriPos.r;
        boss.c = prevAriPos.c;
        boss.dir = ari.dir;
        
    }

    private static void attack(int r, int c) {
        boolean[][] visited = new boolean[R][C];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r, c, boss.ap});
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            if (curr[2] <= 0) return;
            if (ari.r==curr[0] && ari.c==curr[1]) {
                ari.hp -= curr[2];
                return;
            }
            for (int d = 0; d < 4; d++) {
                int nr = curr[0] + dir[d][0];
                int nc = curr[1] + dir[d][1];
                if (isOut(nr, nc) || visited[nr][nc] || map[nr][nc]==1) continue;
                if (boss.r==nr && boss.c==nc) continue;
                visited[nr][nc] = true;
                q.offer(new int[]{nr, nc, curr[2] - 1});
            }
        }
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C;
    }

}