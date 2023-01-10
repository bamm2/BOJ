import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        int N = Integer.parseInt(br.readLine());

        boolean sign=false;
        int curr=0;
        for(int i=0;i<N;i++){
            int num=Integer.parseInt(br.readLine());

            if( curr <= num ) {
                while(true){
                    stack.push(++curr);
                    sb.append('+').append('\n');
                    if(curr==num){
                        stack.pop();
                        sb.append('-').append('\n');
                        break;
                    }
                }
            }else{
                while(true){
                    if(stack.isEmpty()){
                        sign=true;
                        break;
                    }
                    if(stack.peek()==num){
                        stack.pop();
                        sb.append('-').append('\n');
                        break;
                    }
                    stack.pop();
                    sb.append('-').append('\n');
                }
            }
        }

        if(sign) System.out.println("NO");
        else System.out.println(sb.toString().trim());

    }
}