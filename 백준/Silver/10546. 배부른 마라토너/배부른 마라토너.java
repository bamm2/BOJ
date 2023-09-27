import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        HashSet<String> hs =new HashSet<>();
        for(int i=0;i<2*N-1;i++){
            String str= br.readLine();
            if(hs.contains(str)) hs.remove(str);
            else hs.add(str);
        }

        Object[] objects = hs.toArray();
        System.out.println(objects[0]);

    }
}
