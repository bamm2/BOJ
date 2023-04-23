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

        float[] arr =new float[N];
        st=new StringTokenizer(br.readLine()," ");
        for(int i=0;i<N;i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        float sum=arr[N-1];
        for(int i=0;i<N-1;i++){
           sum+=arr[i]/2;
        }
        
        System.out.println(sum);
    }
}