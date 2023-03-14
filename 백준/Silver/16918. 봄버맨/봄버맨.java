import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    폭탄 3초 후에 폭발
    폭발시 상하좌우 폭발 ( 인접해 있던 폭탄은 그냥 폭발 )

    봄버맨 폭탄 설치 -> 다음 1초 아무것도 안함
                -> 다음 1초 폭탄 설치 되어 있지 않은 모든칸에 폭탄 설치
                 -> 1초가 지난 후 3초전에 설치된 폭탄 모두 폭발

 */
public class Main {
    static int R,C,second;
    static char[][] map;
    static int[][] dir = {{-1,0},{1,0},{0,1},{0,-1}};
    static class Point{
        int r,c;

        Point(int r ,int c){
            this.r=r;
            this.c=c;
        }
    }
    static boolean[][] visited;
    static Queue<Point> q=new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine()," ");

        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());
        second=Integer.parseInt(st.nextToken());

        map=new char[R][C];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        char[][] allBombMap=new char[R][C];
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                allBombMap[i][j]='O';
            }
        }

        for(int k=2;k<=second;k++) {
            if(k==0 || k==1) continue;
            if(k%2==0) continue;
            q.clear();
            visited=new boolean[R][C];
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] == 'O') q.offer(new Point(i, j));
                }
            }
            bfs();
            setMap();
        }
        StringBuilder sb =new StringBuilder();
        if(second%2==0) {
            for(char[] cArr:allBombMap){
                for(char c :cArr){
                    sb.append(c);
                }
                sb.append('\n');
            }
        }else{
            for(char[] cArr:map){
                for( char c : cArr){
                    sb.append(c);
                }
                sb.append('\n');
            }
        }
        System.out.println(sb.toString().trim());
    }


    private static void bfs(){
        while(!q.isEmpty()){
            Point curr =q.poll();
            visited[curr.r][curr.c]=true;
            map[curr.r][curr.c]='.';
            for(int d=0;d<4;d++){
                int nr =curr.r+dir[d][0];
                int nc =curr.c+dir[d][1];
                if(isOut(nr,nc)) continue;
                if(visited[nr][nc]) continue;
                visited[nr][nc]=true;
                map[nr][nc]='.';
            }
        }
    }
    private static void setMap(){
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(!visited[i][j]) map[i][j]='O';
            }
        }
    }

    private static boolean isOut(int r,int c){
        return r<0 || c<0 || r>=R || c>= C ;
    }

}