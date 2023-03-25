import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static int N , M , map[][],copy[][],ans;
    static boolean sign;
    static boolean[][] visited;
    static int[][] dir ={ {-1,0} , {1,0}, {0, 1},{0,-1}};
    static List<Point> list=new ArrayList<>();
    static Stack<Point> stack =new Stack<>();
    static class Point{
        int r,c,virus;
        Point(int r,int c,int virus){
            this.r=r;
            this.c=c;
            this.virus=virus;
        }
    }
    static Queue<Point> q;

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int count=0;
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==2) list.add(new Point(i,j,0));
            }
        }

        ans =Integer.MAX_VALUE;

            solve(0,0);
            if(ans==Integer.MAX_VALUE && !sign) ans=-1;

        System.out.println(ans);

    }

    private static void solve(int start,int cnt){
        if(cnt==M){
            copy();
            if(ans>bfs()) ans=bfs() ;
            return;
        }


        for(int i=start,size=list.size();i<size;i++){
            stack.push(list.get(i));
            solve(i+1,cnt+1);
            stack.pop();
        }
    }

    private static void copy(){
        copy=new int[N][N];

        for(Point curr: stack){
            copy[curr.r][curr.c]=-2;
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j]==2) continue;
                if(map[i][j]==1){
                    copy[i][j]=-1;
                }else{
                    copy[i][j]=0;
                }
            }
        }
    }

    private static int bfs(){
        q=new ArrayDeque<>();
        visited=new boolean[N][N];

        for(Point curr: stack){
            q.offer(curr);
            visited[curr.r][curr.c]=true;
        }

        while(!q.isEmpty()){
            Point curr=q.poll();
            for(int d=0;d<4;d++){
                int nr=curr.r+dir[d][0];
                int nc=curr.c+dir[d][1];
                if(isOut(nr,nc)) continue;
                if(visited[nr][nc]) continue;
                if(copy[nr][nc]==-1) continue;
                visited[nr][nc]=true;
                if(copy[nr][nc]<curr.virus+1) {
                    copy[nr][nc] = curr.virus + 1;
                }
                q.offer(new Point(nr,nc,curr.virus+1));
            }
        }
       return findMax();
    }

    private static int findMax(){
        int max=0;
        boolean flag=false;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(copy[i][j]>max){
                    max=copy[i][j];
                }
                if(copy[i][j]==0){
                    flag=true;
                    max=Integer.MAX_VALUE;
                }
            }
        }
        if(!flag) sign=true;
        return max;
    }



    private static boolean isOut(int r,int c){
        return r<0 || c<0 || r>=N || c>=N;
    }
}