import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int H,W,ans;
    static char[][] map;
    static int[][] dir= { {-1,0},{1,0},{0,1}, {0,-1}};
    static class Point{
        int r,c,cnt;
        Point(int r, int c,int cnt){
            this.r=r;
            this.c=c;
            this.cnt=cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");
        H=Integer.parseInt(st.nextToken());
        W=Integer.parseInt(st.nextToken());

        map=new char[H][W];
        for(int i=0;i<H;i++){
            String s =br.readLine();
            for(int j=0;j<W;j++){
                map[i][j]=s.charAt(j);
            }
        }

        ans=0;
        for(int i=0;i<H;i++){
            for(int j=0;j<W;j++){
                if(map[i][j]=='L'){
                    bfs(i,j);
                }
            }
        }
        
        bw.write(ans+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
    private static void bfs(int r ,int c){
        boolean[][] visited= new boolean[H][W];
        Queue<Point> q =new ArrayDeque<>();
        q.offer(new Point(r,c,0));
        visited[r][c]=true;

        while(!q.isEmpty()){
            Point curr=q.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = curr.r + dir[d][0];
                    int nc = curr.c + dir[d][1];
                    if (isOut(nr, nc) || visited[nr][nc]) continue;
                    if (map[nr][nc] == 'W') continue;
                    visited[nr][nc] = true;
                    q.offer(new Point(nr, nc,curr.cnt+1));
                }
            ans=ans>curr.cnt?ans:curr.cnt;
        }
    }
    private static boolean isOut(int r,int c){
        if(r<0 || c<0 || r>=H || c>=W) return true;
        return false;
    }
}
