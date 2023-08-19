import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int ans ;
    static int R,C,map[][];
    static boolean[][] visited;
    static int[][] dir = { {-1,0} ,{1,0} ,{0,1},{0,-1}};
    static class Point{
        int r,c,cnt;
        Point(int r,int c,int cnt){
            this.r=r;
            this.c=c;
            this.cnt=cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");

        C=Integer.parseInt(st.nextToken());
        R=Integer.parseInt(st.nextToken());
        map=new int[R][C];
        visited=new boolean[R][C];

        for(int i=0;i<R;i++){
            String s = br.readLine();
            for(int j=0;j<C;j++){
                map[i][j]=s.charAt(j)-'0';
            }
        }

        bfs();

        System.out.println(ans);
    }

    private static void bfs(){
        Deque<Point> q =new ArrayDeque<>();
        q.offer(new Point(0,0,0));

        while(!q.isEmpty()){
            Point curr = q.poll();
            if(curr.r==R-1 && curr.c==C-1 ){
                ans=curr.cnt;
                break;
            }
            for(int d=0;d<4;d++){
                int nr= curr.r+dir[d][0];
                int nc = curr.c+dir[d][1];
                if(isOut(nr,nc) || visited[nr][nc]) continue;
                visited[nr][nc]=true;
                if(map[nr][nc]==1) q.offerLast(new Point(nr,nc, curr.cnt+1));
                else q.offerFirst(new Point(nr,nc,curr.cnt));
            }
        }

    }
    private static boolean isOut(int r,int c){
        return r<0 || c<0 || r>=R || c>=C;
    }
}