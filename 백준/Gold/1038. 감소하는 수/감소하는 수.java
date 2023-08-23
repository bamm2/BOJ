import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    static int n;
    static List<Long> list =new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        for(int i=0;i<=9;i++){
            solve(i,0);
            list.add((long)i);
        }

        Collections.sort(list);


        if(list.size()<=n) System.out.println(-1);
        else System.out.println(list.get(n));

    }

    private static void solve(long num, int cnt) {

        if(cnt>=10) return;

        int mod =(int)(num%10);

        for (int i = 0; i < mod; i++) {
            solve((num*10)+i,cnt+1);
            list.add( (num*10)+i );
        }
    }

}