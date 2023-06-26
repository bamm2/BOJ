import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        int N =Integer.parseInt(br.readLine());
        int[][] map =new int[N][N];
        int[][] dp =new int[N][N];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<=i;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0]=map[0][0];
        for(int i=0;i<N-1;i++){
            for(int j=0;j<=i;j++){
                int nextR = i+1;
                int leftC = j;
                int rightC= j+1;
                if(dp[nextR][leftC]<dp[i][j]+map[nextR][leftC]){
                    dp[nextR][leftC]=dp[i][j]+map[nextR][leftC];
                }
                if(dp[nextR][rightC]<dp[i][j]+map[nextR][rightC]){
                    dp[nextR][rightC]= dp[i][j]+map[nextR][rightC];
                }
            }
        }
        
        int ans = 0;
        for(int i=0;i<N;i++){
            ans=ans>dp[N-1][i]?ans:dp[N-1][i];
        }

        System.out.println(ans);

    }
}