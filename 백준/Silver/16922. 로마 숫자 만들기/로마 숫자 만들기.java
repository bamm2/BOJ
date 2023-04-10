import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] arr ={1,5,10,50};
    static int[] numbers;
    static int N;
    static HashSet<Integer> hs =new HashSet<>();

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N=Integer.parseInt(br.readLine());
        numbers=new int[N];

        solve(0,0);

        System.out.println(hs.size());

    }
    private static void solve(int idx,int start){
        if(idx==N){
            int sum=0;
            for(int i=0;i<N;i++){
                sum+=numbers[i];
            }
            hs.add(sum);
            return;
        }

        for(int i=start;i<4;i++){
            numbers[idx]=arr[i];
            solve(idx+1,i);
        }

    }
}