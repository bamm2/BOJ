import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] arr,numbers;
    static StringBuilder sb= new StringBuilder();
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true){
            st=new StringTokenizer(br.readLine()," ");
            N=Integer.parseInt(st.nextToken());
            if(N==0) break;
            arr=new int[N];
            numbers=new int[6];

            for(int i=0;i<N;i++){
                arr[i]=Integer.parseInt(st.nextToken());
            }
            solve(0,0);
            System.out.println(sb);
            sb.setLength(0);
        }
    }
    private static void solve(int cnt,int start){
        if(cnt==6){
            for(int i=0;i<6;i++) {
                sb.append(numbers[i]).append(" ");
            }
            sb.append('\n');
            return;
        }

        for(int i=start;i<N;i++){
            numbers[cnt]=arr[i];
            solve(cnt+1,i+1);
        }
    }

}