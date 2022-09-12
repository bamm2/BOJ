import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        Stack<Character> stack = new Stack<>();

        boolean chk = false;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='<'){
                while(!stack.isEmpty()){
                    System.out.print(stack.pop());
                }
                chk=true;
                System.out.print(s.charAt(i));
            }else if(s.charAt(i)=='>'){
                chk=false;
                System.out.print(s.charAt(i));
            }else if(chk){
                System.out.print(s.charAt(i));
            }else{
                if(s.charAt(i)==' '){
                    while(!stack.isEmpty()){
                        System.out.print(stack.pop());
                    }
                    System.out.print(s.charAt(i));
                }else{
                    stack.add(s.charAt(i));
                }
            }
            if(i==s.length()-1){
                while(!stack.isEmpty()){
                    System.out.print(stack.pop());
                }
            }
        }

    }//main
}