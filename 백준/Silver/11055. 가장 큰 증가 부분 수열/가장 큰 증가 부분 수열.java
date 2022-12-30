import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N=Integer.parseInt(br.readLine());

        int[] arr=new int[N];
        int[] dp=new int[N];

        st=new StringTokenizer(br.readLine()," ");
        for(int i=0;i<N;i++){
            arr[i]=Integer.parseInt(st.nextToken());
            dp[i]=arr[i];
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<i;j++){
                if(arr[j]<arr[i] && dp[i]<dp[j]+arr[i]){
                    dp[i]=dp[j]+arr[i];
                }
            }
        }
        int ans=0;
        for(int i=0;i<N;i++){
            ans=ans>dp[i]?ans:dp[i];
        }
        System.out.println(ans);
    }
}