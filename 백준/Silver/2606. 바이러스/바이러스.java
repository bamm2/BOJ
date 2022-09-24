import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int V,E,ans;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

         V=Integer.parseInt(br.readLine());
         E=Integer.parseInt(br.readLine());

        map=new int[V+1][V+1];
        visited=new boolean[V+1];

        for(int i=0;i<E;i++){
                st=new StringTokenizer(br.readLine()," ");
                int a=Integer.parseInt(st.nextToken());
                int b=Integer.parseInt(st.nextToken());
                map[a][b]=map[b][a]=1;
        }

        bfs(1);
        bw.write(ans+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs(int start){
        Queue<Integer> q =new ArrayDeque<>();
       visited[start]=true;
       q.offer(start);

        while(!q.isEmpty()){
            start =q.poll();
            for(int i=1; i<V+1;i++){
                if(map[start][i]==1 && !visited[i]) {
                    q.offer(i);
                    ans++;
                    visited[i] = true;
                }
            }

        }
    }

}
