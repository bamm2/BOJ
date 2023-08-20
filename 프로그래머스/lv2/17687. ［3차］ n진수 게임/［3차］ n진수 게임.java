class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb =new StringBuilder();
            int cnt = 0;
            while(sb.length()<t*m){
                sb.append(convert(n,cnt++));
            }
            String ans = "";
            for(int i=p-1;i<sb.length();i+=m){
                ans+=sb.charAt(i);
                if(ans.length()==t) break;
            }
        return ans;
    }
    
      private static String convert(int n,int num){
            String str ="";
            if(num==0) str="0";
            while(num!=0){
                int mod = num%n;
                char c = (char)(mod+48>=58 ? mod+55 : mod+48 );
                str= c+str;
                num/=n;
            }
            return str;
        }
}