import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));

        String s= br.readLine();

        String ans="";
        for(int i=0;i<s.length();i++){
            if('a'<=s.charAt(i) && s.charAt(i)<='z'){
                ans += (char)(s.charAt(i)-32);
            }else{
                ans += (char)(s.charAt(i)+32);
            }
        }
        System.out.println(ans);
    } // main
}