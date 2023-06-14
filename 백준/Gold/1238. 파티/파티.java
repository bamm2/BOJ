import java.io.*;
import java.util.*;

public class Main {

    static int V,E,goal,dis[];
    static int ans = -1 ;
    static boolean[] visited;
    static class Point implements Comparable<Point>{
        int ed,time;
        Point(int ed,int time){
            this.ed=ed;
            this.time=time;
        }

        @Override
        public int compareTo(Point o) {
            return this.time-o.time;
        }
    }
    static List<Point>[] list,reversList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st ;

        st=new StringTokenizer(br.readLine()," ");
        V=Integer.parseInt(st.nextToken()); // 정점
        E=Integer.parseInt(st.nextToken()); // 간선
        goal=Integer.parseInt(st.nextToken())-1; // 경유지

        list=new ArrayList[V];
        reversList=new ArrayList[V];
        for(int i=0;i<V;i++){
            list[i]=new ArrayList<>();
            reversList[i]=new ArrayList<>();
        }

        for(int i=0;i<E;i++){
            st=new StringTokenizer(br.readLine()," ");
            int start= Integer.parseInt(st.nextToken())-1;
            int end =Integer.parseInt(st.nextToken())-1;
            int time =Integer.parseInt(st.nextToken());
            list[start].add(new Point(end,time));
            reversList[end].add(new Point(start,time));
        }


        int[] distance = move(goal,list);
        int[] reversDis = move(goal,reversList);

        for(int i=0;i<V;i++){
            if(ans<distance[i]+reversDis[i])
                ans = distance[i]+reversDis[i];
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }

    private static int[] move(int st,List<Point>[] list){
        dis=new int[V];
        visited=new boolean[V];
        Arrays.fill(dis,Integer.MAX_VALUE);
        dis[st]=0;

        PriorityQueue<Point> pq =new PriorityQueue<>();
        pq.add(new Point(st,0));

            while (!pq.isEmpty()) {
                Point curr = pq.poll();

                if (visited[curr.ed]) continue;
                visited[curr.ed] = true;

                for (Point points : list[curr.ed]) {
                    if (dis[points.ed] > dis[curr.ed] + points.time) {
                        dis[points.ed] = dis[curr.ed] + points.time;
                        pq.add(new Point(points.ed, dis[points.ed]));
                    }
                }
            }

        return dis;

    }

}
