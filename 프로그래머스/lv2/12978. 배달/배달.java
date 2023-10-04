import java.util.*;

class Solution {
    
        static class Point{
            int v , w;
            Point(int v,int w){
                this.v=v;
                this.w=w;
            }
        }
        static List<Point>[] list;

        public int solution(int N, int[][] road, int K) {
                   list=new ArrayList[N+1];
            for(int i=1;i<=N;i++){
                list[i]=new ArrayList<>();
            }

            for(int i=0;i<road.length;i++){
                list[road[i][0]].add(new Point(road[i][1],road[i][2]));
                list[road[i][1]].add(new Point(road[i][0],road[i][2]));
            }

            int[] dist =new int[N+1];
            Arrays.fill(dist,Integer.MAX_VALUE);
            Queue<Point> q =new ArrayDeque<>();
            q.offer(new Point(1,0));
            dist[1]=0;

            while(!q.isEmpty()){
                Point curr =q.poll();

                for(Point next : list[curr.v]){
                    if(dist[next.v]>dist[curr.v]+next.w){
                        dist[next.v]=dist[curr.v]+next.w;
                        q.offer(new Point(next.v,dist[next.v]));
                    }
                }
            }

            int ans =0 ;
            for(int i=1;i<dist.length;i++){
                if(dist[i]<=K) ans++;
            }

            return ans;
        }
}