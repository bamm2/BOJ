import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Point {
        int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N, M, map[][],ans;
    static boolean visited[][];
    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        ans=0;

        while(true) {
            visited= new boolean[N][M];

            int chk = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] != 0 && !visited[i][j]) {
                        bfs(i, j);
                        chk++;
                    }
                }
            }
            if(chk==0) { // 나눠지지 않고 다 녹아버릴 경우
                ans=0;
                break;
            }else if(chk>=2) { // 나눠진 경우
                break;
            }
            
            melt();
            ans++;

        }

        System.out.println(ans);

//        for(int[] a : map){
//            for(int b : a){
//                System.out.print(b+" ");
//            }
//            System.out.println();
//        }
    }
    private static void melt(){
        visited=new boolean[N][M];

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j]!=0){
                    if(map[i][j]!=0)  visited[i][j]=true;
                    for(int d=0;d<4;d++){
                        int cnt=0;
                        int nr=i+dir[d][0];
                        int nc=j+dir[d][1];

                        if(isOut(nr,nc)) continue;
                        if(!visited[nr][nc] && map[nr][nc]==0) cnt++;
                        if(map[i][j]>0){
                            if(map[i][j]-cnt<0) map[i][j]=0;
                            else map[i][j]-=cnt;
                        }
                    }
                }
            }
        }
    }
    private static void bfs(int r ,int c ) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(r, c));
        visited[r][c] = true;


        while (!q.isEmpty()) {
            Point curr = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = curr.r + dir[d][0];
                int nc = curr.c + dir[d][1];

                if (isOut(nr, nc)) continue;
                if (!visited[nr][nc] && map[nr][nc] != 0) {
                    q.offer(new Point(nr, nc));
                    visited[nr][nc] = true;
                }
            }
        }
    }

    private static boolean isOut(int r, int c) {
        if (r < 0 || c < 0 || r >= N || c >= M) return true;
        return false;
    }

}