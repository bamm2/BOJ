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

    static int from, to;
    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        for (int i = 1000; i < 10_000; i++) {
            if (isSatisfied(i)) {
                list.add(i);
            }
        }

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            int answer = solve();
            if (answer==-1) sb.append("Impossible").append('\n');
            else sb.append(answer).append('\n');
        }

        System.out.println(sb);
        br.close();
    }

    private static int solve() {
        if (from==to) return 0;

        HashSet<Integer> hs = new HashSet<>();
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(from);

        int cnt = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            cnt++;

            while (size-- > 0) {
                Integer curr = q.poll();
                for (Integer candidate : list) {
                    if (curr==candidate) continue;
                    if (isDiffOne(curr, candidate)) {
                        if (hs.add(candidate)) {
                            q.offer(candidate);
                            if (candidate==to) return cnt;
                        }
                    }
                }
            }
        }
        return -1;
    }

    private static boolean isDiffOne(Integer curr, Integer candidate) {
        int cnt = 0;
        int loop = 4;
        int tmpCurr, tmpCandidate;
        while (loop-- > 0) {
            tmpCurr = curr / (int) Math.pow(10, loop);
            tmpCandidate = candidate / (int) Math.pow(10, loop);
            if (tmpCurr % 10!=tmpCandidate % 10) cnt++;
            if (cnt==2) return false;
        }
        return true;
    }

    private static boolean isSatisfied(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i==0) return false;
        }
        return true;
    }

}