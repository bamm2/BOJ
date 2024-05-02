import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static List<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        list = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        int[] sum = new int[N + 1];
        int[] times = new int[N + 1];
        int[] inOrders = new int[N + 1];

        for (int v = 1; v <= N; v++) {
            st = new StringTokenizer(br.readLine(), " ");
            int time = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            times[v] = time;
            inOrders[v] = M;
            for (int i = 0; i < M; i++) {
                int parent = Integer.parseInt(st.nextToken());
                list[parent].add(v);
            }
        }

        Queue<Integer> q = new ArrayDeque<>();

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            if (inOrders[i]==0) {
                sum[i] = times[i];
                ans = Math.max(sum[i], ans);
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            Integer curr = q.poll();
            for (Integer next : list[curr]) {
                inOrders[next]--;
                sum[next] = Math.max(sum[next], sum[curr] + times[next]);
                ans = Math.max(sum[next], ans);
                if (inOrders[next]==0) {
                    q.offer(next);
                }
            }
        }

        System.out.println(ans);
        br.close();
    }
}
