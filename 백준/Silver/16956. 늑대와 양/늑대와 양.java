import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R,C;
    static char[][] map;
    static int[][] dir={{-1,0},{1,0},{0,1},{0,-1}};
    static class Point{
        int r,c;
        Point(int r,int c){
            this.r=r;
            this.c=c;
        }
    }
    static Queue<Point> q =new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");

        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());

        map=new char[R][C];

        boolean lamp=false;
        boolean wolf=false;

        for(int i=0;i<R;i++){
            String s=br.readLine();
            for(int j=0;j<C;j++){
                map[i][j]=s.charAt(j);
                if(map[i][j]=='S') lamp=true;
                if(map[i][j]=='W') wolf=true;
            }
        }

        boolean sign=false;
        
        loop:
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(!wolf) break loop;
                if(map[i][j]=='W'){
                    for(int d=0;d<4;d++){
                        int nr=i+dir[d][0];
                        int nc=j+dir[d][1];
                        if(isOut(nr,nc)) continue;
                        if(map[nr][nc]=='.') map[nr][nc]='D';
                        if(!lamp || map[nr][nc]=='S') {
                            sign=true;
                            break loop;
                        }
                    }
                }
            }
        }

        if(!wolf){
            loop:
            for(int i=0;i<R;i++){
                for(int j=0;j<C;j++){
                    if(map[i][j]=='.') {
                        map[i][j]='D';
                        break loop;
                    }
                }
            }
        }

        StringBuilder sb =new StringBuilder();
        if(sign) {
            sb.append(0);
        }else{
            sb.append(1).append('\n');
            for(char[] chars: map){
                for(char ch : chars){
                    sb.append(ch);
                }
                sb.append('\n');
            }
        }
        System.out.println(sb);

    }

    private static boolean isOut(int r,int c){
        return r<0 || c<0 || r>=R || c>=C ;
    }
}