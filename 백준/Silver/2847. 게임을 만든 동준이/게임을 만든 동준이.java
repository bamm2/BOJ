import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N =Integer.parseInt(br.readLine());

        int[] arr =new int[N];
        for(int i=0;i<N;i++){
            arr[i]=Integer.parseInt(br.readLine());
        }

        int sum = 0;
        int range = arr[N-1];
        for(int i=N-2;i>=0;i--){
            if(range==0){
                sum+=arr[i];
            }else if(range<=arr[i]){
                sum+=arr[i]-range+1;
                range--;
            }else if(range>arr[i]){
                range=arr[i];
            }
        }

        System.out.println(sum);
    }
}