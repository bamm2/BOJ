import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), " ");
            PriorityQueue<Long> pq = new PriorityQueue<>();

            for (int i = 0; i < N; i++) {
                long num = Long.parseLong(st.nextToken());
                pq.add(num);
            }

            long sum = 0;
            while (pq.size() != 1) {
                long num1 = pq.poll();
                long num2 = pq.poll();
                long addNum = num1 + num2;
                pq.offer(num1 + num2);
                sum += addNum;
            }

            sb.append(sum).append('\n');
        }

        System.out.println(sb);

    }
}