import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");
        int N =Integer.parseInt(st.nextToken());
        int M =Integer.parseInt(st.nextToken());
        int R =Integer.parseInt(st.nextToken());

        int[] arr =new int[N];

        st=new StringTokenizer(br.readLine()," ");
        for(int i=0;i<N;i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }

        int[][] map=new int[N][N];

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(i==j) continue;
                map[i][j]=100_000_000;
            }
        }

        for(int i=0;i<R;i++){
            st=new StringTokenizer(br.readLine()," ");
            int from =Integer.parseInt(st.nextToken())-1;
            int to =Integer.parseInt(st.nextToken())-1;
            int w =Integer.parseInt(st.nextToken());
            map[from][to]=Math.min(map[from][to],w);
            map[to][from]=Math.min(map[to][from],w);
        }

        for(int k=0;k<N;k++){ // 경
            for(int i=0;i<N;i++){ // 출
                for(int j=0;j<N;j++){ // 도
                    if(map[i][j]>map[i][k]+map[k][j]){
                        map[i][j]=map[i][k]+map[k][j];
                    }
                }
            }
        }

        int max = Integer.MIN_VALUE ;
        for(int i=0;i<N;i++){
            int sum = 0;
            for(int j=0;j<N;j++){
                if(map[i][j]<=M) sum+=arr[j];
            }
            if(max<sum) max = sum;
        }

        System.out.println(max);

    }
}