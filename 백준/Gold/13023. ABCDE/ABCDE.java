import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int V;
    static boolean[] visited;
    static boolean flag;
    static List<Integer>[] connects;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        connects = new List[V];
        for (int i = 0; i < V; i++) {
            connects[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            connects[from].add(to);
            connects[to].add(from);
        }

        for (int i = 0; i < V; i++) {
            visited = new boolean[V];
            visited[i]=true;
            dfs(i, 0);
        }

        System.out.println(flag ? 1:0);
    }

    private static void dfs(int v, int cnt) {
        if (flag) return;
        if (cnt==4) {
            flag = true;
            return;
        }

        for (Integer next : connects[v]) {
            if (visited[next]) continue;
            visited[next] = true;
            dfs(next, cnt + 1);
            visited[next] = false;
        }
    }
}