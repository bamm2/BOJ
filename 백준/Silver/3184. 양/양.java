import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] dir = {{-1,0} ,{1,0},{0,1},{0,-1}};
    static int R,C,map[][],oNum,vNum;
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
        StringTokenizer st=new StringTokenizer(br.readLine()," ");

        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());

        map=new int[R][C];
        visited=new boolean[R][C];

        oNum=0;
        vNum=0;
        for(int i=0;i<R;i++){
            String s= br.readLine();
            for(int j=0;j<C;j++){
                map[i][j]=s.charAt(j);
                if(map[i][j]=='o') oNum++;
                if(map[i][j]=='v') vNum++;
            }
        }



        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if( (map[i][j]=='.' || map[i][j]=='o' || map[i][j]=='v') && !visited[i][j]){
                    bfs(i,j);
                }
            }
        }

        System.out.println(oNum+ " "+ vNum);

    }

    private static void bfs(int r,int c){
        Queue<Point> q=new ArrayDeque<>();
        int sheep=0;
        int wolf=0;
        if(map[r][c]=='o') sheep++;
        if(map[r][c]=='v') wolf++;
        q.offer(new Point(r,c));
        visited[r][c]=true;

        while(!q.isEmpty()){
            Point curr =q.poll();
            for(int d=0;d<4;d++) {
                int nr = curr.r + dir[d][0];
                int nc = curr.c + dir[d][1];
                if(isOut(nr,nc)) continue;
                if(visited[nr][nc]) continue;
                if(map[nr][nc]=='#') continue;

                if(map[nr][nc]=='v') wolf++;
                if(map[nr][nc]=='o') sheep++;
                visited[nr][nc]=true;
                q.offer(new Point(nr,nc));
            }
        }
        if(sheep >0 && wolf>0 && sheep<=wolf) oNum-=sheep;
        if(sheep >0 && wolf>0 && sheep>wolf ) vNum-=wolf;
    }

    private static boolean isOut(int r,int c){
        return r<0 || c<0 || r>=R || c>=C;
    }
}