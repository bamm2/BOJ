import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int INF = 10001;
    static int R,C,map[][],dist[][];
    static int[][] dir = { {-1,0} ,{1,0} ,{0,1},{0,-1}};
    static class Point{
        int r,c;
        Point(int r,int c){
            this.r=r;
            this.c=c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");

        C=Integer.parseInt(st.nextToken());
        R=Integer.parseInt(st.nextToken());
        map=new int[R][C];
        dist=new int[R][C];

        for(int i=0;i<R;i++){
            String s = br.readLine();
            for(int j=0;j<C;j++){
                map[i][j]=s.charAt(j)-'0';
                dist[i][j]=INF;
            }
        }

        dijkstra();

        System.out.println(dist[R-1][C-1]);
    }

    private static void dijkstra(){
        Queue<Point> q =new ArrayDeque<>();
        q.offer(new Point(0,0));
        dist[0][0]=0;

        while(!q.isEmpty()){
            Point curr = q.poll();
            for(int d=0;d<4;d++){
                int nr= curr.r+dir[d][0];
                int nc = curr.c+dir[d][1];
                if(isOut(nr,nc)) continue;
                if(dist[nr][nc]>dist[curr.r][curr.c]+map[nr][nc]){
                    dist[nr][nc]=dist[curr.r][curr.c]+map[nr][nc];
                    q.offer(new Point(nr,nc));
                }
            }
        }

    }
    private static boolean isOut(int r,int c){
        return r<0 || c<0 || r>=R || c>=C;
    }
}