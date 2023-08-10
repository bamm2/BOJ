import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int R, C, map[][], ans;
    static boolean[][] visited;
    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 상 우 하 좌

    static class Point {
        int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);

        System.out.println(ans);
    }

    private static void dfs(int index , int sum) {

        if (index == R*C) {
            ans = Math.max(ans, sum);
            return;
        }

        int r = index/C;
        int c = index%C;

        if(!visited[r][c]){
            if(!isOut(r-1,c+1) && !visited[r-1][c] && !visited[r][c+1]){
                visited[r][c]=true;
                visited[r-1][c]=true;
                visited[r][c+1]=true;
                int add = map[r][c]*2 + map[r-1][c] + map[r][c+1];
                dfs(index+1,sum+add);
                visited[r][c]=false;
                visited[r-1][c]=false;
                visited[r][c+1]=false;
            }
            if(!isOut(r+1,c+1) && !visited[r+1][c] && !visited[r][c+1]){
                visited[r][c]=true;
                visited[r+1][c]=true;
                visited[r][c+1]=true;
                int add = map[r][c]*2 + map[r+1][c] + map[r][c+1];
                dfs(index+1,sum+add);
                visited[r][c]=false;
                visited[r+1][c]=false;
                visited[r][c+1]=false;
            }
            if(!isOut(r+1,c-1) && !visited[r+1][c] && !visited[r][c-1]){
                visited[r][c]=true;
                visited[r+1][c]=true;
                visited[r][c-1]=true;
                int add = map[r][c]*2 + map[r+1][c] + map[r][c-1];
                dfs(index+1,sum+add);
                visited[r][c]=false;
                visited[r+1][c]=false;
                visited[r][c-1]=false;
            }
            if(!isOut(r-1,c-1) && !visited[r-1][c] && !visited[r][c-1]){
                visited[r][c]=true;
                visited[r-1][c]=true;
                visited[r][c-1]=true;
                int add = map[r][c]*2 + map[r-1][c] + map[r][c-1];
                dfs(index+1,sum+add);
                visited[r][c]=false;
                visited[r-1][c]=false;
                visited[r][c-1]=false;
            }
        }
            dfs(index+1,sum);
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C;
    }
}