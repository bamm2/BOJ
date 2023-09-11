import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();
        StringTokenizer st;

        int N =Integer.parseInt(br.readLine());
        Map<String,Integer> map =new TreeMap<>(Collections.reverseOrder());

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine()," ");
            String name= st.nextToken();
            String inOrOut = st.nextToken();
            if(inOrOut.equals("leave")) map.remove(name);
            else map.put(name,0);
        }

        for(Map.Entry<String,Integer> curr : map.entrySet()){
            sb.append(curr.getKey()).append('\n');
        }

        System.out.println(sb);
    }
}