import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb =new StringBuilder();
        StringTokenizer st ;

        st=new StringTokenizer(br.readLine()," ");
        int N =Integer.parseInt(st.nextToken());
        int M =Integer.parseInt(st.nextToken());

        int[] arr =new int[N+1];
        for(int i=0;i<=N;i++){
            arr[i]=i;
        }
        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine()," ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int tmp = arr[from];
            arr[from]=arr[to];
            arr[to]=tmp;
        }
        for(int i=1;i<=N;i++){
            sb.append(arr[i]).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}