import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static Stack<Character> front, back;

    /**
     * 백스페이스 -> '-' , 커서 바로 앞에 글자 존재하면 지우기
     * 화살표는 < , >  , 왼쪽 오른쪽으로 1만큼 이동
     * 만약 커서 위치가 줄의 마지막이 아니면 , 커서 및 커서 오른쪽에 있는 모든 문자 오른쪽 한칸 이동
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            front = new Stack<>();
            back = new Stack<>();

            String str = br.readLine();
            for (int i = 0; i < str.length(); i++) {
                char curr = str.charAt(i);
                if (!(curr=='-' || curr=='<' || curr=='>')) {
                    front.push(curr);
                } else {
                    solve(curr);
                }
            }

            String ans = makeAns();
            sb.append(ans).append('\n');
        }

        System.out.println(sb);

    }

    private static void solve(char c) {
        switch (c) {
            case '<':
                if (!front.isEmpty()){
                    back.push(front.pop());
                }
                    break;
            case '>':
                if(!back.isEmpty()){
                    front.push(back.pop());
                }
                break;
            case '-':
                if(!front.isEmpty()){
                    front.pop();
                }
                break;
        }

    }

    private static String makeAns() {
        StringBuilder sb =new StringBuilder();
        while (!front.isEmpty()) {
            back.push(front.pop());
        }
        while (!back.isEmpty()) {
            sb.append(back.pop());
        }
        return sb.toString();
    }
}