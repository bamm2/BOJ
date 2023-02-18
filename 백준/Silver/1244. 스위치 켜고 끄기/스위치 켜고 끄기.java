import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N=Integer.parseInt(br.readLine());
        int[] arr =new int[N+1];
        st=new StringTokenizer(br.readLine()," ");
        for(int i=1;i<=N;i++) arr[i]=Integer.parseInt(st.nextToken());

        int M=Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine()," ");
            int gender=Integer.parseInt(st.nextToken());
            int number=Integer.parseInt(st.nextToken());
            switch (gender){
                case 1: // 남
                    int plus=number;
                    while(true){
                        if(arr[number]==0) arr[number]=1;
                        else arr[number]=0;
                        number+=plus;
                        if(number>N) break;
                    }
                    break;
                case 2: // 여
                    if(arr[number]==0) arr[number]=1;
                    else arr[number]=0;

                    int idx=1;
                    while(true){
                        if(number+idx>N || number-idx<=0) break;
                        if(arr[number-idx]!=arr[number+idx]) break;
                        if(arr[number-idx]==0) {
                            arr[number-idx]=1;
                            arr[number+idx]=1;
                        }else{
                            arr[number-idx]=0;
                            arr[number+idx]=0;
                        }
                        idx++;
                    }
                    break;
            }
        }
        StringBuilder sb =new StringBuilder();
        for(int i=1;i<=N;i++){
            sb.append(arr[i]).append(" ");
            if(i%20==0) sb.append('\n');
        }
        System.out.println(sb.toString().trim());
    }
}