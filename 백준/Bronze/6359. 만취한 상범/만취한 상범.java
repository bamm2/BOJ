import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();

        int T =Integer.parseInt(br.readLine());
        while(T-->0){
            int N =Integer.parseInt(br.readLine());
            boolean[] visited= new boolean[N+1];
            for(int i=2;i<=N;i++){
                for(int j=i;j<=N;j+=i){
                    if(!visited[j]) visited[j]=true;
                    else visited[j]=false;
                }
            }
            int sum = 0;
            for(int i=1;i<=N;i++){
                if(!visited[i]) sum++;
            }

            sb.append(sum).append('\n');
        }

        System.out.println(sb);

    }
}