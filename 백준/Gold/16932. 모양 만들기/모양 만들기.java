import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C, map[][], ans, idx;
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static boolean[][] visited;

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static List<Integer> sizeList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j]==1) map[i][j] = -1;
            }
        }

        idx = 1;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j]==0) {
                    ans = Math.max(ans, getCount(i, j));
                }
            }
        }

        System.out.println(ans);

    }

    private static int getCount(int r, int c) {
        int count = 1;
        HashSet<Integer> hs =new HashSet<>();
        for (int d = 0; d < 4; d++) {
            int nr = r + dir[d][0];
            int nc = c + dir[d][1];
            if (isOut(nr, nc) || map[nr][nc]==0) continue;
            if (map[nr][nc]==-1) {
                hs.add(idx);
                count+=goMemo(nr, nc);
            } else {
                if(hs.contains(map[nr][nc])) continue;
                hs.add(map[nr][nc]);
                count+=sizeList.get(map[nr][nc]-1);
            }
        }

        return count;
    }

    private static int goMemo(int r, int c) {
        map[r][c] = idx;
        Queue<Point> q = new ArrayDeque<>();
        visited[r][c] = true;
        q.offer(new Point(r, c));
        int count = 1;
        while (!q.isEmpty()) {
            Point curr = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = curr.r + dir[d][0];
                int nc = curr.c + dir[d][1];
                if (isOut(nr, nc) || visited[nr][nc] || map[nr][nc]!=-1) continue;
                visited[nr][nc] = true;
                map[nr][nc] = idx;
                count++;
                q.offer(new Point(nr, nc));
            }
        }

        sizeList.add(count);

        idx++;
        return count;
    }


    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C;
    }

}
