import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N ;
    static StringBuilder sb =new StringBuilder();

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        N=Integer.parseInt(br.readLine());

        dfs("",0);
        System.out.println(sb);

    }
    private static void dfs(String s, int cnt){
        if(cnt==N){
            sb.append(s).append('\n');
            return;
        }
//        if(cnt==1) if(isNotPrime(Integer.parseInt(s))) return;

        for(int i=1;i<=9;i++){
            if(isPrime(Integer.parseInt(s+i))){
                    dfs(s+i, cnt+1);
            }
        }

    }

    private static boolean isNotPrime(int num){
        if(num==1 || num==4 || num==6 || num == 8 || num==9){
            return true;
        }
        return false;
    }

    private static boolean isPrime(int num){
        if(num==1) return false;

        for(int i=2;i<=(int)Math.sqrt(num);i++){
            if(num%i==0) return false;
        }
        return true;
    }

}