import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    static int N ;
    static List<Long> list =new ArrayList<>(); // 인덱스 : 감소하는 수의 작은 번째 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N=Integer.parseInt(br.readLine());

        for(int i=0;i<10;i++){ // 맨 앞자리가 0 ~ 9
            solve(i,0);
        }

        Collections.sort(list);

        if(list.size()<=N-1) System.out.println(-1);
        else System.out.println(list.get(N-1));

    }

    private static void solve(long num, int cnt) {
        if(cnt>10) return;

        list.add(num);
        long mod = num%10;

        for(int i=0;i<mod ; i++){
            solve((num*10 + i ),cnt+1);
        }

    }
}