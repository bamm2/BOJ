import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N,dig[],map[][],dis[],p[];
    static boolean[] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        N=Integer.parseInt(br.readLine());

        dig=new int[N+1];
        map=new int[N+1][N+1];
        visited=new boolean[N+1];

        int minDig=Integer.MAX_VALUE;
        int minidx=0;
        for(int i=1;i<=N;i++){
            dig[i]=Integer.parseInt(br.readLine());
            if(minDig>dig[i]) {
                minDig=dig[i];
                minidx=i;
            }
        }

        for(int i=1;i<=N;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=1;j<=N;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        dis=new int[N+1];
        Arrays.fill(dis,Integer.MAX_VALUE);

        dis[minidx]=minDig;

        for(int i=1;i<N;i++){
            int min=Integer.MAX_VALUE;
            int idx=-1;
            for(int j=1;j<=N;j++) {
                if (!visited[j] && dis[j] < min) {
                    min=dis[j];
                    idx = j;
                }
            }
            visited[idx]=true;

            for(int j=1;j<=N;j++){
                if(!visited[j] && map[idx][j]!=0 && dis[j]>map[idx][j]){
                    dis[j]=map[idx][j];
                    if(dis[j]>dig[j]) dis[j]=dig[j];
                }
            }
         }

        int sum=0;
        for(int i=1;i<=N;i++) sum+=dis[i];


        System.out.println(sum);

    }
}
