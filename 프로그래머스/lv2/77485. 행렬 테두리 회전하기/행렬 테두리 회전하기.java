import java.util.ArrayList;
import java.util.List;

class Solution {
     static int R,C,map[][],min;
        static List<Integer> list;

        public int[] solution(int rows, int columns, int[][] queries) {
            R=rows+1;
            C=columns+1;
            map=new int[R][C];

            int num = 0;
            for(int i=1;i<R;i++){
                for(int j=1;j<C;j++){
                    map[i][j]=++num;
                }
            }

            list =new ArrayList<>();

            for(int i=0;i<queries.length;i++){
                min=Integer.MAX_VALUE;
                int sr = queries[i][0]; int sc = queries[i][1];
                int er = queries[i][2]; int ec = queries[i][3];

                turn(sr,sc,er,ec);
                list.add(min);
            }

            int[] ans = list.stream().mapToInt(i -> i).toArray();
            return ans;
        }


        private void turn(int sr, int sc, int er, int ec) {
            int tmp = map[sr][sc];
            int r = sr ; int c = sc;

                while (c++<ec) tmp = swap(tmp,r,c);
                c--;
                while (r++<er) tmp = swap(tmp,r,c);
                r--;
                while (c-->sc) tmp = swap(tmp,r,c);
                c++;
                while (r-->sr) tmp = swap(tmp,r,c);
        }

        private int swap(int tmp, int r, int c) {
            if(min>tmp) min=tmp;
            int temp = map[r][c];
            map[r][c]=tmp;

            return temp;
        }
}