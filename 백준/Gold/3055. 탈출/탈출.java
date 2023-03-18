import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {

    static int R,C,chk[][];
    static int ans=Integer.MAX_VALUE;
    static boolean[][] visited1,visited2;
    static char map[][];
    static int[][] dir ={ {-1,0}, {1,0}, {0,1}, {0,-1} };
    static class Point{
        int r,c,cnt;
        Point(int r,int c,int cnt) {
            this.r = r;
            this.c = c;
            this.cnt=cnt;
        }
    }

    static Queue<Point> gosumi=new ArrayDeque<>();
    static Queue<Point> water=new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");

        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());

        map=new char[R][C];
        chk=new int[R][C];
        visited1=new boolean[R][C];
        visited2=new boolean[R][C];

        for(int i=0;i<R;i++){
            String s =br.readLine();
            for(int j=0;j<C;j++){
                map[i][j]=s.charAt(j);
                if(map[i][j]=='S') {
                    gosumi.offer(new Point(i,j,0));
                    visited1[i][j]=true;
                }
                if(map[i][j]=='*') {
                    water.offer(new Point(i,j,0));
                    visited2[i][j]=true;
                }
            }
        }

        water_bfs();
        gosumi_bfs();

        if(ans==Integer.MAX_VALUE) System.out.println("KAKTUS");
        else System.out.println(ans);

      }

      private static void water_bfs(){
        while(!water.isEmpty()){
            Point curr =water.poll();
            for(int d=0;d<4;d++){
                int nr=curr.r+dir[d][0];
                int nc=curr.c+dir[d][1];
                if(isOut(nr,nc)) continue;
                if(map[nr][nc]=='X') continue;
                if(map[nr][nc]=='D') continue;
                if(visited2[nr][nc]) continue;
                visited2[nr][nc]=true;
                chk[nr][nc]=curr.cnt+1;
                water.offer(new Point(nr,nc,curr.cnt+1));
            }
        }
      }

      private static void gosumi_bfs(){
        while(!gosumi.isEmpty()){
            Point curr=gosumi.poll();
            for(int d=0;d<4;d++){
                int nr=curr.r+dir[d][0];
                int nc=curr.c+dir[d][1];
                if(isOut(nr,nc)) continue;
                if(map[nr][nc]=='X') continue;
                if(map[nr][nc]=='D' && ans>curr.cnt+1){
                    ans=curr.cnt+1;
                    break;
                }
                if(chk[nr][nc]!=0 && chk[nr][nc]<=curr.cnt+1) continue;
                if(visited1[nr][nc]) continue;
                visited1[nr][nc]=true;
                gosumi.offer(new Point(nr,nc,curr.cnt+1));
            }
        }
      }

      private static boolean isOut(int r,int c){
        return r<0 || c<0 || r>=R || c>=C ;
      }
}