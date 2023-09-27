import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N= Integer.parseInt(br.readLine());

        TreeMap<String,Integer> tm = new TreeMap<>(Comparator.reverseOrder());

        for(int i=0;i<N;i++){
            String str =br.readLine();
            tm.put(str,tm.getOrDefault(str,0)+1);
        }

        String ans ="";
        int cnt =0;
        for(Map.Entry<String,Integer> entry : tm.entrySet()){
            if(entry.getValue()>=cnt){
                cnt=entry.getValue();
                ans=entry.getKey();
            }
        }

        System.out.println(ans);

    }
}
