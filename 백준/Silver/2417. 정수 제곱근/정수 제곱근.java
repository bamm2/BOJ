import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long N = Long.parseLong(br.readLine());
        long ans=0;
        long start=0;
        long end=N;

        while(start<=end){
            long mid=(start+end)/2;
            double numPow=Math.pow(mid,2);
            if(N <= numPow){
                ans=mid;
                end=mid-1;
            }else{
                start=mid+1;
            }
        }
        System.out.println(ans);
    }
}