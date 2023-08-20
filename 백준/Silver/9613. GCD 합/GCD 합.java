import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();
        StringTokenizer st ;

        int T =Integer.parseInt(br.readLine());

        while(T-->0){
            st=new StringTokenizer(br.readLine(), " ");
            int  N =Integer.parseInt(st.nextToken());

            int[] arr =new int[N];

            for(int i=0;i<N;i++){
                arr[i]=Integer.parseInt(st.nextToken());
            }

            long  sum =0 ;
            for(int i=0;i<N-1;i++){
                for(int j=i+1;j<N;j++){
                   sum+=GCD(arr[i],arr[j]);
                }
            }

            sb.append(sum).append('\n');
        }

        System.out.println(sb);

    }

    private static int GCD(int a ,int b){
        if(a%b==0) return b;
        return GCD(b,a%b);
    }

}
