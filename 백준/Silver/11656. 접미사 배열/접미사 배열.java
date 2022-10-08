import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Main{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list =new ArrayList<>();

        String s =br.readLine();

        for(int i=0;i<s.length();i++){
            String idx="";
            for(int j=i;j<s.length();j++){
               idx+=s.charAt(j);
            }
            list.add(idx);
        }
        Collections.sort(list);
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }

    }
}
