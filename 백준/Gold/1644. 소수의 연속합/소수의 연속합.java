import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<Integer> primeList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num =Integer.parseInt(br.readLine());
        primeList =new ArrayList<>();

        for(int i=2;i<=num;i++){
            if(isPrime(i)) primeList.add(i);
        }

        int left=0;
        int right=0;
        int sum = 0;
        int count =0 ;
        while (left<=right){
            if(sum<num){
                if(right== primeList.size()) break;
                sum+=primeList.get(right);
                right++;
            }else if(sum>=num){
                if(sum==num) count++;
                sum-=primeList.get(left);
                left++;
            }
        }
        System.out.println(count);
    }

    private static boolean isPrime(int num){
        for(int i=2;i<=Math.sqrt(num);i++){
            if(num%i==0) return false;
        }
        return true;
    }
}