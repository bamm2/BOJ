import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
    static int N, M;
    static int[][] map, visited;

    static class Point {
        int r, c, dis, broke;

        Point(int r, int c, int dis, int broke) {
            this.r = r;
            this.c = c;
            this.dis = dis; // 거리
            this.broke = broke; // 공사 횟수
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new int[N][M]; // 배열 돌면서 공사횟수 체크

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) - '0';
                visited[i][j] = Integer.MAX_VALUE;
            }
        }

        int ans=bfs(0, 0);
        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();

    }// main

    private static int bfs(int r, int c) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(r, c, 1, 0));
        visited[r][c]=0;

        while (!q.isEmpty()) {
            Point curr = q.poll();

            if(curr.r==N-1 && curr.c==M-1){
                return curr.dis;
            }

            for (int d = 0; d < 4; d++) {
                int nr = curr.r + dir[d][0];
                int nc = curr.c + dir[d][1];

                if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
                    if(visited[nr][nc]>curr.broke) {
                        if(map[nr][nc]==0) {
                            q.offer(new Point(nr,nc,curr.dis+1,curr.broke));
                            visited[nr][nc]=curr.broke;
                        }else {
                            if(curr.broke==0) {
                                q.offer(new Point(nr,nc,curr.dis+1,curr.broke+1));
                                visited[nr][nc]=curr.broke+1;
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }

}