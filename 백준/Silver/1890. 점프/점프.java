import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, map[][];
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N =Integer.parseInt(br.readLine());

        map =new int[N][N];
        dp = new long[N][N];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<N;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
                dp[i][j]=-1;
            }
        }

        System.out.println( dfs(0,0) ) ;

    }

    private static long dfs(int r,int c){
        if(r==N-1 && c==N-1){
            return 1;
        }

        if(dp[r][c]!=-1) return dp[r][c];

        dp[r][c]=0;
        
        if(!isOut(r+map[r][c],c)) dp[r][c]+=dfs(r+map[r][c],c);
        if(!isOut(r,c+map[r][c])) dp[r][c]+=dfs(r,c+map[r][c]);

        return dp[r][c];
    }

    private static boolean isOut(long r,long c){
        return r<0 || c<0 || r>=N || c>=N ;
    }
}