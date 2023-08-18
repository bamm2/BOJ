import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int V, E, dist[];
    static boolean[] visited;
    static List<Point>[] list;
    static final int INF = 80_000_000; // 정점 개수 * 간선 가중치의 최대크기 * 최악의 경우 왕복으로 탐색하고 돌아오는 경우를 고려하기 위해 *10

    static class Point {
        int v, w;

        Point(int v, int w) {
            this.v= v;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        list = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list[start].add(new Point(end, weight));
            list[end].add(new Point(start, weight));
        }
        st = new StringTokenizer(br.readLine(), " ");
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int compA = 0;
        compA+=dijkstra(1,A);
        compA+=dijkstra(A,B);
        compA+=dijkstra(B,V);

        int compB = 0;
        compB+=dijkstra(1,B);
        compB+=dijkstra(B,A);
        compB+=dijkstra(A,V);

        int ans = Math.min(compA, compB);
        if(compA>=INF && compB>=INF) System.out.println(-1);
        else System.out.println(ans);

    }

    private static int dijkstra(int start ,int end){
        PriorityQueue<Point> pq = new PriorityQueue<>((n1, n2) -> n1.w - n2.w);
        visited = new boolean[V + 1];
        dist = new int[V + 1];
        Arrays.fill(dist, INF);

        pq.offer(new Point(start,0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Point curr = pq.poll();

            if(visited[curr.v]) continue;
            visited[curr.v]=true;

            for (Point next : list[curr.v]) {
                if(!visited[next.v] && dist[next.v]>dist[curr.v]+next.w){
                    dist[next.v]=dist[curr.v]+next.w;
                    pq.offer(new Point(next.v,dist[next.v]));
                }
            }
        }

        return dist[end];

    }
}

