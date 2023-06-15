import java.io.*;
import java.util.*;

public class Main {

    static int V,E,start,goal,dis[];
    static boolean[] visited;
    static class Point implements Comparable<Point>{
        int v,cost;
        Point(int v, int cost){
            this.v=v;
            this.cost=cost;
        }

        @Override
        public int compareTo(Point o) {
            return this.cost-o.cost;
        }
    }
    static List<Point>[] list;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();
        StringTokenizer st ;

        V=Integer.parseInt(br.readLine());
        E=Integer.parseInt(br.readLine());

        list=new ArrayList[V+1];
        for(int i=1;i<=V;i++){
            list[i]=new ArrayList<>();
        }

        for(int i=0;i<E;i++){
            st=new StringTokenizer(br.readLine()," ");
            int from = Integer.parseInt(st.nextToken());
            int to =Integer.parseInt(st.nextToken());
            int time =Integer.parseInt(st.nextToken());
            list[from].add(new Point(to,time));
        }

        st=new StringTokenizer(br.readLine()," ");
        start=Integer.parseInt(st.nextToken());
        goal=Integer.parseInt(st.nextToken());

        visited=new boolean[V+1];
        dis=new int[V+1];
        Arrays.fill(dis,Integer.MAX_VALUE);

        dis[start] = 0;
        int[] chkIdx =new int[V+1];
        chkIdx[start] = 0 ;

        PriorityQueue<Point> pq =new PriorityQueue<>();
        pq.add(new Point(start,0));

        while(!pq.isEmpty()){
            Point curr =pq.poll();

            if(visited[curr.v]) continue;
            visited[curr.v] = true;

            for(Point next : list[curr.v]){
                if(dis[next.v]>dis[curr.v]+next.cost) {
                    dis[next.v] = dis[curr.v] + next.cost;
                    chkIdx[next.v]=curr.v;
                    pq.add(new Point(next.v,dis[next.v]));
                }
            }
        }

        sb.append(dis[goal]).append('\n');

        Stack<Integer> stack =new Stack<>();
        stack.push(goal);
        int idx = goal ;
       while(true) {
           if(chkIdx[idx] == 0) break;
           stack.push(chkIdx[idx]);
           idx=chkIdx[idx];
        }

       sb.append(stack.size()).append('\n');
       while(!stack.isEmpty()){
           int num = stack.pop();
           sb.append(num).append(" ");
       }

        System.out.println(sb.toString().trim());

        br.close();

    }
}
