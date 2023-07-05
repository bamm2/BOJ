import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N,M,ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N=Integer.parseInt(br.readLine());
        M=Integer.parseInt(br.readLine());


       comb(0,0);

        System.out.println(ans);
    }
    private static void comb(int cnt, int start){
        if(cnt==M-N){
            ans++;
            return;
        }

        for(int i=start;i<N;i++){
            comb(cnt+1,i);
        }

    }
}