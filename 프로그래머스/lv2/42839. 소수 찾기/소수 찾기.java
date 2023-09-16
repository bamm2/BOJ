import java.util.HashSet;

class Solution {
    
    static boolean[] visited;
    static HashSet<Integer> hs =new HashSet<>();
    
    public int solution(String numbers) {
           int L = numbers.length();
            visited =new boolean[L];
            for(int i=1;i<=L;i++) {
                solve(0,i, numbers, "");
            }

            return hs.size();
    }
     private void solve(int idx, int goal,String s,String chk) {

            if(idx==goal){
                System.out.println("chk = " + chk);
                int check = Integer.parseInt(chk);
                if(isPrime(check)) hs.add(check);
                return;
            }

            for(int i=0;i<s.length();i++){
                if(visited[i]) continue;
                visited[i]=true;
                solve(idx+1,goal,s,chk+s.charAt(i));
                visited[i]=false;
            }

        }

        private static boolean isPrime(int number){
            if(number<=1) return false;
            for(int i=2;i<=Math.sqrt(number);i++){
                if(number%i==0) return false;
            }
            return true;
        }
}