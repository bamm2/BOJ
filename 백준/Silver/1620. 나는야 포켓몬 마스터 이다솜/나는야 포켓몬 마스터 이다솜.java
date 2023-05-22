import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");

        int N =Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());

        HashMap<String,Integer> map =new HashMap<>();
        List<String> list =new ArrayList<>();
        for(int i=1;i<=N;i++){
            String s=br.readLine();
            map.put(s,i);
            list.add(s);
        }

        StringBuilder sb =new StringBuilder();

        for(int i=0;i<M;i++){
            String s =br.readLine();
            if(isString(s)){
                sb.append(map.get(s)).append('\n');
            }else{
               sb.append(list.get(Integer.parseInt(s)-1)).append('\n');
            }
        }
        System.out.println(sb.toString().trim());
    }

    private static boolean isString(String s){
       return  0<= s.charAt(0)-'A' && s.charAt(0)-'Z'<26 ;
    }
}