import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");
        int A=Integer.parseInt(st.nextToken());
        int B=Integer.parseInt(st.nextToken());

        int[] count = new int[100_000_001];

        st=new StringTokenizer(br.readLine()," ");
        for(int i=0;i<A;i++){
            count[Integer.parseInt(st.nextToken())]++;
        }
        st=new StringTokenizer(br.readLine()," ");
        for(int i=0;i<B;i++){
            count[Integer.parseInt(st.nextToken())]++;
        }

        int ans =0;
        int cnt=0;
        for(int i=0;i<count.length;i++){
            if(count[i]==0) continue;
            if(count[i]==1) ans++;
            cnt+=count[i];
            if(cnt==A+B) break;
        }

        System.out.println(ans);
    }
}
