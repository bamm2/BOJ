import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();

        Map<String,Long> map =new TreeMap<>();
        double cnt = 0;
        String str;
        while ( (str=br.readLine())!=null && !str.isEmpty()){
            cnt++;
            map.put(str,map.getOrDefault(str,0L)+1);
        }

        for(Map.Entry<String,Long> entry : map.entrySet() ){
            String format = String.format("%.4f", entry.getValue() / cnt * 100 );
            sb.append(entry.getKey()).append(" ").append(format).append('\n');
        }
        System.out.println(sb);
    }
}