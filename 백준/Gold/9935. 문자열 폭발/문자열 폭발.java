import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static class State {
        char c;
        int equalsCount;

        public State(char c, int equalsCount) {
            this.c = c;
            this.equalsCount = equalsCount;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String bomb = br.readLine();

        int bombLen = bomb.length();

        Stack<State> stack = new Stack<>();
        int idx = 0;
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (bomb.charAt(idx)==curr) { // 폭탄 idx와 일치하면 다음 idx 비교
                idx++;
            } else { // 일치하지 않을 경우
                idx = 0;
                if (bomb.charAt(0)==curr) { // 첫문자와 일치한다면 idx 1부터 시작
                    idx++;
                }
            }
            stack.push(new State(curr, idx));

            if (idx==bombLen) { // 문자와 일치한다면
                while (idx-- > 0) { // 다 뺴주기
                    stack.pop();
                }
                if (stack.isEmpty()) idx = 0;
                else idx = stack.peek().equalsCount; // 젤 위의 숫자가 일치하는 위치+1 에서부터 시작
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop().c);
        }

        if (sb.length()==0) System.out.println("FRULA");
        else System.out.println(sb.reverse());

    }
}