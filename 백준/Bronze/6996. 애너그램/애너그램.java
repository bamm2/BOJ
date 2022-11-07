import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N=Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine()," ");
            String A =st.nextToken();
            String B =st.nextToken();
            int[] chk=new int[26];
            for(int j=0;j<A.length();j++){
                chk[A.charAt(j)-'a']++;
            }
            boolean sign=true;
            for(int j=0;j<B.length();j++){
                if(A.length()!=B.length()) {
                    sign=false;
                    break;
                }
                chk[B.charAt(j)-'a']--;
                if(chk[B.charAt(j)-'a']<0){
                    sign=false;
                    break;
                }
            }
            if(sign) System.out.println(A+" & "+B+" are anagrams.");
            else System.out.println(A+" & "+B+" are NOT anagrams.");
        }
    }
}