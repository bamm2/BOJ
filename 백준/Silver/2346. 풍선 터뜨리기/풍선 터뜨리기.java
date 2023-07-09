import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static class Number {
        int idx, num;

        Number(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        Number[] numbers = new Number[N+1];

        Deque<Number> deque = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            numbers[i] = new Number(i, Integer.parseInt(st.nextToken()));
            deque.offer(numbers[i]);
        }

        while (deque.size()!=1) {
            Number curr = deque.poll();
            sb.append(curr.idx).append(" ");

            if(curr.num>0){
                while(--curr.num>0){
                    deque.offer(deque.poll());
                }
            }else{
                while(++curr.num<=0){
                    deque.offerFirst(deque.pollLast());
                }
            }
        }

        sb.append(deque.poll().idx);

        System.out.println(sb);

    }
}