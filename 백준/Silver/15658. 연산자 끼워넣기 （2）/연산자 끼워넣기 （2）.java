import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N,numbers[],sign[],max,min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N=Integer.parseInt(br.readLine());
        numbers=new int[N];

        st=new StringTokenizer(br.readLine()," ");
        for(int i=0;i<N;i++){
            numbers[i]=Integer.parseInt(st.nextToken());
        }

        sign=new int[4];
        st=new StringTokenizer(br.readLine()," ");
        for(int i=0;i<4;i++){
            sign[i]=Integer.parseInt(st.nextToken());
        }
        max=Integer.MIN_VALUE;
        min=Integer.MAX_VALUE;
        solve(1,numbers[0]);
        System.out.println(max);
        System.out.println(min);

    }
    private static void solve(int idx,int sum){
        if(idx==N){
            max=Math.max(max,sum);
            min=Math.min(min,sum);
            return;
        }

        for(int i=0;i<4;i++){
            if(sign[i]==0) continue;
            sign[i]--;
            switch (i){
                case 0:
                    solve(idx+1,sum+numbers[idx]);
                    break;
                case 1:
                    solve(idx+1,sum-numbers[idx]);
                    break;
                case 2:
                    solve(idx+1,sum*numbers[idx]);
                    break;
                case 3:
                    solve(idx+1,sum/numbers[idx]);
                    break;
            }
            sign[i]++;
        }

    }
}