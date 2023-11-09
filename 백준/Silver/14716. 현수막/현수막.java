import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C, map[][];
    static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, -1}, {-1, 1}, {1, -1}};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());
        map=new int[R][C];
        visited=new boolean[R][C];

        for(int i=0;i<R;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<C;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(!visited[i][j] && map[i][j]==1){
                    bfs(i,j);
                    count++;
                }
            }
        }

        System.out.println(count);
    }

    private static void bfs(int r, int c) {
        visited[r][c]=true;
        Queue<int[]> q =new ArrayDeque<>();
        q.offer(new int[]{r,c});

        while (!q.isEmpty()){
            int[] curr = q.poll();
            for(int d=0;d<8;d++){
                int nr =curr[0]+dir[d][0];
                int nc =curr[1]+dir[d][1];
                if(isOut(nr,nc) || visited[nr][nc] || map[nr][nc]==0) continue;
                visited[nr][nc]=true;
                q.offer(new int[]{nr,nc});
            }
        }
    }

    private static boolean isOut(int r,int c){
        return r<0 || c<0 || r>=R || c>=C;
    }
}