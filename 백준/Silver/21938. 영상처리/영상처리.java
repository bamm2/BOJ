import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] dir ={{-1,0},{1,0},{0,1},{0,-1}};
    static int N,M,map[][];
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N =Integer.parseInt(st.nextToken());
        M =Integer.parseInt(st.nextToken());

        map=new int[N][M];
        visited=new boolean[N][M];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<M;j++) {
                int sum =0;
                for (int k= 0; k < 3; k++) {
                    sum+=Integer.parseInt(st.nextToken());
                }
                map[i][j]=sum/3;
            }
        }

        int T = Integer.parseInt(br.readLine());

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j]>=T) map[i][j]=1;
                else map[i][j]=0;
            }
        }

        int count =0 ;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j]==1 && !visited[i][j]){
                    count++;
                    bfs(i,j);
                }
            }
        }

        System.out.println(count);

    }

    private static void bfs(int r, int c) {
        Queue<int[]> q =new ArrayDeque<>();
        visited[r][c]=true;
        q.offer(new int[]{r,c});

        while (!q.isEmpty()){
            int[] curr =q.poll();
            for(int d=0;d<4;d++){
                int nr =curr[0]+dir[d][0];
                int nc=curr[1]+dir[d][1];
                if(isOut(nr,nc)|| visited[nr][nc] || map[nr][nc]!=1) continue;
                visited[nr][nc]=true;
                q.offer(new int[]{nr,nc});
            }
        }
    }

    private static boolean isOut(int r,int c){
        return r<0|| c<0 || r>=N || c>=M;
    }
}