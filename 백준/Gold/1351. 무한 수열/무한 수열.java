import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static Map<Long,Long> map =new HashMap<>();
    static int P,Q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");

        long N=Long.parseLong(st.nextToken());
        P=Integer.parseInt(st.nextToken());
        Q=Integer.parseInt(st.nextToken());

        map.put(0L,1L);
        System.out.println(recur(N));
    }
    
    private static long recur(long num){
        if(map.containsKey(num)) return map.get(num);
        long prevDivP =  Math.floorDiv(num,P);
        long prevDivQ =  Math.floorDiv(num,Q);
        map.put(num, recur(prevDivP)+recur(prevDivQ) );
        return map.get(num);
    }
}