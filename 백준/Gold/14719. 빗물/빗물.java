import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        Stack<Integer> stack = new Stack<>();

        st = new StringTokenizer(br.readLine(), " ");

        int max = 0;
        int ans = 0;

        for (int i = 0; i < C; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (max < num) {
                if (!stack.isEmpty()) {
                    while (stack.size() != 1) {
                        ans += max - stack.pop();
                    }
                    stack.pop();
                }
                max = num;
                stack.push(num);
            }  else {
                stack.push(num);
            }
        }

        if(!stack.isEmpty()){
           int comp =stack.pop();
           while(stack.size()!=1 && !stack.isEmpty()) {
               while (!stack.isEmpty() && stack.peek() < comp) {
                   ans += comp - stack.pop();
               }
               if(!stack.isEmpty()) {
                   comp = stack.pop();
               }
           }
        }

        System.out.println(ans);

    }
}