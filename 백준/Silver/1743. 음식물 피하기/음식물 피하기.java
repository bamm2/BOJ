import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] dir = { {-1,0}, {1,0} ,{0,1} ,{0,-1}};
    static int R,C,map[][],ans;
    static boolean[][] visited;
    static class Point{
        int r,c;
        Point(int r,int c){
            this.r=r;
            this.c=c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");
        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());
        int K=Integer.parseInt(st.nextToken());

        map=new int[R][C];
        visited=new boolean[R][C];

        for(int i=0;i<K;i++){
            st=new StringTokenizer(br.readLine()," ");
            int r =Integer.parseInt(st.nextToken());
            int c =Integer.parseInt(st.nextToken());
            map[r-1][c-1]=1;
        }

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(!visited[i][j] && map[i][j]!=0){
                    bfs(i,j);
                }
            }
        }

        System.out.println(ans);

    }

    private static void bfs(int r,int c){
        Queue<Point> q =new ArrayDeque<>();
        q.offer(new Point(r,c));
        visited[r][c]=true;
        int cnt =1 ;

        while(!q.isEmpty()){
            Point curr =q.poll();
            for(int d=0;d<4;d++){
                int nr =curr.r+dir[d][0];
                int nc =curr.c+dir[d][1];
                if(isOut(nr,nc) || visited[nr][nc]) continue;
                if(map[nr][nc] == 0 ) continue;
                visited[nr][nc]=true;
                cnt++;
                q.offer(new Point(nr,nc));
            }
        }
        if(ans<cnt) ans =cnt;
    }

    private static boolean isOut(int r,int c){
        return r<0 || c<0 || r>=R || c>=C;
    }
}