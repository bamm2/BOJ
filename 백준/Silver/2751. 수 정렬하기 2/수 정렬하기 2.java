import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();

        boolean[] chk=new boolean[2000001];

        int T=Integer.parseInt(br.readLine());
        for(int tc=0;tc<T;tc++){
            chk[Integer.parseInt(br.readLine())+1000000]=true;
        }

        for(int i=0;i<2000001;i++){
            if(chk[i])
                sb.append(i-1000000).append('\n');
        }
        System.out.println(sb);
    }
}