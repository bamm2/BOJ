import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static boolean[] visited;
    static int V, rootCnt;
    static int[] parents;
    static List<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        visited = new boolean[V + 1];
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

        make();

        for (int i = 1; i <= V; i++) {
            if (visited[i]) continue;
            search(i);
            rootCnt++;
        }

//        rootCnt - 1 ( 새로 연결한 노드 갯수 ) + E ( 기존 간선 )  = V -1 ( 트리의 간선 갯수 )  ;
        System.out.println(rootCnt - 1 + rootCnt + E - V );

        br.close();
    }

    private static void search(int v) {
        visited[v] = true;

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(v);

        while (!q.isEmpty()) {
            Integer curr = q.poll();
            for (Integer next : list[curr]) {
                if (visited[next]) continue;
                visited[next] = true;
                if (union(curr, next)) {
                    q.offer(next);
                }
            }
        }

    }


    private static void make() {
        parents = new int[V + 1];
        for (int i = 0; i <= V; i++) {
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

        if (aRoot > bRoot) {
            int tmp = aRoot;
            aRoot = bRoot;
            bRoot = tmp;
        }

        parents[bRoot] = aRoot;
        return true;
    }

}