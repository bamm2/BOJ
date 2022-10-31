import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N,arr[],min,max,sign[];
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N=Integer.parseInt(br.readLine());
        st=new StringTokenizer(br.readLine()," ");
        arr=new int[N];
        sign=new int[4];
        for(int i=0;i<N;i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }
        st=new StringTokenizer(br.readLine()," ");
        for(int i=0;i<4;i++){
            sign[i]=Integer.parseInt(st.nextToken());  // [0]+ [1]- [2]* [3]/
        }
        min=Integer.MAX_VALUE;
        max=Integer.MIN_VALUE;
        solve(1,arr[0]);
        System.out.println(max);
        System.out.println(min);
    }
    private static void solve(int idx,int sum){
        if(idx==N){
            min=min<sum?min:sum;
            max=max>sum?max:sum;
            return;
        }
        for(int d=0;d<4;d++){
            if(sign[d]==0) continue;
                sign[d]--;
                switch (d){
                    case 0:
                        solve(idx+1,sum+arr[idx]);
                        break;
                    case 1:
                        solve(idx+1,sum-arr[idx]);
                        break;
                    case 2:
                        solve(idx+1,sum*arr[idx]);
                        break;
                    case 3:
                        solve(idx+1,sum/arr[idx]);
                        break;
                }
                sign[d]++;
            }
        }
}
