import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int num = Integer.parseInt(br.readLine()) ;
            int[] arr =new int[num+1];
            while(num!=1){
                for(int i=2;;i++){
                    if(num%i==0){
                        num/=i;
                        arr[i]++;
                        break;
                    }
                }
            }
            
            for(int i=1;i<arr.length;i++){
                if(arr[i]!=0){
                    sb.append(i).append(" ").append(arr[i]).append('\n');
                }
            }
        }
        System.out.println(sb);

    }
}