class Solution {
    
     static char[] alphabet = {'A','E','I','O','U'};
        static int ans , cnt;
        static String findStr ="";
    
    public int solution(String word) {
          findStr=word;

            solve("");
            return ans;
    }
    private static void solve(String str){
            if(str.length()>5) return;

            if(str.equals(findStr)){
                ans = cnt;
                return;
            }

            cnt++;
            for(int i=0;i<5;i++){
                solve(str+alphabet[i]);
            }
        }
}