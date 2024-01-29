import java.util.*;

class Solution {
    
    static List<Integer>[] list;
    static boolean[] visited;
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        list =new ArrayList[n+1];
        for(int i=0;i<=n;i++){
            list[i]=new ArrayList<>();
        }
        
        for(int i=0;i<edge.length;i++){
            list[edge[i][0]].add(edge[i][1]);
            list[edge[i][1]].add(edge[i][0]);
        }
        
        visited=new boolean[n+1];
        visited[1]=true;
        
        Queue<Integer> q =new ArrayDeque<>();
        q.offer(1);
        
        int max =0 ;
        while(!q.isEmpty()){
            int size = q.size();
            max = q.size();
            while(size-->0){
                int curr = q.poll();
                for(Integer next : list[curr]){
                    if(visited[next]) continue;
                    visited[next]=true;
                    q.offer(next);
                }
            }
        }
        
        return max;
    }
}