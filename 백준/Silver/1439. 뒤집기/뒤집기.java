import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s= br.readLine();

        char c =s.charAt(0);
        int cnt=0;
        boolean sign =false;
        for(int i=1;i<s.length();i++){
            if(!sign && c!=s.charAt(i)){
                cnt++;
                sign=true;
            }else if(sign && c==s.charAt(i)){
                sign=false;
            }
        }
        System.out.println(cnt);
    }
}