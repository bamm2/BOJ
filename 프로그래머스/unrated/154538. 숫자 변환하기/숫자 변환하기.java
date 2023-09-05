import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {

      static class Point{
            int num , cnt;
            Point(int num ,int cnt){
                this.num=num;
                this.cnt=cnt;
            }
        }

    
    public int solution(int x, int y, int n) {
            int[] count = new int[y+1];
            Arrays.fill(count,Integer.MAX_VALUE);

            Queue<Point> q = new ArrayDeque<>();
            q.offer(new Point(x,0));

            while (!q.isEmpty()){
                Point curr =q.poll();
                int[] next = {curr.num+n,curr.num*2,curr.num*3};
                for(int d=0;d<3;d++){
                    int nextPos = next[d];
                    if(nextPos>y) continue;
                    if(count[nextPos]>curr.cnt+1){
                        count[nextPos] =curr.cnt+1;
                        q.offer(new Point(nextPos,curr.cnt+1));
                    }
                }
            }
            if(count[y]==Integer.MAX_VALUE) count[y]=-1;
            if(x==y) count[y]=0;    
        
            return count[y];
    }
}