import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N =Integer.parseInt(br.readLine());

        int[] line =new int[N];

        for(int i=0;i<N;i++){
           line[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(line);

        long sum=0;
        int idx=1;
        for(int i=N-1;i>=0;i--){
            int num =  line[i]-(idx-1) ;
            if(num>0) {
                sum+=num;
                idx++;
            };
        }

        System.out.println(sum);
    }
}