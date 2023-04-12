import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s =br.readLine();

        String target = br.readLine();

        int len= target.length();
        int slen=s.length();

        int ans =0;
        for(int i=0;i<=slen-len;i++){
            int idx=i;
            int cnt=0;
            while(true) {
                if (idx+len > slen) break;
                String chk = s.substring(idx, idx + len);
                if(target.equals(chk)) {
                    cnt++;
                    idx+=len;
                }else {
                    idx++;
                }
            }
            if(ans<cnt) ans=cnt;
        }
        System.out.println(ans);
    }
}