import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N =Integer.parseInt(br.readLine());

        int[] arr =new int[N];
        for(int i=0;i<N;i++){
            arr[i]=Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        int minus = getMinus(N);
        int people = N - minus*2;
        int sum = 0;
        for(int i = minus ; i<N-minus ;i++){
            sum += arr[i];
        }

        int result = getAvg(sum,people);

        if(N==0) System.out.println(0);
        else System.out.println(result);
    }

    private static int getMinus(int n) {
        return (int)Math.round(n * 0.15);
    }
    private static int getAvg(int sum,int n){
        return (int)Math.round((double)sum/n);
    }
}