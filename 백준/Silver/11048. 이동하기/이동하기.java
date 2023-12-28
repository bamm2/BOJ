import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static int[][] dir = {{-1, 0}, {0, -1}, {-1, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int[][] map = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int[][] dp =new int[R][C];
        dp[0][0]=map[0][0];
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                for(int d=0;d<3;d++){
                   int pr = i+dir[d][0];
                   int pc = j+dir[d][1];
                   if(isOut(pr,pc)) continue;
                   dp[i][j]=Math.max(map[i][j]+dp[pr][pc],dp[i][j]);
                }
            }
        }
        System.out.println(dp[R-1][C-1]);

    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C;
    }
}
