import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static List<Integer>[] list;
    static int V;
    static int[] inOrder;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        inOrder = new int[V + 1];

        list = new ArrayList[V + 1];
        for (int i = 0; i <= V; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int v = Integer.parseInt(st.nextToken());
            int after = Integer.parseInt(st.nextToken());
            list[v].add(after);
            inOrder[after]++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= V; i++) {
            if (inOrder[i]==0) pq.offer(i);
        }

        solve(pq);

        System.out.println(sb);
        br.close();
    }

    private static void solve(PriorityQueue<Integer> pq) {
        while (!pq.isEmpty()) {
            Integer curr = pq.poll();
            sb.append(curr).append(" ");
            for (Integer prev : list[curr]) {
                inOrder[prev]--;
                if (inOrder[prev]==0) {
                    pq.offer(prev);
                }
            }
        }
    }


}