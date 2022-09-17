import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


/*
    현재 연산자 우선순위보다 큰 연산자가 stack 맨 위에 있다면 없을때까지 pop
            ')'일 경우 '(' 가 나올 때까지 pop
    피연산자는 바로바로 출력에 쌓아주기
            '('는 밖에서 우선순위가 제일 높지만
                 스택에 들어갈때는 연산자 우선순위 최하위
*/

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();
        Stack<Character> stack = new Stack<>();

        String s = br.readLine();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            switch (c) {
                case '+':
                case '-':
                case '*':
                case '/':
                    while (!stack.isEmpty() && comp(stack.peek()) >= comp(c)) {
                        sb.append(stack.pop());
                    }
                    stack.push(c);
                    break;
                case '(':
                    stack.push(c);
                    break;
                case ')':
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        sb.append(stack.pop());
                    }
                    stack.pop();
                    break;
                default:
                    sb.append(c);
            }
        }
            while(!stack.isEmpty()){
                sb.append(stack.pop());
            }

            System.out.println(sb.toString());

    }//main
    public static int comp(char c){
        if(c=='(' || c==')'){
            return 0;
        }else if (c=='+'||c=='-'){
            return 1;
        }else if( c=='*' || c=='/'){
            return 2;
        }
        return -1;
    }
}