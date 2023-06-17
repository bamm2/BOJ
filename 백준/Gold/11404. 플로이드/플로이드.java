import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int V =Integer.parseInt(br.readLine());
        int E =Integer.parseInt(br.readLine());

        int[][] map=new int[V][V];

        for(int i=0;i<V;i++){
            for(int j=0;j<V;j++){
                if(i==j) continue;
                map[i][j]=10_000_000;
            }
        }

        for(int i=0;i<E;i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from=Integer.parseInt(st.nextToken())-1;
            int to =Integer.parseInt(st.nextToken())-1;
            int time = Integer.parseInt(st.nextToken());
            map[from][to]=Math.min(map[from][to],time);
        }

        for(int k=0;k<V;k++){ // 경유지
            for(int i=0;i<V;i++){ // 출발지
                if( i== k ) continue;
                for(int j=0;j<V;j++){ // 도착지
                    if(i==j || k==j) continue;
                    map[i][j]=Math.min(map[i][j],map[i][k]+map[k][j]);
                }
            }
        }

        StringBuilder sb= new StringBuilder();
        for(int[] row : map){
            for(int num : row){
                if(num==10_000_000) sb.append(0).append(" ");
                else sb.append(num).append(" ");
            }
            sb.append('\n');
        }

        System.out.println(sb.toString().trim());

    }
}