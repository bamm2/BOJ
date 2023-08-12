import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N =Integer.parseInt(br.readLine());
        int k=Integer.parseInt(br.readLine());

        int left = 1;
        int right = k;
        int mid ;

        while(left<=right){
            mid= left + (right-left)/2;
            int cnt = 0;

            for(int i=1;i<=N;i++){
                cnt+= Math.min(N,mid/i);
            }

            if(cnt>=k){
                right=mid-1;
            }else{
                left=mid+1;
            }
        }
        System.out.println(left);

    }
}
