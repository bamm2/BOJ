import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();
        StringTokenizer st;

        int T=Integer.parseInt(br.readLine());

        while(T-->0){
            st=new StringTokenizer(br.readLine()," ");
            int a =Integer.parseInt(st.nextToken());
            int b =Integer.parseInt(st.nextToken());

            int gcd = a>b ? gcd(a,b) : gcd(b,a);
            sb.append(((long)a *b)/gcd).append('\n');
        }
        System.out.println(sb);
    }

    private static int gcd(int a, int b) {
        if(b==0) return a;
        return gcd(b, a%b);
    }
}