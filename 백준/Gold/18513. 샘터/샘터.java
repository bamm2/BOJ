import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, K, count;
    static long sum;
    static boolean[] visited;
    static Queue<Integer> q = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited=new boolean[400_000_001];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            q.offer(num + 200_000_000);
            visited[num + 200_000_000]= true;
        }

        bfs();

        System.out.println(sum);
    }

    private static void bfs() {
        int distance = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            distance++;
            while (size-- > 0) {
                int curr = q.poll();
                int[] next = {curr + 1, curr - 1};
                for (int i = 0; i < next.length; i++) {
                    if(visited[next[i]]) continue;
                    visited[next[i]] = true;
                    q.offer(next[i]);
                    sum += distance;
                    count++;
                    if(count == K) return;
                }
            }
        }
    }

}