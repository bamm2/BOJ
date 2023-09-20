import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine()," ");

        String before = st.nextToken();
        String end = st.nextToken();
        String lastEnd = st.nextToken();

        Map<String,Boolean> map =new HashMap<>();

        String str="";
        while ( (str=br.readLine())!=null && !str.isEmpty()){
            String time = str.split(" ")[0];
            String name =str.split(" ")[1];

            if(before.compareTo(time) >= 0) {
                map.put(name,false);
            }else if ( end.compareTo(time) <=0  && lastEnd.compareTo(time) >=0 ){
                if(map.containsKey(name)){
                    map.put(name,true);
                }
            }

        }
        int ans =0;
        for(Map.Entry<String,Boolean> entry : map.entrySet()){
            if(entry.getValue()) ans++;
        }

        System.out.println(ans);

    }
}