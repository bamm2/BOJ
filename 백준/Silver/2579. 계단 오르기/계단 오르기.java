import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N=Integer.parseInt(br.readLine());
        int[] dp =new int[N+1];
        int[] arr= new int[N+1];
        for(int i=1;i<=N;i++){
            arr[i]=Integer.parseInt(br.readLine());
            }

        dp[1]=arr[1];
        if(N>=2) {
            dp[2] = arr[1] + arr[2];
        if (N >= 3) {
                dp[3] = Math.max(arr[1], arr[2]) + arr[3];
                // 4번째까지 갈 수 있는 경우 1칸가고 2칸,1칸  or 2칸 ,2칸  ( 2칸,1칸,1칸은 연속이므로 )
                for (int i = 4; i <= N; i++) {
                    dp[i] = Math.max(dp[i - 3] + arr[i - 1], dp[i - 2]) + arr[i];
                }
            }
        }
        System.out.println(dp[N]);
    } // main
}