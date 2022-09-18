import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] sarr=br.readLine().toCharArray();
        String chk =br.readLine();
        int[] chkarr=new int[chk.length()];
        for(int i=0;i<chk.length();i++){
            chkarr[i]=chk.charAt(i)-'a'+1; // 인덱스가 아닌 순서 체크 인덱스가 0부터 시작이니 +1
        }
        String ans="";
        for(int i=0;i<sarr.length;i++){
            if(sarr[i]==' ') {
                ans+=" ";
                continue;
            }
           int tmp=(int)(sarr[i]-'a')-chkarr[i%chkarr.length];
           if( tmp<0){
               ans+=(char)(tmp+123);
           }else{
               ans+=(char)(tmp+97);
           }
        }

        System.out.println(ans);
    }
}