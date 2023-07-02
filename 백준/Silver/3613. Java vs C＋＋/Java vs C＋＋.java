import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String ans ="Error!";

        boolean hasUnderBar = false;
        boolean hasBigAlp= false;
        if(s.contains("_")) hasUnderBar=true;
        if(isBigAlp(s)) hasBigAlp =true;

        if(hasUnderBar && !hasBigAlp){
            ans=cToJava(s);
        }else if(!hasUnderBar && !isFirstBigAlp(s) ){
            ans=javaToC(s);
        }

        if(nopeC(s)) ans="Error!";

        System.out.println(ans);

    }

    private static String cToJava(String s){
        StringBuilder sb =new StringBuilder();
        boolean nextUnderBar = false;
        for(int i=0;i<s.length();i++){
            char c =s.charAt(i);
            if(c=='_') {
                if(nextUnderBar) return "Error!";
                nextUnderBar = true;
                continue;
            }
            if(nextUnderBar){
                c=(char)(c-32);
                nextUnderBar=false;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    private static String javaToC(String s){
        StringBuilder sb =new StringBuilder();
        for(int i=0;i<s.length();i++){
            if(isBigAlp(s,i)){
                char c =(char)(s.charAt(i)+32);
                sb.append("_").append(c);
            }else{
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    private static boolean nopeC(String s){
        return s.charAt(0)=='_' || s.charAt(s.length()-1)=='_';
    }

    private static boolean isBigAlp(String s){
        for(int i=0;i<s.length();i++){
           if(isBigAlp(s,i)) return true;
        }
        return false;
    }

    private static boolean isFirstBigAlp(String s){
        return isBigAlp(s,0);
    }

    private static boolean isBigAlp(String s ,int idx){
        return 'A'<=s.charAt(idx) && s.charAt(idx) <='Z';
    }
}