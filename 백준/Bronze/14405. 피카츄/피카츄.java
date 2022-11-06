import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        boolean[] visited=new boolean[s.length()];
        
        for(int i=0;i<s.length()-1;i++){
            if(!visited[i] && s.charAt(i)=='p' && s.charAt(i+1)=='i'){
                visited[i]=true;
                visited[i+1]=true;
            }else if(!visited[i] && s.charAt(i)=='k' && s.charAt(i+1)=='a'){
                visited[i]=true;
                visited[i+1]=true;
            }
        }
        
        for(int i=0;i<s.length()-2;i++){
            if(!visited[i] && s.charAt(i)=='c' && s.charAt(i+1)=='h' && s.charAt(i+2)=='u'){
                visited[i]=true;
                visited[i+1]=true;
                visited[i+2]=true;
            }
        }

        boolean sign=true;
        for(int i=0;i<s.length();i++){
            if(!visited[i]){
                System.out.println("NO");
                sign=false;
                break;
            }
        }

        if(sign) System.out.println("YES");

    }
}