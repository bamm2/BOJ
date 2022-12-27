import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int V,E,start;
    static int[] D;
    static final int INF=Integer.MAX_VALUE;
    static List<Point>[] list;

    static class Point implements Comparable<Point>{
        int v,weight;
        Point(int v,int weight){
            this.v=v;
            this.weight=weight;
        }
        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.weight,o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");
        V=Integer.parseInt(st.nextToken());
        E=Integer.parseInt(st.nextToken());

        start=Integer.parseInt(br.readLine());

        D=new int[V+1];
        list=new ArrayList[V+1];
        for(int i=0;i<=V;i++){
            list[i]=new ArrayList<>();
        }

        for(int i=0;i<E;i++){
            st=new StringTokenizer(br.readLine()," ");
            int from=Integer.parseInt(st.nextToken());
            int to=Integer.parseInt(st.nextToken());
            int w=Integer.parseInt(st.nextToken());
            list[from].add(new Point(to,w));
        }

        Arrays.fill(D,INF);
        D[start]=0;
        dijkstra(start);
        for(int i=1;i<=V;i++){
            if(D[i]==INF) System.out.println("INF");
            else System.out.println(D[i]);
        }

    }
    private static void dijkstra(int start){
        boolean[] visited=new boolean[V+1];

        PriorityQueue<Point> pq=new PriorityQueue<>();
        pq.offer(new Point(start,0));

        while(!pq.isEmpty()){
            Point curr=pq.poll();

            if(visited[curr.v]) continue;
            visited[curr.v]=true;

            for(Point p : list[curr.v] ){
                if(D[p.v]>D[curr.v]+p.weight){
                    D[p.v]=D[curr.v]+p.weight;
                    pq.add(new Point(p.v,D[p.v]));
                }
            }

        }

    }
}