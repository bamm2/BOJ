import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N,M,map[][];
    static boolean[] visited;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N=Integer.parseInt(st.nextToken()); // 정점의 개수
        M=Integer.parseInt(st.nextToken()); // 간선의 개수
        map=new int[N+1][N+1];
        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine()," ");
            int start =Integer.parseInt(st.nextToken());
            int ed =Integer.parseInt(st.nextToken());
            map[start][ed]=map[ed][start]=1;
        }
        visited=new boolean[N+1];

        int ans=0;
        for(int i=1;i<=N;i++){
            if(!visited[i]){
               dfs(i);
                ans++;
            }
        }

        System.out.println(ans);

    }
    private static void dfs(int idx){

        visited[idx]= true;

        for(int i=1;i<=N;i++){
            if(!visited[i] && map[idx][i]==1){
                dfs(i);
            }
        }
    }
}