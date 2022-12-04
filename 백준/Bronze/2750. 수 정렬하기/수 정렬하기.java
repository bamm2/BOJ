import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb= new StringBuilder();

        int N=Integer.parseInt(br.readLine());
        List<Integer> list =new LinkedList<>();
        for(int i=0;i<N;i++){
            list.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(list);
        for(int i=0;i<list.size();i++){
            sb.append(list.get(i)).append('\n');
        }
        System.out.println(sb);
    }
}