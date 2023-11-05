import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우
    static boolean[][] airVisited, visited;
    static int map[][], R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map=new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;

        while (countCheese()!=0) {
            time++;
            innerAirCheck();
            removeCheese();
        }

        System.out.println(time);

    }

    private static void removeCheese() {
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!visited[i][j] && map[i][j]==1) {
                    findRemoveCheese(i, j);
                }
            }
        }
    }

    private static void findRemoveCheese(int r, int c) {
        Queue<Point> q =new ArrayDeque<>();
        q.offer(new Point(r,c));
        visited[r][c]=true;

        while (!q.isEmpty()){
            Point curr = q.poll();
            int cnt = 0 ;
            for(int d=0;d<4;d++){
                int nr = curr.r+dir[d][0];
                int nc =curr.c+dir[d][1];
                if(isOut(nr,nc) || visited[nr][nc]) continue;
                if(airVisited[nr][nc] && map[nr][nc]==0) cnt++;
                if(map[nr][nc]==1){
                    visited[nr][nc]=true;
                    q.offer(new Point(nr,nc));
                }
            }
            if(cnt>=2) map[curr.r][curr.c]=0;
        }
    }

    private static void innerAirCheck() {
        airVisited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            if (!airVisited[i][0] && map[i][0]==0) {
                mapSearch(i, 0);
            }
            if (!airVisited[i][C - 1] && map[i][C - 1]==0) {
                mapSearch(i, C - 1);
            }
        }
        for (int i = 0; i < C; i++) {
            if (!airVisited[0][i] && map[0][i]==0) {
                mapSearch(0, i);
            }
            if (!airVisited[R - 1][i] && map[R - 1][i]==0) {
                mapSearch(R - 1, i);
            }
        }
    }

    private static void mapSearch(int r, int c) {
        airVisited[r][c] = true;
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(r, c));

        while (!q.isEmpty()) {
            Point curr = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = curr.r + dir[d][0];
                int nc = curr.c + dir[d][1];
                if (isOut(nr, nc) || airVisited[nr][nc] || map[nr][nc]==1) continue;
                airVisited[nr][nc] = true;
                q.offer(new Point(nr, nc));
            }
        }
    }

    private static int countCheese() {
        int count = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j]==1) count++;
            }
        }
        return count;
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C;
    }
}