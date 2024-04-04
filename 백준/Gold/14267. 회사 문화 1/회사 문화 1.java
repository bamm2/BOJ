import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static List<Integer>[] connections;
    static int V;
    static int[] costs;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        visited = new boolean[V + 1];
        costs = new int[V + 1];
        connections = new ArrayList[V + 1];
        for (int i = 0; i <= V; i++) {
            connections[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= V; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num!=-1) connections[num].add(i);
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            costs[v] += cost;
        }

        for (int i = 1; i <= V; i++) {
            if (!visited[i]) solve(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            sb.append(costs[i]).append(" ");
        }

        System.out.println(sb);
        br.close();
    }

    private static void solve(int v) {
        visited[v] = true;

        for (Integer next : connections[v]) {
            if (visited[next]) continue;
            visited[next] = true;
            costs[next] += costs[v];
            solve(next);
        }
    }

}