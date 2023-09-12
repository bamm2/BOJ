import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();
        StringTokenizer st;

        int T=Integer.parseInt(br.readLine());
        while (T-->0){
            int  N =Integer.parseInt(br.readLine());
            Map<String,Integer> map= new HashMap<>();
            for(int i=0;i<N;i++){
                st=new StringTokenizer(br.readLine()," ");
                String value =st.nextToken();
                String key =st.nextToken();
                map.put(key,map.getOrDefault(key,0)+1);
            }
            int ans = 1;
            for(Map.Entry<String,Integer> entry : map.entrySet()){
               ans *=  (entry.getValue()+1);
            }

           sb.append(--ans).append('\n');
        }
        System.out.println(sb);

    }
}