import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<Integer> list =new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();
        StringTokenizer st;

        int T =Integer.parseInt(br.readLine());
        for(int i=2;i<=10000;i++) {
            if(isPrime(i)) list.add(i);
        }

        while (T-->0){
            int first = 0;
            int second = 0;
            int diff = Integer.MAX_VALUE;
            int num =Integer.parseInt(br.readLine());

            for(int i=0;i<list.size();i++){
                if(list.get(i)>num) break;
                for(int j=i;j<list.size();j++){
                    int sum = list.get(i)+list.get(j);
                    int minus = list.get(j)-list.get(i);
                    if(sum==num &&  diff>minus ){
                        first=list.get(i);
                        second=list.get(j);
                        diff=minus;
                    }
                    if(sum>num) break;
                }
            }
            sb.append(first).append(' ').append(second).append('\n');
        }

        System.out.println(sb);

    }

    private static boolean isPrime(int num) {
        for(int i=2;i<=Math.sqrt(num);i++){
            if(num%i==0) return false;
        }
        return true;
    }
}