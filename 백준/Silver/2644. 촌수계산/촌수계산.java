import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    static int N,from,to,ans;
    static int[][] map;
    static boolean[] visited;
    static int[][] dir={{-1,0},{0,1},{1,0},{0,-1}};
    static class Point{
        int r,cnt;
        Point(int r,int cnt){
            this.r=r;
            this.cnt=cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N=Integer.parseInt(br.readLine());

        st=new StringTokenizer(br.readLine()," ");
        from=Integer.parseInt(st.nextToken());
        to=Integer.parseInt(st.nextToken());

        int E=Integer.parseInt(br.readLine());
        map=new int[N+1][N+1];
        visited=new boolean[N+1];

        for(int i=0;i<E;i++){
            st=new StringTokenizer(br.readLine()," ");
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            map[a][b]=map[b][a]=1;
        }

        bfs(from,to);
        br.close();
    }
    private static void bfs(int r ,int target){
        boolean flag=false;
        Queue<Point> q =new ArrayDeque<>();
        q.offer(new Point(r,0));
        visited[r]=true;

        while(!q.isEmpty()){
            Point curr=q.poll();
            int chk=curr.r;
            if(chk==target){
                flag=true;
                System.out.println(curr.cnt);
                break;
            }
            for(int i=1;i<=N;i++){
                if(!visited[i] && map[chk][i]==1){
                    visited[i]=true;
                    q.offer(new Point(i,curr.cnt+1));
                }
            }
        }
        if(!flag) System.out.println(-1);
    }

}
