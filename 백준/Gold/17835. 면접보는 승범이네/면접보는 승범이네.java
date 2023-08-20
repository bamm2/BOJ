import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static long ansMax;
    static int V,E,K,ansIdx;
    static long[] dist;
    static PriorityQueue<Point> pq;
    static class Point{
        int v;
        long cost;
        Point(int v,long cost){
            this.v=v;
            this.cost=cost;
        }
    }
    static List<Point>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        V=Integer.parseInt(st.nextToken());
        E=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());

        list=new ArrayList[V+1];
        for(int i=1;i<=V;i++){
            list[i]=new ArrayList<>();
        }

        for(int i=0;i<E;i++){
            st=new StringTokenizer(br.readLine()," ");
            int from=Integer.parseInt(st.nextToken());
            int to=Integer.parseInt(st.nextToken());
            int cost=Integer.parseInt(st.nextToken());
            list[to].add(new Point(from,cost));
        }

        dist=new long[V+1];
        Arrays.fill(dist,Long.MAX_VALUE);

        pq =new PriorityQueue<>((n1,n2)->Long.compare(n1.cost,n2.cost));
        st=new StringTokenizer(br.readLine()," ");
        for(int i=0;i<K;i++){
            int num=Integer.parseInt(st.nextToken());
            pq.offer(new Point(num,0));
            dist[num]=0;
        }

        dijkstra();

        for(int i=1;i<=V;i++){
            if(ansMax<dist[i]) {
                ansMax=dist[i];
                ansIdx=i;
            }
        }

        System.out.println(ansIdx);
        System.out.println(ansMax);
    }

    private static void dijkstra(){

        while(!pq.isEmpty()){
            Point curr =pq.poll();

            if(dist[curr.v]<curr.cost) continue;

            for(Point next : list[curr.v]){
                if(dist[next.v]>dist[curr.v]+next.cost){
                    dist[next.v]=dist[curr.v]+next.cost;
                    pq.offer(new Point(next.v,dist[next.v]));
                }
            }
        }
    }

}