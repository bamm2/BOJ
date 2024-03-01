import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split("");
        Stack<String> stack = new Stack<>();

        for (String curr : s) {
            if (stack.isEmpty()) stack.push(curr);
            else {
                if (curr.equals(")")) {
                    decode(stack);
                } else {
                    stack.push(curr);
                }
            }
        }

        int ans = 0;
        while (!stack.isEmpty()) {
            String curr = stack.pop();
            if (curr.contains("X")) ans += Integer.parseInt(curr.substring(1));
            else ans++;
        }

        System.out.println(ans);
        br.close();
    }

    private static void decode(Stack<String> stack) {

        int size = 0;
        while (true) {
            String pop = stack.pop();
            if (pop.equals("(")) break;
            if (pop.contains("X")) size += Integer.parseInt(pop.substring(1));
            else size++;
        }

        int loop = Integer.parseInt(stack.pop()); // K는 한자리 정수이므로 항상 존재

        String pushStrSize = "X" + (size * loop); // 문자 길이를 포함한 경우 구분하기 위해 X로 표시 
        stack.push(pushStrSize);
    }
}