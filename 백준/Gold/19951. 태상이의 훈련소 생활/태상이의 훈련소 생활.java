import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        int[] arr =new int[N+2];
        int[] sum =new int[N+2];

        st=new StringTokenizer(br.readLine()," ");
        for(int i=1;i<=N;i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine()," ");
            int from = Integer.parseInt(st.nextToken());
            int to =Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            sum[from]+=weight;
            sum[to+1]-=weight;
        }

        for(int i=1;i<=N;i++){
            sum[i]+=sum[i-1];
            sb.append(arr[i]+sum[i]).append(" ");
        }

        System.out.println(sb);
    }
}