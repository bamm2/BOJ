import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N,K,numbers[],arr[];
    static HashSet<String> hs =new HashSet<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N=Integer.parseInt(br.readLine());
        K=Integer.parseInt(br.readLine());

        numbers=new int[K];
        arr=new int[N];
        visited=new boolean[N];

        for(int i=0;i<N;i++){
            arr[i]=Integer.parseInt(br.readLine());
        }

        solve(0);

        System.out.println(hs.size());
    }

    private static void solve(int idx){
        if(idx==K){
            String str="";
            for(int num:numbers) str+=num;
            hs.add(str);
            return;
        }


        for(int i=0;i<N;i++){
            if(visited[i]) continue;
            visited[i]=true;
            numbers[idx]=arr[i];
            solve(idx+1);
            visited[i]=false;
        }
    }
}