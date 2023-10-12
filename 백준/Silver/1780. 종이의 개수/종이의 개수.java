import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int map[][];
    static int[] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N =Integer.parseInt(br.readLine());

        map=new int[N][N];
        ans=new int[3];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<N;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        solve(0,0,N);

        Arrays.stream(ans).forEach(i->sb.append(i).append('\n'));

        System.out.println(sb);
    }

    private static void solve(int r, int c, int range) {

        if(range==1 || isOk(r,c,range)){
            ans[map[r][c]+1]++;
            return;
        }
        
        int size=range/3;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                solve(r+i*size,c+j*size,size);
            }
        }
        
    }

    private static boolean isOk(int r,int c,int range){
        int standard= map[r][c];
        for(int i=0;i<range;i++){
            for(int j=0;j<range;j++){
                if(map[r+i][c+j]!=standard) return false;
            }
        }
        return true;
    }
}