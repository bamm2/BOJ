import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int V ,E;
    static int maxDis=Integer.MIN_VALUE;
    static List<Point>[] map;
    static class Point{
        int to,dis;
        Point(int to,int dis){
            this.to=to;
            this.dis=dis;
        }
    }
    static int[] dis;
    static boolean[] visited;

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        st = new StringTokenizer(br.readLine()," ");

        V=Integer.parseInt(st.nextToken());
        E=Integer.parseInt(st.nextToken());

        map=new ArrayList[V+1];
        visited=new boolean[V+1];
        dis=new int[V+1];

        for(int i=1;i<=V;i++){
            map[i]=new ArrayList<>();
        }

        for(int i=0;i<E;i++){
            st=new StringTokenizer(br.readLine()," ");
            int from=Integer.parseInt(st.nextToken());
            int to=Integer.parseInt(st.nextToken());

            map[from].add(new Point(to,0));
            map[to].add(new Point(from,0));
        }

        solve();

        int minV=V+1;
        int sameCnt=0;

        for(int i=V;i>=1;i--){
            if(dis[i]==maxDis){
                sameCnt++;
                if(minV>i){
                    minV=i;
                }
            }
        }

        System.out.println(minV+" "+maxDis+" "+sameCnt);
    }

    private static void solve(){

        Queue<Point> q =new ArrayDeque<>();
        q.offer(new Point(1,0));
        visited[1]=true;

        while(!q.isEmpty()){
            Point curr = q.poll();
            if(maxDis<curr.dis) maxDis=curr.dis;

            for(Point num : map[curr.to]){
                if(visited[num.to]) continue;
                visited[num.to]=true;
                dis[num.to]=curr.dis+1;
                q.offer(new Point(num.to,curr.dis+1));
            }
        }
    }

}

