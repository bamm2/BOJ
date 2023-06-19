import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str= br.readLine();

        Stack<Character> in = new Stack<>();
        Stack<Character> comp =new Stack<>();

        for(int i=str.length()-1;i>=0;i--){
            char c = str.charAt(i);
            in.add(c);
        }

        boolean isA = false;
        boolean isNotAns = false;
        loop:
        while(!in.isEmpty()){
            char c = in.pop();

            comp.push(c);

            if(isA){
                int cnt = 4;
                String checkPAPP = "";
                while(cnt-->0){
                    if(comp.isEmpty()){
                        isNotAns=true;
                        break loop;
                    }
                    checkPAPP+=comp.pop();
                }
                if(checkPAPP.equals("PAPP")) comp.push('P');
                isA=false;
            }

            if( c == 'A') isA=true;

        }

        String ans = "";
        while(!comp.isEmpty()){
            ans+=comp.pop();
        }

        if(isNotAns || !ans.equals("P")) System.out.println("NP");
        else System.out.println("PPAP");
      }
}