import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int V,E,map[][],dis[],ans;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        V=Integer.parseInt(br.readLine());
        E=Integer.parseInt(br.readLine());
        map =new int[V+1][V+1];
        dis =new int[V+1];
        visited= new boolean[V+1];
        for(int i=0;i<E;i++){
            st=new StringTokenizer(br.readLine()," ");
            int from =Integer.parseInt(st.nextToken());
            int to =Integer.parseInt(st.nextToken());
            map[from][to]=map[to][from]=1;
        }

        bfs();
        System.out.println(ans);
    }

    private static void bfs(){
        Queue<Integer> q =new ArrayDeque<>();
        visited[1]=true;
        q.offer(1);

        int cnt=0;
        while(!q.isEmpty()){
            int size=q.size();
            for(int i=0;i<size;i++){
                int curr=q.poll();
                for(int j=1;j<=V;j++){
                    if(map[curr][j]==1 && !visited[j]){
                        visited[j]=true;
                        q.offer(j);
                        ans++;
                    }
                }
            }
            if(++cnt==2) break;
        }
    }
}