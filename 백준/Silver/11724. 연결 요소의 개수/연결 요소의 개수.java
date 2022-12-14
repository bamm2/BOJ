import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int V,E,map[][];
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");
        V=Integer.parseInt(st.nextToken());
        E=Integer.parseInt(st.nextToken());

        map=new int[V+1][V+1];
        visited=new boolean[V+1];

        for(int i=0;i<E;i++){
            st=new StringTokenizer(br.readLine()," ");
            int from=Integer.parseInt(st.nextToken());
            int to=Integer.parseInt(st.nextToken());
            map[from][to]=map[to][from]=1;
        }

        int ans=0;
        for(int i=1;i<=V;i++){
                if(!visited[i]){
                    ans++;
                    solve(i);
                }
        }

        System.out.println(ans);
        br.close();
    }


    private static void solve(int r){
        Queue<Integer> q= new ArrayDeque<>();
        q.offer(r);

        while(!q.isEmpty()){
            Integer curr =q.poll();
            for(int i=1;i<=V;i++){
                if(map[curr][i]!=0 && !visited[i]){
                    q.offer(i);
                    visited[i]=true;
                }
            }
        }
    }

}