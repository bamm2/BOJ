import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int idx = 1;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            if (arr[i]==idx) idx++;
            else {
                while (!stack.isEmpty() && stack.peek()==idx){
                    stack.pop();
                    idx++;
                }
                stack.push(arr[i]);
            }
        }

        while (!stack.isEmpty()) {
            Integer curr = stack.pop();
            if (curr!=idx) {
                System.out.println("Sad");
                return;
            }
            idx++;
        }

        System.out.println("Nice");
        br.close();
    }

}
