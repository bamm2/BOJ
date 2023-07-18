import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String str = br.readLine();

            int N = Integer.parseInt(br.readLine());

            Deque<String> deque = new ArrayDeque<>();

            String number = br.readLine();
            String[] numbers = number.substring(1, number.length() - 1).split(",");

            for (int i = 0; i < N; i++) {
                deque.offer(numbers[i]);
            }
            boolean result = true; // 답이 있는지 체크
            boolean isFirst = true; // 뒤집어진 상태 체크용
            for (int i = 0, size = str.length(); i < size; i++) {
                char c = str.charAt(i);
                if (c == 'R') {
                    isFirst = !isFirst;
                    continue;
                }
                if (isFirst) {
                    if (deque.isEmpty()) {
                        sb.append("error").append('\n');
                        result = false;
                        break;
                    } else {
                        deque.poll();
                    }
                } else {
                    if (deque.isEmpty()) {
                        sb.append("error").append('\n');
                        result = false;
                        break;
                    } else {
                        deque.pollLast();
                    }
                }
            }

            if (result) {
                sb.append('[');
                if (isFirst) {
                    while (deque.size()!=1 && !deque.isEmpty()){
                        sb.append(deque.poll()).append(',');
                    }
                    if(!deque.isEmpty()) {
                        sb.append(deque.poll()).append(']').append('\n');
                    }else{
                        sb.append(']').append('\n');
                    }
                }else{
                    while(deque.size()!=1 && !deque.isEmpty()){
                        sb.append(deque.pollLast()).append(',');
                    }
                    if(!deque.isEmpty()) {
                        sb.append(deque.pollLast()).append(']').append('\n');
                    }else{
                        sb.append(']').append('\n');
                    }
                }
            }
        }

        System.out.println(sb);

    }
}