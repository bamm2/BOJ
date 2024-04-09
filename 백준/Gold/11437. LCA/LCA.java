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

    static int N;
    static List<Integer>[] list;
    static int[] parents;
    static boolean[] visited;
    static HashSet<Integer> hs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        list = new ArrayList[N + 1];
        parents = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0, size = N - 1; i < size; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            list[from].add(to);
            list[to].add(from);
        }

        initTree();

        hs = new HashSet<>();
        int M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            findParent(v1, true);
            int answer = findParent(v2, false);
            sb.append(answer==-1 ? 1:answer).append('\n');
            hs.clear();
        }

        System.out.println(sb);
        br.close();
    }

    private static void initTree() {
        visited = new boolean[N + 1];
        visited[1] = true;
        parents[1] = -1;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);

        while (!q.isEmpty()) {
            Integer curr = q.poll();
            for (Integer next : list[curr]) {
                if (visited[next]) continue;
                visited[next] = true;
                parents[next] = curr;
                q.offer(next);
            }
        }
    }

    private static int findParent(int v, boolean flag) {
        int idx = v;
        while (parents[idx]!=-1) {
            if (flag) hs.add(idx);
            else {
                if (hs.contains(idx)) {
                    return idx;
                }
            }
            idx = parents[idx];
        }
        return -1;
    }

}