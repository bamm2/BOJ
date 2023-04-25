import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashSet<Integer> hs= new HashSet<>();
        StringTokenizer st ;

        int N=Integer.parseInt(br.readLine());
        List<Integer> list =new ArrayList<>();

        st=new StringTokenizer(br.readLine()," ");
        for(int i=0;i<N;i++){
            int num=Integer.parseInt(st.nextToken());
            if(!hs.contains(num)){
                hs.add(num);
                list.add(num);
            }
        }
        Collections.sort(list);

        StringBuilder sb =new StringBuilder();
        for(Integer num : list){
            sb.append(num).append(" ");
        }

        System.out.println(sb.toString().trim());

    }
}