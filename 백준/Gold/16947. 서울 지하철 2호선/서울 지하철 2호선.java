import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static List<Integer>[] connection;
    static int N;
    static boolean[] visited;
    static boolean flag;
    static int[] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        ans = new int[N + 1];
        connection = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            connection[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            connection[from].add(to);
            connection[to].add(from);
        }

        for (int i = 1; i <= N; i++) {
            findCircle(i, i, i);
        }

        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue;
            ans[i] = bfs(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(ans[i]).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static int bfs(int v) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visit = new boolean[N + 1];
        q.offer(v);
        visit[v] = true;
        int cnt = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            cnt++;
            while (size-- > 0) {
                Integer curr = q.poll();
                for (Integer next : connection[curr]) {
                    if (visit[next]) continue;
                    if (visited[next]) return cnt; // 순환되는 정점에 도달했으면
                    q.offer(next);
                    visit[next] = true;
                }
            }
        }
        return -1;
    }

    private static void findCircle(int v, int prev, int start) {
        if (flag) return;
        if (visited[v] && start!=v) return; // 시작점이 순환에 포함되지 않는 경우
        if (visited[v] && start==v) { // 시작점이 순환에 포함되는 경우
            flag = true;
            return;
        }

        visited[v] = true;
        for (Integer next : connection[v]) {
            if (next==prev) continue;
            findCircle(next, v, start);
        }
        if (!flag) visited[v] = false;
    }

}