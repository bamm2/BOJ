import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Point{
        int pos,cnt;
        Point(int pos, int cnt){
            this.pos=pos;
            this.cnt=cnt;
        }
    }
    static int[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[101];
        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            map[from] = to;
        }

        System.out.println(solve());

    }

    private static int solve() {
        boolean[] visited = new boolean[101];
        Queue<Point> q =new ArrayDeque<>();
        visited[1]=true;
        q.offer(new Point(1,0));

        while (!q.isEmpty()){
            Point curr = q.poll();
            if(curr.pos==100) return curr.cnt;
            for(int i=1;i<=6;i++){
                int np = curr.pos+i;
                if( np > 100 ) continue;
                np = map[np] == 0 ? np : map[np] ;
                if(visited[np]) continue;
                visited[np]=true;
                q.offer(new Point(np,curr.cnt+1));
            }
        }

        return -1;
    }
}