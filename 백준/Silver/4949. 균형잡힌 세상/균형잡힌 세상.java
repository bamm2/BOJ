import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str;
        while( !(str = br.readLine()).equals(".")){
            String ans ="no";
            Stack<Character> stack =new Stack<>();

            for(int i=0 ;i<str.length();i++){
                char c = str.charAt(i);
                if(c=='(' || c=='[') stack.push(c);
                else if(c==')' || c==']'){
                    if(stack.isEmpty()) break;
                    char tmp = stack.pop();
                    if(c==']' && tmp!='['){
                        break;
                    }else if(c==')' && tmp!='('){
                        break;
                    }
                }
                if(i==str.length()-1 && stack.isEmpty()) ans="yes";
            }

            System.out.println(ans);
        }


    }
}