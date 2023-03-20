import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R,C,map[][];
    static int ans = Integer.MIN_VALUE;
    static int[][] dir ={ {-1,0}, {1,0} , {0,1}, {0,-1} };
    static class Point{
        int r,c;
        Point(int r,int c ){
            this.r=r;
            this.c=c;
        }
    }
    static Queue<Point> q= new ArrayDeque<>();
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");
        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());

        map=new int[R][C];

        for(int i=0;i<R;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<C;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        solve(0);

        System.out.println(ans);

    }

    private static void initVirus(){
        q.clear();
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(map[i][j]==2) q.offer(new Point(i,j));
            }
        }
    }

    private static void solve(int cnt){

        if(cnt==3){
            if(ans<virus()){
                ans=virus();
            }
            return;
        }

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(map[i][j]==0){
                    map[i][j]=1;
                    solve(cnt+1);
                    map[i][j]=0;
                }
            }
        }

    }

    private static int virus(){
        initVirus();

        visited=new boolean[R][C];
        while(!q.isEmpty()){
            Point curr =q.poll();
            for(int d=0;d<4;d++){
                int nr=curr.r+dir[d][0];
                int nc=curr.c+dir[d][1];
                if(isOut(nr,nc)) continue;
                if(map[nr][nc]==1) continue;
                if(visited[nr][nc]) continue;
                visited[nr][nc]=true;
                q.offer(new Point(nr,nc));
            }
        }

        return Counting();
    }

    private static int Counting(){
        int count=0;
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(map[i][j]==0 && !visited[i][j]){
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean isOut(int r,int c){
        return r<0 || c<0 || r>=R || c>=C ;
    }
}