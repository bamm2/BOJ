import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    static int N,ans;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        for(int i=0;i<N;i++){
            solve(i);
        }

        System.out.println(ans);

    }
    private static void solve(int row){
        boolean[] visited= new boolean[N];
        visited[row]=true;

        Queue<Integer> q =new ArrayDeque<>();

        int count = 0;
        for(int i=0;i<N;i++){
            if(map[row][i]=='Y') {
                q.offer(i);
                count++;
                visited[i]=true;
            }
        }

        while(!q.isEmpty()){
            int r = q.poll();
            for(int i=0;i<N;i++){
                if(visited[i]) continue;
                if(map[r][i]=='Y') {
                    visited[i]=true;
                    count++;
                }
            }
        }

        if(ans<count) ans = count;

    }
}