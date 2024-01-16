import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] dir = {{-1, 0, 0}, {1, 0, 0}, {0, 1, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1}}; // 상 하 좌 우 위 아래

    static class Point {
        int r, c, floor, cnt;

        public Point(int r, int c, int floor, int cnt) {
            this.r = r;
            this.c = c;
            this.floor = floor;
            this.cnt = cnt;
        }
    }

    static int[] floors = new int[5]; // 현재층의 map 의 idx
    static int[] turnTable = new int[5]; // 방향 회전 여부
    static List<int[][]> map = new ArrayList<>();
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;


        for (int k = 0; k < 5; k++) {
            int[][] tmp = new int[5][5];
            for (int i = 0; i < 5; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < 5; j++) {
                    tmp[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            map.add(tmp);
        }

        perm(0, 0);

        if (ans==Integer.MAX_VALUE) ans = -1;

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();

    }

    private static void perm(int idx, int flag) {
        if (idx==5) {
            makeTurntable(0); // 층별로 회전 시켜보면서 최소 찾기
            return;
        }

        for (int i = 0; i < 5; i++) {
            if ((flag & (1 << i))!=0) continue;
            floors[idx] = i;
            perm(idx + 1, flag | (1 << i));
        }
    }

    private static void makeTurntable(int idx) {
        if (idx==5) { // 층별로 회전 !
            int[][][] testMap = new int[5][5][5];
            for (int i = 0; i < 5; i++) {
                int floor = floors[i];
                int[][] turnedFloor = turn(map.get(floor), turnTable[i]);
                for (int a = 0; a < 5; a++) {
                    for (int b = 0; b < 5; b++) {
                        testMap[i][a][b] = turnedFloor[a][b];
                    }
                }
            }

            bfs(0, 0, 4, 4, testMap);
            bfs(0, 4, 4, 0, testMap);
            bfs(4, 0, 0, 4, testMap);
            bfs(4, 4, 0, 0, testMap);

            return;
        }

        for (int i = 0; i < 4; i++) {
            turnTable[idx] = i;
            makeTurntable(idx + 1);
        }
    }

    private static void bfs(int startR, int startC, int destR, int destC, int[][][] map) {
        if (map[startR][startC][0]==0 || map[destR][destC][4]==0) return;
        boolean[][][] visited = new boolean[5][5][5];
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(startR, startC, 0, 0));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Point curr = q.poll();
            if (curr.cnt >= ans) return;
            if (curr.r==destR && curr.c==destC && curr.floor==4) {
                ans = Math.min(ans, curr.cnt);
                return;
            }
            for (int d = 0; d < 6; d++) {
                int nr = curr.r + dir[d][0];
                int nc = curr.c + dir[d][1];
                int nf = curr.floor + dir[d][2];
                if (isOut(nr, nc, nf) || visited[nr][nc][nf] || map[nr][nc][nf]==0) continue;
                visited[nr][nc][nf] = true;
                q.offer(new Point(nr, nc, nf, curr.cnt + 1));
            }
        }
    }

    private static int[][] turn(int[][] floor, int turn) { // 0 제자리 , 1 오른쪽 한번 회전 ,2 오른쪽 2번 회전 , 3 오른쪽 3번 회전
        int[][] copy = new int[5][5];
        switch (turn) {
            case 0: // 제자리
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        copy[i][j] = floor[i][j];
                    }
                }
                return copy;
            case 1: // 시계방향 90도 회전
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        copy[i][j] = floor[4 - j][i];
                    }
                }
                return copy;
            case 2: // 시계방향 180도 회전
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        copy[i][j] = floor[4 - i][4 - j];
                    }
                }
                return copy;
            case 3: // 시계방향 270도 회전
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        copy[i][j] = floor[j][4 - i];
                    }
                }
                return copy;
        }
        return null;
    }

    private static boolean isOut(int r, int c, int floor) {
        return r < 0 || c < 0 || floor < 0 || r >= 5 || c >= 5 || floor >= 5;
    }

}