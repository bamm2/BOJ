import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[1_000_001];
        int[] preMove = new int[1_000_001];

        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;
        preMove[1] = 1;
        preMove[2] = 1;
        preMove[3] = 1;

        for(int i=4;i<=N;i++){
            if(i%6==0) {
                if(dp[i/2]>dp[i/3]){
                    if(dp[i/3]>dp[i-1]){
                        dp[i]=dp[i-1]+1;
                        preMove[i]=i-1;
                    }else{
                        dp[i]=dp[i/3]+1;
                        preMove[i]=i/3;
                    }
                }else if(dp[i/3]>dp[i/2]){
                    if(dp[i/2]>dp[i-1]){
                        dp[i]=dp[i-1]+1;
                        preMove[i]=i-1;
                    }else{
                        dp[i]=dp[i/2]+1;
                        preMove[i]=i/2;
                    }
                }else if(dp[i-1]>dp[i/3]){
                    if(dp[i/3]>dp[i/2]){
                        dp[i]=dp[i/2]+1;
                        preMove[i]=i/2;
                    }else{
                        dp[i]=dp[i/3]+1;
                        preMove[i]=i/3;
                    }
                }
            }else if(i%3 ==0 ){
                if(dp[i/3]>dp[i-1]){
                    dp[i]=dp[i-1]+1;
                    preMove[i]=i-1;
                }else{
                    dp[i]=dp[i/3]+1;
                    preMove[i]=i/3;
                }
            }else if(i%2==0){
                if(dp[i/2]>dp[i-1]){
                    dp[i]=dp[i-1]+1;
                    preMove[i]=i-1;
                }else{
                    dp[i]=dp[i/2]+1;
                    preMove[i]=i/2;
                }
            }else{
                dp[i]=dp[i-1]+1;
                preMove[i]=i-1;
            }
        }

        sb.append(dp[N]).append('\n');

        int idx = N;
        sb.append(idx).append(" ");
        while(true){
            if(idx==1) break;
            idx=preMove[idx];
            sb.append(idx).append(" ");
        }

        System.out.println(sb);

    }
}