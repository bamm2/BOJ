import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 말숭이
 * 말 - > 8군데 ( 앞으로 한칸 대각선으로 한칸 ) , 장애물 뛰어넘을 수 있다
 * <p>
 * 원숭이 K번만 말처럼 이동 가능 , 아닐경우는 사방탐색
 */

public class Main {

    static int R, C, K, map[][];
    static final int MAX = Integer.MAX_VALUE;
    static int ans =MAX;
    static boolean[][][] visited;
    static int[][] monkeyDir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static int[][] horseDir = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2},
                                {1,2},{2,1},{2,-1},{1,-2}};
    static class Point{
        int r,c,cnt,validMoveCnt;
        Point(int r,int c,int cnt,int validMoveCnt){
            this.r=r;
            this.c=c;
            this.cnt=cnt;
            this.validMoveCnt=validMoveCnt;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine()); // 원숭이가 말처럼 이동할 수 있는 횟수

        st = new StringTokenizer(br.readLine(), " ");
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        visited=new boolean[K+1][R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 1) map[i][j] = -1;
                else map[i][j]=Integer.MAX_VALUE;
            }
        }

            solve();

        if(ans==MAX) System.out.println(-1);
        else System.out.println(ans);

    }

    private static void solve(){

        Queue<Point> q=new ArrayDeque<>();
        q.offer(new Point(0,0,0,K));
        visited[K][0][0]=true;

        while(!q.isEmpty()) {
            Point curr = q.poll();
            if(curr.r==R-1 && curr.c==C-1){
                ans=curr.cnt;
                break;
            }

            for(int d=0;d<4;d++){
                int nr =curr.r+monkeyDir[d][0];
                int nc =curr.c+monkeyDir[d][1];
                if(isOut(nr,nc)) continue;
                if(map[nr][nc]==-1) continue;
                if(visited[curr.validMoveCnt][nr][nc]) continue;
                    visited[curr.validMoveCnt][nr][nc] = true;
                    q.offer(new Point(nr,nc,curr.cnt+1,curr.validMoveCnt));
            }

            if(curr.validMoveCnt!=0){
                for(int d=0;d<8;d++){
                    int nr=curr.r+horseDir[d][0];
                    int nc=curr.c+horseDir[d][1];
                    if(isOut(nr,nc)) continue;
                    if(map[nr][nc]==-1) continue;
                    if(visited[curr.validMoveCnt-1][nr][nc]) continue;
                        visited[curr.validMoveCnt-1][nr][nc]=true;
                        q.offer(new Point(nr,nc,curr.cnt+1,curr.validMoveCnt-1));
                }
            }

        }
    }


    private static boolean isOut(int r,int c){
        return r<0 || c<0 || r>=R || c>=C ;
    }
}