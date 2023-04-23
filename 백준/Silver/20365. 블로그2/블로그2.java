import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        int N=Integer.parseInt(br.readLine());

        String s=br.readLine();


        int cnt=1;
        char comp=s.charAt(0);
        boolean sign = false;
        for(int i=1;i<N;i++){
            char c =s.charAt(i);
            if(c==comp) {
                sign=false;
                continue;
            }
            if(!sign){
                cnt++;
                sign=true;
            }
        }

        System.out.println(cnt);
    }
}