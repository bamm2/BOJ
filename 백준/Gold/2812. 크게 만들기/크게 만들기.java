import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        StringBuilder sb =new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int len = Integer.parseInt(st.nextToken());
        int remove = Integer.parseInt(st.nextToken());

        String s = br.readLine();
        Queue<Character> q = new ArrayDeque<>();
        Deque<Character> answer = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            q.offer(s.charAt(i));
        }

        char range = q.poll();
        answer.offer(range);

        loop:
        while (!q.isEmpty()) {
            Character curr = q.poll();

            if (range < curr) { // 기준보다 큰 수
                range = curr;
                while (!answer.isEmpty()) {
                    Character last = answer.peekLast();
                    if (last < curr) {
                        answer.pollLast();
                        remove--;
                    } else break;
                    if (remove==0) {
                        answer.offerLast(curr);
                        break loop;
                    }
                }
                answer.offerLast(curr);
            } else { // 기준이랑 작거나 같은 수
                answer.offerLast(curr);
                range = curr;
            }
        }

        while (!q.isEmpty()){
            answer.offerLast(q.poll());
        }

        while (remove-->0){
            answer.pollLast();
        }

        while (!answer.isEmpty()){
           sb.append(answer.poll());
        }

        System.out.println(sb);

    }
}