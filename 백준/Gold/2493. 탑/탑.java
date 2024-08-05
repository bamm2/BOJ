import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Point{
        int h,idx;
        Point(int h, int idx){
            this.h=h;
            this.idx=idx;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Point> stack = new Stack<>();

        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int height = 0;
        int idx = 0;
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            if(!stack.isEmpty()){
               if(stack.peek().h<num) { // 스택에 있는 수보다 새로들어온 수가 클 경우
                   while(!stack.isEmpty()){
                       if(stack.peek().h>num){
                           sb.append(stack.peek().idx+" ");
                           stack.push(new Point(num,i+1));
                           break;
                       }
                       stack.pop();
                   }
               }else{
                   sb.append(stack.peek().idx+" ");
                   stack.push(new Point(num,i+1));
               }
            }

            if(stack.isEmpty()){
                sb.append("0 ");
                stack.push(new Point(num,i+1));
            }
        }
        System.out.println(sb.toString().trim());
    }
}