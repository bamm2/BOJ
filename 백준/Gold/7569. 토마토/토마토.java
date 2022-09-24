import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dh = {0, 0, 0, 0, 1, -1};
    static int[] dr = {-1, 0, 1, 0, 0, 0};
    static int[] dc = {0, 1, 0, -1, 0, 0};
    static int[][][] map;
    static Queue<Point> q;
    static int N,M,H;

    static class Point {
        int h, r, c;

        Point(int h, int r, int c) {
            this.h = h;
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
         M = Integer.parseInt(st.nextToken());
         N = Integer.parseInt(st.nextToken());
         H = Integer.parseInt(st.nextToken());

        q = new ArrayDeque<>();
        map = new int[H][N][M];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine()," ");
                for (int k = 0; k < M; k++) {
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                    if (map[i][j][k] == 1) q.offer(new Point(i, j, k));
                }
            }
        }
        bw.write(bfs()+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int bfs() {
        while (!q.isEmpty()){
            Point curr=q.poll();
            for(int d=0;d<6;d++){
                int nh=curr.h+dh[d];
                int nr=curr.r+dr[d];
                int nc=curr.c+dc[d];
                if(nr>=0 && nc>=0 && nh>=0 && nr<N && nc<M && nh<H){
                    if(map[nh][nr][nc]==0){
                        q.offer(new Point(nh,nr,nc));
                        map[nh][nr][nc]=map[curr.h][curr.r][curr.c]+1;
                    }
                }
            }
        }

      int ans =Integer.MIN_VALUE;
      for(int i=0;i<H;i++){
          for(int j=0;j<N;j++){
              for(int k=0;k<M;k++){
                  if(map[i][j][k]==0) return -1;
                  ans=ans>map[i][j][k]?ans:map[i][j][k];
              }
          }
      }
      if(ans==1) return 0;
      else return ans-1;
    }
}
