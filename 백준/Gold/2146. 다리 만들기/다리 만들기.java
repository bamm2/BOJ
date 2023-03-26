import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static int N,map[][],copy[][] ;
    static boolean[][] visited,whole;
    static int[][] dir= {{-1,0},{1,0},{0,1},{0,-1}};
    static class Point{
        int r, c,cnt;
        Point(int r,int c,int cnt){
            this.r=r;
            this.c=c;
            this.cnt=cnt;
        }
    }

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N=Integer.parseInt(br.readLine());

        map=new int[N][N];
        visited=new boolean[N][N];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<N;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        int cnt=0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j]==1 && !visited[i][j]){
                    bfs(i,j,--cnt);
                }
            }
        }

        int ans=Integer.MAX_VALUE;

        whole=new boolean[N][N];

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j] < 0 && !whole[i][j]){
                   copy();
                   int num=search(i,j,map[i][j]);
                   if(ans>num) ans=num;
                }
            }
        }

        System.out.println(ans);

    }
    private static void copy(){
        copy=new int[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                copy[i][j]=map[i][j];
            }
        }
    }

    private static int search(int r,int c, int num){
        int min=Integer.MAX_VALUE;

        Queue<Point> q=new ArrayDeque<>();
        visited=new boolean[N][N];

        q.offer(new Point(r,c,0));
        whole[r][c]=true;
        visited[r][c]=true;

        boolean sign=false;

        loop:
        while (!q.isEmpty()){
            Point curr =q.poll();
            for(int d=0;d<4;d++){
                int nr=curr.r+dir[d][0];
                int nc=curr.c+dir[d][1];
                if(isOut(nr,nc)) continue;
                if(copy[nr][nc]==num) continue;
                if(visited[nr][nc]) continue;
                if(copy[nr][nc]==0){
                    copy[nr][nc]=curr.cnt+1;
                    q.offer(new Point(nr,nc,curr.cnt+1));
                }
                if(copy[nr][nc] < 0  && copy[nr][nc]!=num){
                    min=curr.cnt;
                    sign=true;
                    break loop;
                }
            }
        }

        if(!sign) min=Integer.MAX_VALUE;

//        System.out.println("R:"+r+" , C:"+c);
//        for(int[] a : copy){
//            for(int b: a){
//                System.out.print(b+" ");
//            }
//            System.out.println();
//        }
        return min;
    }

    private static void bfs(int r,int c, int landNum){
        Queue<Point> q=new ArrayDeque<>();
        q.offer(new Point(r,c,landNum));
        visited[r][c]=true;
        map[r][c]=landNum;

        while(!q.isEmpty()){
            Point curr =q.poll();
            for(int d=0;d<4;d++){
                int nr=curr.r+dir[d][0];
                int nc=curr.c+dir[d][1];
                if(isOut(nr,nc)) continue;
                if(map[nr][nc]==0) continue;
                if(visited[nr][nc]) continue;
                visited[nr][nc]=true;
                map[nr][nc]=landNum;
                q.offer(new Point(nr,nc,landNum));
            }
        }

    }



    private static boolean isOut(int r,int c){
        return r<0 || c<0 || r>=N || c>=N ;
    }
}