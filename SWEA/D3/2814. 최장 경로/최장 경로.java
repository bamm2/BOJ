import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    static final String FORMAT = "#%d %d\n";
    static List<Integer>[] list;
    static boolean[] visited;
    static int V,ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            list = new ArrayList[V + 1];

            for (int i = 0; i <= V; i++) {
                list[i] = new ArrayList<>();
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                list[from].add(to);
                list[to].add(from);
            }

            ans = 0;
            visited = new boolean[V + 1];
            for (int i = 1; i <= V; i++) {
                visited[i]=true;
                dfs(i, 1);
                visited[i] = false;
            }

            sb.append(String.format(FORMAT, tc, ans));
        }

        System.out.println(sb);

    }

    private static void dfs(int v,int cnt ) {
        ans = Math.max(ans,cnt);

        if(cnt == V) return;

        for(int next : list[v]){
            if(visited[next]) continue;
            visited[next]=true;
            dfs(next,cnt+1);
            visited[next]=false;
        }
    }

}