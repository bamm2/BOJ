import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        boolean flag = false;
        int ans = 0;
        int value = 1;

        Stack<Character> stack = new Stack<>();

        for (int i = 0, size = str.length(); i < size; i++) {
            char curr = str.charAt(i);

            if (curr == '('){
                stack.push(curr);
                value*=2;
            }

            if(curr=='['){
                stack.push(curr);
                value*=3;
            }

            if(curr==')'){
                if(stack.isEmpty() || stack.peek() !='('){
                    flag=true;
                    break;
                }

                char prev = str.charAt(i-1);
                if(prev =='(') ans+=value;

                stack.pop();
                value/=2;

            }else if(curr==']'){
                if(stack.isEmpty() || stack.peek() !='['){
                    flag=true;
                    break;
                }

                char prev = str.charAt(i-1);
                if(prev =='[') ans+=value;

                stack.pop();
                value/=3;
            }
        }

        if(flag || !stack.isEmpty()) System.out.println(0);
        else System.out.println(ans);

    }
}