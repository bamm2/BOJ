import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        int N=Integer.parseInt(br.readLine());

        long[] arr =new long[N];

        st=new StringTokenizer(br.readLine()," ");

        for(int i=0;i<N;i++){
            arr[i]=Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        long max=arr[N-1];
        if(N%2==0){
            max = 0;
            for(int i=0;i<N/2;i++){
                long num=arr[i]+arr[N-1-i];
                if(max<num){
                    max=num;
                }
            }
        }else{
            max = arr[N - 1];
            for(int i=0,size=(N-1)/2;i<size;i++){
                long num=arr[i]+arr[N-2-i];
                if(max<num){
                    max=num;
                }
            }
        }
        System.out.println(max);

    }
}