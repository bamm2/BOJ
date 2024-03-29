import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N=Integer.parseInt(br.readLine()); // 인원수

        int[] arr =new int[N+1];
        for(int i=1;i<=N;i++) arr[i] = Integer.parseInt(br.readLine());
        
        Arrays.sort(arr);

        long sum=0;
        for(int i=1;i<=N;i++){
            if(arr[i]!=i){
                sum+=Math.abs(i-arr[i]);
            }
        }
        System.out.println(sum);
    }
}


