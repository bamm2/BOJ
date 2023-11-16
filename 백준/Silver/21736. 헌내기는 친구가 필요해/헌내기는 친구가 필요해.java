import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static char[][] map;
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        Queue<int[]> q =new ArrayDeque<>();

        visited = new boolean[R][C];
        map=new char[R][C];
        for(int i=0;i<R;i++){
            String s= br.readLine();
            for(int j=0;j<C;j++){
                map[i][j]=s.charAt(j);
                if(map[i][j]=='I'){
                    visited[i][j]=true;
                    q.offer(new int[]{i,j});
                }
            }
        }

        int cnt =0 ;
        while (!q.isEmpty()){
            int[] poll = q.poll();
            for(int d=0;d<4;d++){
                int nr = poll[0]+dir[d][0];
                int nc= poll[1]+dir[d][1];
                if(isOut(nr,nc) || visited[nr][nc] || map[nr][nc]=='X') continue;
                visited[nr][nc]=true;
                if(map[nr][nc]=='P') cnt++;
                q.offer(new int[]{nr,nc});
            }
        }

        if(cnt==0) System.out.println("TT");
        else System.out.println(cnt);

    }

    private static boolean isOut(int r,int c){
        return r<0 || c<0 || r>=R || c>=C;
    }
}