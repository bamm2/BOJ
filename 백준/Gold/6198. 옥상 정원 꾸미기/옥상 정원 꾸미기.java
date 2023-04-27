import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N =Integer.parseInt(br.readLine()); // 빌딩의 개수

        int[] arr =new int[N];
        for(int i=0;i<N;i++){
            arr[i]=Integer.parseInt(br.readLine());
        }

        Stack<Integer> stack =new Stack<>();
        long sum=0;
        for(int i=0;i<N;i++){
            int curr = arr[i];
            while(!stack.isEmpty() && stack.peek() <= curr ) {
                stack.pop();
            }
            stack.push(curr);
            sum+=stack.size()-1;
        }
        System.out.println(sum);

    }
}