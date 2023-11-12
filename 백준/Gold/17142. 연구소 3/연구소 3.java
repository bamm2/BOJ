import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, map[][], M;
    static int minTime = Integer.MAX_VALUE;
    static Virus[] activeVirus;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    static class Virus {
        int r, c;

        public Virus(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static List<Virus> virusList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        activeVirus = new Virus[M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j]==2) virusList.add(new Virus(i, j));
            }
        }

        comb(0, 0);
        if (minTime==Integer.MAX_VALUE) minTime = -1;

        System.out.println(minTime);
    }

    private static void comb(int start, int idx) {
        if (idx==M) {
            int[][] copyMap = copy();
            minTime = Math.min(bfs(copyMap, activeVirus), minTime);
            return;
        }

        for (int i = start; i < virusList.size(); i++) {
            activeVirus[idx] = virusList.get(i);
            comb(i + 1, idx + 1);
        }
    }

    private static int bfs(int[][] map, Virus[] activeVirus) {
        int time = 0;
        Queue<Virus> q = new ArrayDeque<>();
        for (int i = 0; i < activeVirus.length; i++) {
            Virus curr = activeVirus[i];
            map[curr.r][curr.c] = 1;
            q.offer(curr);
        }

        while (!q.isEmpty()) {
            int size = q.size();
            boolean isSpread = false;
            while (size-- > 0) {
                Virus curr = q.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = curr.r + dir[d][0];
                    int nc = curr.c + dir[d][1];
                    if (isOut(nr, nc) || map[nr][nc]==1) continue;
                    if(map[nr][nc]==0) {
                        isSpread = true;
                    }
                    map[nr][nc] = 1;
                    q.offer(new Virus(nr, nc));
                }
            }
            if (isSpread) time++;

            boolean flag = allClear(map);
            if (flag && !isSpread) return time;
            else if(!flag && !isSpread) time++;
        }

        return Integer.MAX_VALUE;
    }

    private static boolean allClear(int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j]==0) return false;
            }
        }
        return true;
    }

    private static int[][] copy() {
        int[][] copy = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copy[i][j] = map[i][j];
            }
        }
        return copy;
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= N;
    }
}
