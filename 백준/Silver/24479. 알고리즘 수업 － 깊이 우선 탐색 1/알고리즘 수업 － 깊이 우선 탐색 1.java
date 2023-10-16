import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int V,E,start,depth;
    static List<Integer>[] list;
    static boolean[] visited;
    static int[] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();
        StringTokenizer st ;

        st=new StringTokenizer(br.readLine()," ");
        V=Integer.parseInt(st.nextToken());
        E=Integer.parseInt(st.nextToken());
        start=Integer.parseInt(st.nextToken());

        list=new ArrayList[V+1];
        visited=new boolean[V+1];
        ans=new int[V+1];

        for(int i=1;i<=V;i++){
            list[i]=new ArrayList<>();
        }

        for(int i=0;i<E;i++){
            st=new StringTokenizer(br.readLine()," ");
            int from= Integer.parseInt(st.nextToken());
            int to =Integer.parseInt(st.nextToken());
            list[from].add(to);
            list[to].add(from);
        }

        for(int i=1;i<=V;i++){
            Collections.sort(list[i]);
        }

        dfs(start);

        for(int i=1;i<=V;i++){
           sb.append(ans[i]).append('\n');
        }

        System.out.println(sb);
    }

    private static void dfs(int v) {
        ans[v]=++depth;
        visited[v]=true;

        for(Integer next : list[v]){
            if(visited[next]) continue;
            dfs(next);
        }

    }
}