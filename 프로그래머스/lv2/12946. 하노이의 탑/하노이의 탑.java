import java.util.ArrayList;
import java.util.List;

class Solution {
   static List<int[]> list =new ArrayList<>();

        public int[][] solution(int n) {

            solve(1,2,3,n);

            int[][] answer=new int[list.size()][2];
            int idx=0;
            for (int[] curr : list){
                answer[idx++]=curr;
            }

            return answer;
        }

        private void solve(int st , int mid ,int ed,int num) {
            if(num==1){
                list.add(new int[]{st,ed});
                return;
            }

            solve(st,ed,mid,num-1);
            list.add(new int[]{st,ed});
            solve(mid,st,ed,num-1);
        }
}