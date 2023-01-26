import javafx.geometry.Pos;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static Integer[] dp ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N=Integer.parseInt(br.readLine());
        dp =new Integer[N+1];

        dp[0]=dp[1]=0;
        System.out.println(solve(N));

    }
    private static int solve(int num){
        if(dp[num]==null){
            if(num%6==0){
                dp[num]=Math.min(solve(num-1),Math.min(solve(num/2),solve(num/3)))+1;
            }else if(num%3==0){
                dp[num]=Math.min(solve(num/3),solve(num-1))+1;
            }else if(num%2==0){
                dp[num]=Math.min(solve(num/2),solve(num-1))+1;
            }else {
                dp[num]=solve(num-1)+1;
            }
        }
        return dp[num];
    }
}
