import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N =Integer.parseInt(br.readLine());

        int ans = 0 ;
        for(int i=0;i<N;i++){
            String str = br.readLine();
            Stack<Character> stack= new Stack<>();

            if(str.length()%2==1) continue;
            for (int j = 0; j < str.length(); j++) {
                char c = str.charAt(j);
                if(stack.isEmpty()) stack.push(c);
                else {
                    if(c==stack.peek()){
                        stack.pop();
                    }else{
                        stack.push(c);
                    }
                }
                if(j==str.length()-1 && stack.isEmpty()) ans++;
            }
        }

        System.out.println(ans);

    }
}