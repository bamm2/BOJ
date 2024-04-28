import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int V;

    static class Node {
        int v, maxCost;
        long sum;

        public Node(int v, int maxCost, long sum) {
            this.v = v;
            this.maxCost = maxCost;
            this.sum = sum;
        }
    }

    static List<Node>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        long money = Long.parseLong(st.nextToken());

        list = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list[from].add(new Node(to, cost, cost));
            list[to].add(new Node(from, cost, cost));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.maxCost==o2.maxCost
                ? Long.compare(o1.sum, o2.sum)
                :Integer.compare(o1.maxCost, o2.maxCost));

        pq.addAll(list[start]);

        int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (dist[curr.v] <= curr.maxCost) continue;
            dist[curr.v] = curr.maxCost;
            if (curr.v==end) {
                System.out.println(curr.maxCost);
                return;
            }
            for (Node next : list[curr.v]) {
                if (dist[next.v] <= curr.maxCost || money < curr.sum + next.maxCost) continue;
                int compCost = Math.max(curr.maxCost, next.maxCost);
                long sum = curr.sum + next.maxCost;
                pq.offer(new Node(next.v, compCost, sum));
            }
        }

        System.out.println(-1);
        br.close();
    }
}
