import java.util.HashSet;

class Solution {
   
        static int curR, curC,ans;
        static HashSet<String> hs =new HashSet<>();

    public int solution(String dirs) {
        
          curR = 5;
            curC = 5;

            for(int i=0;i<dirs.length();i++){
                move(dirs.charAt(i));
            }

            return ans;
    }
    
       private static void move(char c) {
            switch (c) {
                case 'U':
                    if (!isOut(curR + 1)) {
                        String tmp = ""+curR+curC+(curR+1)+curC;
                        String tmp2 = ""+(curR+1)+curC+curR+curC;
                        curR++;
                        if(!hs.contains(tmp) && !hs.contains(tmp2)){
                            hs.add(tmp);
                            hs.add(tmp2);
                            ans++;
                        }
                    }
                    break;
                case 'D':
                    if (!isOut(curR - 1)) {
                        String tmp = ""+curR+curC+(curR-1)+curC;
                        String tmp2 = ""+(curR-1)+curC+curR+curC;
                        curR--;
                        if(!hs.contains(tmp) && !hs.contains(tmp2)){
                            hs.add(tmp);
                            hs.add(tmp2);
                            ans++;
                        }
                    }
                    break;
                case 'R':
                    if (!isOut(curC + 1)) {
                        String tmp = ""+curR+curC+curR+(curC+1);
                        String tmp2 = ""+curR+(curC+1)+curR+curC;
                        curC++;
                        if(!hs.contains(tmp) && !hs.contains(tmp2)){
                            hs.add(tmp);
                            hs.add(tmp2);
                            ans++;
                        }
                    }
                    break;
                case 'L':
                    if (!isOut(curC - 1)) {
                        String tmp = ""+curR+curC+curR+(curC-1);
                        String tmp2 = ""+curR+(curC-1)+curR+curC;
                        curC--;
                        if(!hs.contains(tmp) && !hs.contains(tmp2)){
                            hs.add(tmp);
                            hs.add(tmp2);
                            ans++;
                        }
                    }
                    break;
            }
        }

        private static boolean isOut(int v) {
            return v < 0 || v < 0 || v > 10 || v > 10;
        }

    
}