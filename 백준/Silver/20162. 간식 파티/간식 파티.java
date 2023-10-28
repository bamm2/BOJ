import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        int[] dp =new int[N];

        for (int i =0; i < N; i++) {
            arr[i]=Integer.parseInt(br.readLine());
            dp[i]=arr[i];
        }

        int max =Integer.MIN_VALUE ;

        for(int i=0;i<N;i++){
            for(int j=0;j<i;j++){
                if(arr[i]>arr[j] && dp[i] < dp[j]+arr[i]){
                    dp[i]=dp[j]+arr[i];
                }
            }
            max=Math.max(dp[i],max);
        }
        System.out.println(max);
    }
}