import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int V,E,stPoint,edPoint,dis[];
    static boolean[] visited;
    static class Point implements Comparable<Point> {
        int to,w;
        Point(int to,int w){
            this.to=to;
            this.w=w;
        }

        @Override
        public int compareTo(Point o) {
            return this.w-o.w;
        }
    }
    static List<Point>[] list;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        V=Integer.parseInt(br.readLine());
        E=Integer.parseInt(br.readLine());

        list=new ArrayList[V+1];
        for(int i=0;i<=V;i++){
            list[i]=new ArrayList<>();
        }

        for(int i=0;i<E;i++){
            st=new StringTokenizer(br.readLine()," ");
            int start=Integer.parseInt(st.nextToken());
            int end=Integer.parseInt(st.nextToken());
            int weight=Integer.parseInt(st.nextToken());
            list[start].add(new Point(end,weight));
        }

        st=new StringTokenizer(br.readLine()," ");
        stPoint=Integer.parseInt(st.nextToken());
        edPoint=Integer.parseInt(st.nextToken());

        dis=new int[V+1];
        Arrays.fill(dis,Integer.MAX_VALUE);
        dis[stPoint]=0;

        visited=new boolean[V+1];

        PriorityQueue<Point> pq=new PriorityQueue<>();

        pq.add(new Point(stPoint,0));

        while(!pq.isEmpty()){
            Point curr=pq.poll();

            if(visited[curr.to]) continue;
            visited[curr.to]=true;


            for(Point node : list[curr.to]){
                if(!visited[node.to] && dis[node.to]>dis[curr.to]+node.w){
                    dis[node.to]=dis[curr.to]+node.w;
                    pq.add(new Point(node.to,dis[node.to]));
                }
            }
        }
        System.out.println(dis[edPoint]);

    }
}