import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N =Integer.parseInt(br.readLine());

        int[][] map =new int[N][N];
        long[][] dp = new long[N][N];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<N;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        int move = map[0][0];
        if(move<N) {
            dp[0][move]++;
            dp[move][0]++;
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j]==0) continue;
                if(dp[i][j]>0){
                    move = map[i][j];
                    if(!isOut(move+i,j)) dp[move+i][j]+=dp[i][j];
                    if(!isOut(i,move+j)) dp[i][move+j]+=dp[i][j];
                }
            }
        }

        System.out.println(dp[N-1][N-1]);
    }

    private static boolean isOut(long r,long c){
        return r<0 || c<0 || r>=N || c>=N ;
    }
}