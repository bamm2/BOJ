
import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int N,M,V;
    static boolean[] visited;
    static Queue<Integer> q =new ArrayDeque<>();
    static StringBuilder sb =new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        V=Integer.parseInt(st.nextToken());

        map=new int[N+1][N+1];


        for(int i=0;i<M;i++){
           st=new StringTokenizer(br.readLine()," ");
            int num1=Integer.parseInt(st.nextToken());
            int num2=Integer.parseInt(st.nextToken());
            map[num1][num2]=map[num2][num1]=1;
        }

        visited=new boolean[N+1];
         dfs(V);
        sb.append('\n');
        visited=new boolean[N+1];
        bfs(V);
        sb.append('\n');
        System.out.println(sb);

        br.close();
    }
    private static void dfs(int start){
        visited[start]=true;
        sb.append(start+" ");

        for(int i=0;i<=N;i++){
            if(map[start][i]==1 && !visited[i]) dfs(i);
        }
    }

    private static void bfs(int start){
        q.offer(start);
        visited[start]=true;

        while(!q.isEmpty()){
            start=q.poll();
            sb.append(start+" ");
            for(int i=1;i<=N;i++){
                if(map[start][i]==1 && !visited[i]){
                    q.offer(i);
                    visited[i]=true;

                }
            }
        }
    }
}
