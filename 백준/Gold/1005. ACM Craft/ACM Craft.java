import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] times, inOrder,result;
    static int findNumber;
    static List<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            times = new int[N + 1];
            inOrder = new int[N + 1];
            result=new int[N+1];

            list = new List[N + 1];
            for (int i = 1; i <= N; i++) {
                list[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 1; i <= N; i++) {
                times[i] = Integer.parseInt(st.nextToken());
                result[i]=times[i];
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                inOrder[to]++;
                list[from].add(to);
            }

            findNumber = Integer.parseInt(br.readLine());

            Queue<Integer> q=new ArrayDeque<>();

            for(int i=1;i<=N;i++){
                if(inOrder[i]==0) q.offer(i);
            }

            while (!q.isEmpty()) {
                Integer curr = q.poll();

                for (Integer next : list[curr]) {
                    result[next] = Math.max(result[next], result[curr] + times[next]); // 다음 건물로 가는 최장 시간과 현재위치에서 다음 건물로 이동하는 시간 중 최댓값을 비교  
                    inOrder[next]--;
                    if (inOrder[next] == 0) q.offer(next);
                }

            }

                sb.append(result[findNumber]).append('\n');
        }

        System.out.println(sb);
    }

}