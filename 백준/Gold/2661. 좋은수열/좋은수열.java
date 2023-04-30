import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] nums={1,2,3};
    static int N;
    static String ans="";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N=Integer.parseInt(br.readLine());

        solve(1,"1");

        System.out.println(ans);
    }
    private static void solve(int cnt,String s){
        if(N==cnt && !isChk(s)){
            ans=s;
            return;
        }

        if(isChk(s)) return;

        for(int d=0;d<=2;d++){
            if(!ans.equals("")) break;
            if(s.charAt(cnt-1)-'0'==nums[d]) continue;
            solve(cnt+1,s+nums[d]);
        }
    }

    private static boolean isChk(String s){

            for(int i=1;i<=s.length()/2;i++){
                if(s.substring(s.length()-i).equals(s.substring((s.length()-2*i),s.length()-i)))
                    return true;
            }

        return false;
    }


}
