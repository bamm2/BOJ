import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final Integer R = 200_001;
    static int N, M;
    static List<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList[R];
        for (int i = 0; i < R; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int c = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            list[r].add(c);
        }

        for (int i = 0; i < R; i++) {
            Collections.sort(list[i]);
        }

        System.out.println(search());
        br.close();
    }

    private static int search() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0});

        int cnt = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            cnt++;
            while (size-- > 0) {
                int[] curr = q.poll();
                for (int r = -2; r <= 2; ++r) {
                    int nextR = curr[0] + r;
                    if (nextR < 0) continue;
                    for (int i = 0; i < list[nextR].size(); ++i) {
                        int nextC = list[nextR].get(i);
                        if (curr[1] - 2 <= nextC && nextC <= curr[1] + 2) {
                            if (nextR >= M) return cnt;
                            list[nextR].remove(i);
                            --i;
                            q.offer(new int[]{nextR, nextC});
                        }
                        if (curr[1] + 2 < nextC) break;
                    }
                }
            }
        }
        return -1;
    }

}
