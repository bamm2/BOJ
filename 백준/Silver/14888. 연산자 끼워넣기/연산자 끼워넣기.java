import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int sign[],N,numbers[],min,max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        sign=new int[4];

        N=Integer.parseInt(br.readLine());

        numbers=new int[N];

        st=new StringTokenizer(br.readLine()," ");
        for(int i=0;i<N;i++){
            numbers[i]=Integer.parseInt(st.nextToken());
        }

        st=new StringTokenizer(br.readLine()," ");
        for(int i=0;i<4;i++){
            sign[i]=Integer.parseInt(st.nextToken());
        }

        min=Integer.MAX_VALUE;
        max=Integer.MIN_VALUE;

        solve(1,numbers[0]);
        System.out.println(max);
        System.out.println(min);
    }
    private static void solve(int idx,int sum){
        if(idx==N){
            min=min<sum?min:sum;
            max=max>sum?max:sum;
            return;
        }

        for(int i=0;i<4;i++){
            if(sign[i]>0){
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
}
