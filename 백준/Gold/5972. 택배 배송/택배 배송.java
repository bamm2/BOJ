import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int V,dist[];
    static boolean[] visited;
    static List<Point>[] list;
    static class Point implements Comparable<Point>{
        int v,w;
        Point(int v,int w){
            this.v=v;
            this.w=w;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.w,o.w);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");
        V=Integer.parseInt(st.nextToken());
        int K =Integer.parseInt(st.nextToken());

        list=new ArrayList[V];
        for(int i=0;i<V;i++){
            list[i]=new ArrayList<>();
        }
        visited=new boolean[V];
        dist=new int[V];
        Arrays.fill(dist,Integer.MAX_VALUE);

        for(int i=0;i<K;i++){
            st=new StringTokenizer(br.readLine()," ");
            int from=Integer.parseInt(st.nextToken())-1;
            int to =Integer.parseInt(st.nextToken())-1;
            int w = Integer.parseInt(st.nextToken());
            list[from].add(new Point(to,w));
            list[to].add(new Point(from,w));
        }

        solve();

        System.out.println(dist[V-1]);

    }

    private static void solve() {
        PriorityQueue<Point> q= new PriorityQueue<>();
        q.offer(new Point(0,0));
        dist[0]=0;

        while(!q.isEmpty()){
            Point curr =q.poll();

            if(visited[curr.v]) continue;
            visited[curr.v]=true;

            for(Point next : list[curr.v]){
                if(dist[next.v]>dist[curr.v]+next.w){
                    dist[next.v]=dist[curr.v]+next.w;
                    q.offer(new Point(next.v,dist[next.v]));
                }
            }
        }

    }
}