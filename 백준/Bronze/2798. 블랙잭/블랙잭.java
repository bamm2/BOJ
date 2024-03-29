import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N,M,arr[],numbers[],answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        arr =new int[N];
        st=new StringTokenizer(br.readLine()," ");
        for(int i=0;i<N;i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }

        numbers=new int[3];
        answer=Integer.MIN_VALUE;

        solve(0,0,0);

        System.out.println(answer);
    }

    private static void solve(int idx,int start,int sum){
        if(idx==3){
            if(sum<=M){
                answer=answer>sum?answer:sum;
            }
            return;
        }

        for(int i=start;i<N;i++){
            solve(idx+1,i+1,sum+arr[i]);
        }
    }
}
