import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        int[] dp = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int ans = 0 ;

        if(N==1) ans = arr[0];
        else if(N==2) ans =arr[0]+arr[1];
        else{
            dp[0]=arr[0];
            dp[1]=arr[0]+arr[1];
            dp[2]=Math.max(arr[0],arr[1])+arr[2];
            for(int i=3;i<N;i++){
                dp[i]=Math.max(dp[i-3]+arr[i-1],dp[i-2])+arr[i];
            }
            ans=dp[N-1];
        }

        System.out.println(ans);
    }
}