import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static boolean[] visited,chk;
    static int N,arr[],ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb= new StringBuilder();
        int T =Integer.parseInt(br.readLine());

        while(T-->0){
            N=Integer.parseInt(br.readLine());
            ans=0;
            arr=new int[N+1];
            chk=new boolean[N+1];
            visited=new boolean[N+1];

            StringTokenizer st =new StringTokenizer(br.readLine()," ");
            for(int i=1;i<=N;i++){
                arr[i]=Integer.parseInt(st.nextToken());
            }

            for(int i=1;i<=N;i++){
                dfs(i);
            }

            sb.append(N-ans).append('\n');
        }
        System.out.println(sb.toString().trim());
    }
    private static void dfs(int from){
        if(visited[from]) return;

        visited[from]=true;

        if(!visited[arr[from]]) dfs(arr[from]);
        else{
            if(!chk[arr[from]]){
                ans++;
                for(int i=arr[from] ; i!=from ; i=arr[i])
                    ans++;
            }
        }

        chk[from]=true;
    }
}