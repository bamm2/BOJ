import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st=new StringTokenizer(br.readLine()," ");
        int N =Integer.parseInt(st.nextToken());
        int K =Integer.parseInt(st.nextToken());

        int[] arr =new int[N+1];
        st=new StringTokenizer(br.readLine()," ");
        for(int i=1;i<=N;i++){
            arr[i]=Integer.parseInt(st.nextToken());
            arr[i]+=arr[i-1];
        }

        int ans = Integer.MIN_VALUE ;
        int[] tmp =new int[N+1];
        for(int i=K;i<=N;i++){
            int sum = arr[i] - arr[i - K];
            tmp[i]=sum;
            if(sum > ans){
                ans = sum;
            }
        }
        System.out.println(ans);
    }
}
