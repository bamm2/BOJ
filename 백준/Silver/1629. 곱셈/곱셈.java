import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine()," ");

        int A =Integer.parseInt(st.nextToken());
        int B=Integer.parseInt(st.nextToken());
        int C=Integer.parseInt(st.nextToken());

        long ans = solve(A,B,C);

        System.out.println(ans);

    }

    private static long solve(int a,int b,int c){
        if(b==1){
            return a % c;
        }

        long tmp = solve(a,b/2,c);

        if(b%2==1){
            return ((tmp*tmp%c)*a)%c;
        }else{
            return tmp*tmp%c;
        }

    }
}