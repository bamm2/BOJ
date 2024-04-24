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

    static StringTokenizer st;
    static List<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int V = Integer.parseInt(br.readLine());
            list = new ArrayList[V + 1];
            for (int i = 1; i <= V; i++) {
                list[i] = new ArrayList<>();
            }
            for (int i = 0; i < V - 1; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());
                list[child].add(parent);
            }

            st = new StringTokenizer(br.readLine(), " ");
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            sb.append(search(v1, v2)).append('\n');
        }

        System.out.println(sb);
        br.close();
    }

    private static int search(int v1, int v2) {
        HashSet<Integer> hs = new HashSet<>();
        Queue<Integer> q = new ArrayDeque<>();
        hs.add(v1);
        hs.add(v2);
        q.offer(v1);
        q.offer(v2);
        while (!q.isEmpty()) {
            Integer curr = q.poll();
            for (Integer parent : list[curr]) {
                if (!hs.add(parent)) return parent;
                q.offer(parent);
            }
        }

        return -1;
    }


}
