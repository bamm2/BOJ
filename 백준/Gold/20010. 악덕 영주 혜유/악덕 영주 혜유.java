import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int V;
    static int[] parents;
    static List<Node>[] list;

    static class Point {
        int from, to, cost;

        public Point(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static class Node {
        int v;
        long sum;

        public Node(int v, long sum) {
            this.v = v;
            this.sum = sum;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        PriorityQueue<Point> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.offer(new Point(from, to, cost));
        }

        list = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            list[i] = new ArrayList<>();
        }

        make();
        int pick = 1;
        long sum = 0;
        while (!pq.isEmpty()) {
            Point curr = pq.poll();
            if (union(curr.from, curr.to)) {
                pick++;
                sum += curr.cost;
                list[curr.from].add(new Node(curr.to, curr.cost));
                list[curr.to].add(new Node(curr.from, curr.cost));
            }
            if (pick==V) break;
        }

        long startNumber = bfs(0, false);
        long max = bfs((int) startNumber, true);

        System.out.println(sum);
        System.out.println(max);
        br.close();
    }


    private static void make() {
        parents = new int[V];
        for (int i = 0; i < V; i++) {
            parents[i] = i;
        }
    }

    private static int find(int a) {
        if (parents[a]==a) return a;
        return parents[a] = find(parents[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot==bRoot) return false;

        if (aRoot > bRoot) parents[aRoot] = bRoot;
        else parents[bRoot] = aRoot;
        return true;
    }

    private static long bfs(int v, boolean flag) {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(v, 0L));
        boolean[] visited = new boolean[V];
        visited[v] = true;
        long max = -1;
        int vert = -1;
        while (!q.isEmpty()) {
            Node curr = q.poll();
            if (curr.sum > max) {
                max = curr.sum;
                vert = curr.v;
            }
            for (Node next : list[curr.v]) {
                if (visited[next.v]) continue;
                visited[next.v] = true;
                long nextCost = curr.sum + next.sum;
                q.offer(new Node(next.v, nextCost));
            }
        }

        return flag ? max:vert;
    }
}
