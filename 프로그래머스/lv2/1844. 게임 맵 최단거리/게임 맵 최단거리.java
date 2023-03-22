import java.util.Queue;
import java.util.ArrayDeque;

class Solution {
    
    static int[][] dir={{-1,0},{1,0},{0,1},{0,-1}};
    static class Point{
        int r ,c,cnt;
        Point(int r,int c,int cnt){
            this.r=r;
            this.c=c;
            this.cnt=cnt;
        }
    }
    static int R,C;
    static boolean[][] visited;
    
    public int solution(int[][] maps) {
        int answer = 0;
        
       
        R=maps.length;
        C=maps[0].length;
        
        Queue<Point> q= new ArrayDeque(); 
        q.offer(new Point(0,0,1));
        visited=new boolean[R][C];
        visited[0][0]=true;
        int ans=Integer.MAX_VALUE;
        while(!q.isEmpty()){
            Point curr=q.poll();
            
            if(curr.r==R-1 && curr.c==C-1){
                ans=ans>curr.cnt?curr.cnt:ans;
            }
            
            for(int d=0;d<4;d++){
                int nr=curr.r+dir[d][0];
                int nc=curr.c+dir[d][1];
                if(isOut(nr,nc)) continue;
                if(visited[nr][nc]) continue;
                if(maps[nr][nc]==0) continue;
                    visited[nr][nc]=true;
                    // maps[nr][nc]=curr.cnt+1;
                    q.offer(new Point(nr,nc,curr.cnt+1));
                  
            }
        }
        
        if(!visited[R-1][C-1]) answer=-1;
        else answer=ans;
        
        return answer;
        
    }
    
    private static boolean isOut(int r,int c){
        return r<0 || c<0 || r>=R || c>=C;
    }
}