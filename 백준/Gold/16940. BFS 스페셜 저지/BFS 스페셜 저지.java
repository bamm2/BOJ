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

    static List<Integer>[] list;
    static Queue<Integer> findQ;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        list = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            list[from].add(to);
            list[to].add(from);
        }

        findQ = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            findQ.offer(num);
        }

        System.out.println(bfs() ? 1:0);
        br.close();
    }

    private static boolean bfs() {
        if(findQ.poll()!=1) return false;

        boolean[] visited = new boolean[N + 1];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);
        visited[1] = true;

        HashSet<Integer> hs;
        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                hs = new HashSet<>();
                Integer curr = q.poll();
                for (Integer next : list[curr]) {
                    if (visited[next]) continue;
                    visited[next] = true;
                    hs.add(next);
                }
                int loop = hs.size();
                while (loop-- > 0) {
                    int curNumber = findQ.poll();
                    if (!hs.contains(curNumber)) return false;
                    q.offer(curNumber);
                }
                if(findQ.size()==0) return true;
            }
        }

        return true;
    }

}