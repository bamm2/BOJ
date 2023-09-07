import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *  고정 이전까지의 갯수는 i =  i-2 + i-1  의 규칙성을 띄고 있음
 *  고정좌석을 1로 하고 그 다음수부터 다시 1 부터 규칙성을 쌓아가기
 *  dp가 1보다 크면서 다음 수가 1이면 그만큼의 경우의 수가 발생 / 경우의 수 다 곱해주기
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N=Integer.parseInt(br.readLine());

        int M=Integer.parseInt(br.readLine());

        int[] dp =new int[41];
        boolean[] visited= new boolean[41];

        while(M-->0){
            int num=Integer.parseInt(br.readLine());
            visited[num]=true;
        }
        dp[1]=1;
        if(visited[1]||visited[2]) dp[2]=1;
        else dp[2]=2;

        for(int i=3;i<=N;i++){
            if(visited[i]) dp[i]=1;
            else if(visited[i-1]) dp[i]=1;
            else dp[i]=dp[i-2]+dp[i-1];
        }

        int ans = 1;
        for(int i=1;i<N;i++){
            if(dp[i+1]==1 && dp[i]>1) ans*=dp[i];
        }
        ans*=dp[N];
        System.out.println(ans);
    }
}