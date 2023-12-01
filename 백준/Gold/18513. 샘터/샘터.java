import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, K, count;
    static long sum;
    static Queue<Integer> q = new ArrayDeque<>();
    static HashSet<Integer> hs =new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            q.offer(num);
            hs.add(num);
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
                    if(hs.contains(next[i])) continue;
                    q.offer(next[i]);
                    hs.add(next[i]);
                    sum += distance;
                    count++;
                    if(hs.size() == K+N) return;
                }
            }
        }
    }

}