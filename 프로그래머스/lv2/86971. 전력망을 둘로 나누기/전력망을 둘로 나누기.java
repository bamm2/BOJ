import java.util.*;

class Solution {
     static int N, min = Integer.MAX_VALUE;
        static boolean[] visited;
        static List<Integer>[] list;

        public int solution(int n, int[][] wires) {
            N=n;

            list=new ArrayList[N+1];
            for(int i=1;i<=n;i++){
                list[i]=new ArrayList<>();
            }

            for(int i=0;i<n-1;i++){
                int from = wires[i][0];
                int to = wires[i][1];
                list[from].add(to);
                list[to].add(from);
            }

            for(int i=0;i<n-1;i++){
                int v1= wires[i][0];
                int v2 = wires[i][1];
                int fromCnt =searchConnectCnt(v1,v2);
                int toCnt =searchConnectCnt(v2,v1);
                int diff = Math.abs(fromCnt-toCnt);
                if(min>diff){
                    min=diff;
                }
            }

            return min;
        }

        private int searchConnectCnt(int v, int disConnectV) {
            visited=new boolean[N+1];
            int cnt = 1;
            Queue<Integer> q =new ArrayDeque<>();
            q.offer(v);
            visited[disConnectV]=true;
            visited[v]=true;

            while(!q.isEmpty()){
                Integer curr =q.poll();
                cnt++;
                for(Integer next : list[curr]){
                    if(visited[next]) continue;
                    visited[next]=true;
                    q.offer(next);
                }
            }

            return cnt;
        }
}