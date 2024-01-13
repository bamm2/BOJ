import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<Integer>[] list, tree;
    static int V, root;
    static int[] parents, count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        root = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        list = new ArrayList[V + 1];
        tree = new ArrayList[V + 1];
        for (int i = 0; i <= V; i++) {
            list[i] = new ArrayList<>();
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < V - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            list[from].add(to);
            list[to].add(from);
        }

        parents = new int[V + 1];
        makeTree(root, -1);
        count = new int[V + 1];
        countSubtreeNodes(root);

        for(int i=0;i<Q;i++){
            sb.append(count[Integer.parseInt(br.readLine())]).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void makeTree(int curr, int parent) { // 현재 정점 , 부모 정점
        for (int next : list[curr]) { // 양방향으로 연결되어 있기에
            if (next!=parent) { // 리프노드에서 next==parent, 돌아오면서 부모 일치
                parents[next] = curr; // 자식 정점에서 부모정점을 연결되어 있음을 표시
                tree[curr].add(next); // 부모 정점과 연결되어 있는 정점들
                makeTree(next, curr);
            }
        }
    }

    private static void countSubtreeNodes(int v) {
        count[v] = 1; // 자기 자신
        for (int next : tree[v]) {
            countSubtreeNodes(next);
            count[v] += count[next];
        }
    }

}