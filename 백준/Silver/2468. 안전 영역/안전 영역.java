import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N,map[][];
    static boolean[][] visited;
    static int[][] dir ={{-1,0},{1,0},{0,1},{0,-1}};
    static class Point{
        int r,c;
        Point(int r, int c){
            this.r=r;
            this.c=c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N=Integer.parseInt(br.readLine());

        map=new int[N][N];

        int maxheight=Integer.MIN_VALUE;
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<N;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
                if(maxheight<map[i][j]) maxheight=map[i][j];
            }
        }

        int ans=0;
        for(int k=maxheight;k>0 ;k--) {
            int cnt=0;
            visited=new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(map[i][j]>k && !visited[i][j]){
                        bfs(i,j,k);
                        cnt++;
                    }
                }
            }
            if(cnt>ans) ans=cnt;
        }
        if(ans==0) System.out.println(1);
        else System.out.println(ans);
    }

    private static void bfs(int r,int c,int range){
        Queue<Point> q =new ArrayDeque<>();
        q.offer(new Point(r,c));
        visited[r][c]=true;

        while(!q.isEmpty()){
            Point curr=q.poll();

            for(int d=0;d<4;d++){
                int nr =curr.r+dir[d][0];
                int nc= curr.c+dir[d][1];
                if(isOut(nr,nc)) continue;
                if(visited[nr][nc]) continue;
                if(map[nr][nc]<=range) continue;
                visited[nr][nc]=true;
                q.offer(new Point(nr,nc));
            }
        }
    }

    private static boolean isOut(int r,int c){
        return (r<0 || c<0 || r>=N || c>=N);
    }

}