import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static String[] comp = {"c=","c-","dz=","d-","lj","nj","s=","z="};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s= br.readLine();
        
        for(int i=0 ; i<comp.length;i++){
            if(s.contains(comp[i])){
               s = s.replaceAll(comp[i],"ㄱ");
            }
        }

        System.out.println(s.length());
    }
}
