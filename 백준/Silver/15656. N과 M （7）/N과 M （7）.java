import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int[] input,numbers;
    static StringBuilder sb =new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

         N =Integer.parseInt(st.nextToken());
         M =Integer.parseInt(st.nextToken());
         input= new int[N];
         numbers=new int[M];

         st=new StringTokenizer(br.readLine()," ");
         for(int i=0;i<N;i++){
             input[i]=Integer.parseInt(st.nextToken());
         }
        Arrays.sort(input);
        solve(0);
        System.out.println(sb);
       }
       private static void solve(int num){
            if(num==M){
                for(int i=0;i<M;i++){
                    sb.append(numbers[i]).append(' ');
                }
                sb.append('\n');
                return;
            }

            for(int i=0;i<N;i++){
                numbers[num]=input[i];
                solve(num+1);
            }
       }
}