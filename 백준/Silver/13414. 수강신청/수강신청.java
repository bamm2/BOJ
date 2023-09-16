import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int K = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        Map<String,Integer> map =new HashMap<>();
        String[] arr = new String[L];
        for(int i=0;i<L;i++){
            String key =br.readLine();
            map.put(key, i);
        }

        for(Map.Entry<String,Integer> entry : map.entrySet()){
            arr[entry.getValue()] = entry.getKey();
        }

        int cnt =0;
        for(int i=0;i<L;i++){
            if(arr[i]!=null) {
                sb.append(arr[i]).append('\n');
                cnt++;
            }
            if(cnt==K) break;
        }

        System.out.println(sb);

    }
}