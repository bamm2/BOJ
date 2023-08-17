import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();
        StringTokenizer st ;

        int L,P,V; // 1<L<P<V
        int idx = 0;
        while(true){
            st=new StringTokenizer(br.readLine()," ");
            L=Integer.parseInt(st.nextToken());
            P=Integer.parseInt(st.nextToken());
            V=Integer.parseInt(st.nextToken());
            if(L==0 && P ==0 && V ==0) break;

            int div = V/P;
            int mod = V%P;

            int remain = mod>=L ? L : mod;

            sb.append("Case ").append(++idx).append(": ").append(div*L+remain).append('\n');
        }
        System.out.println(sb);
    }
}

