import java.util.Queue;
import java.util.ArrayDeque;

class Solution {
    
    static class Point{
            int idx,num;
            Point(int idx,int num){
                this.idx=idx;
                this.num=num;
            }
        }
    
    public int solution(int[] priorities, int location) {
      int[] counting = new int[10];

            Queue<Point> q =new ArrayDeque<>();

            for(int i=0;i< priorities.length;i++){
                counting[priorities[i]]++;
                q.offer(new Point(i,priorities[i]));
            }

            int answer = 0;
            int max = 9;

            loop:
            while(!q.isEmpty()){
                max = findMax(counting);

                while(true){
                    Point curr =q.poll();
                    if(curr.num == max && curr.idx == location){
                        ++answer;
                        break loop;
                    }else if(curr.num==max){
                        counting[curr.num]--;
                        ++answer;
                        break;
                    }

                    q.offer(curr);
                }
            }
        return answer;
    }
     private static int findMax(int[] arr){
            for(int i=9;i>=1;i--){
                if(arr[i]>0) return i;
            }
            return -1;
        }
}