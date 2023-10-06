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
        int range = Integer.parseInt(st.nextToken());

        long[] arr = new long[N+2];
        long[] sum = new long[N+2];

        st=new StringTokenizer(br.readLine()," ");
        for(int i=1;i<=N;i++){
            int num =Integer.parseInt(st.nextToken());
            arr[i]=num;
        }


        for(int i=N;i>=0;i--){
            sum[i]+=sum[i+1]+arr[i];
        }

        boolean flag= true;
        for(int i=1;i<=N-1;i++){
            long left = arr[i]-range;
            long right = arr[i]+range;
            double avg =  (double)(sum[i+1]) / (N-i) ;
            if( ! (left< avg && avg <right) ){
                flag=false;
                break;
            }
        }
        if(!flag) System.out.println("unstable");
        else System.out.println("stable");

    }
}
