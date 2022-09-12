import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int [] table;
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        String s =br.readLine();
        String p =br.readLine();

        table=new int[p.length()];

        System.out.println(KMP(s,p));

        }



    public static int KMP(String s, String p) {
        makeTable(p);
        int sLen = s.length();
        int pLen = p.length();

        int idx = 0;

        for (int i = 0; i < sLen; i++) {
            while (idx > 0 && s.charAt(i) != p.charAt(idx)) {
                idx = table[idx - 1];
            }
            if (s.charAt(i) == p.charAt(idx)) {
                if (idx == pLen - 1) {
                    idx = table[idx];
                    return 1;
                } else {
                    idx += 1;
                }
            }
        }
        return 0;
    }

    public static void makeTable(String p){
        int idx=0;

        for(int i=1;i<p.length();i++){
            while(idx>0 && p.charAt(i)!=p.charAt(idx)){
                idx=table[idx-1];
            }

            if(p.charAt(i)==p.charAt(idx)){
                idx++;
                table[i]=idx;
            }
        }
    }
}