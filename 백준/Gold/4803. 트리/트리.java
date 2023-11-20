import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    static final String NOT_TREE = "Case %d: No trees.";
    static final String ONE_TREE = "Case %d: There is one tree.";
    static final String LOWEST_TWO_TREE = "Case %d: A forest of %d trees.";
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int testcase = 0;
        while (true) {

            st = new StringTokenizer(br.readLine(), " ");
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            if (V==0 && E==0) break;
            make(V);

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                union(from, to);
            }

            HashSet<Integer> hs = new HashSet<>();
            for (int i = 1; i <= V; i++) {
                if (find(parent[i])==0) continue;
                hs.add(find(parent[i]));
            }

            if (hs.size()==0) {
                sb.append(String.format(NOT_TREE, ++testcase)).append('\n');
            } else {
                if (hs.size()==1) sb.append(String.format(ONE_TREE, ++testcase)).append('\n');
                else sb.append(String.format(LOWEST_TWO_TREE, ++testcase, hs.size())).append('\n');
            }
        }

        System.out.println(sb);

    }

    private static void make(int V) {
        parent = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }
    }

    private static int find(int a) {
        if (parent[a]==a) return a;
        return parent[a] = find(parent[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (bRoot < aRoot) {
            int tmp = aRoot;
            aRoot = bRoot;
            bRoot = tmp;
        }
        if (aRoot==bRoot) {
            parent[bRoot] = aRoot;
            parent[aRoot] = 0;
            return false;
        }

        parent[bRoot] = aRoot;
        return true;
    }

}