import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Serial implements Comparable<Serial>{
        String s ;
        int length;
        int sum;
        Serial(String s,int length,int sum){
            this.s=s;
            this.length=length;
            this.sum=sum;
        }

        @Override
        public int compareTo(Serial o) {
            if(this.length!=o.length){
                return this.length-o.length;
            }else{
                if(this.sum!=o.sum){
                    return this.sum-o.sum;
                }else{
                    if(comp(this.s,o.s)=='o'){
                        return -1;
                    }else if(comp(this.s,o.s)=='s'){
                        return 1;
                    }
                }
                return -1;
            }
        }
    }

    static Serial[] arr ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N=Integer.parseInt(br.readLine());

        arr=new Serial[N];
        for(int i=0;i<N;i++){
            String s = br.readLine();
            arr[i]=new Serial(s,s.length(),sum(s));
        }

        Arrays.sort(arr);

        StringBuilder sb =new StringBuilder();
        for(int i=0;i<N;i++){
            sb.append(arr[i].s).append('\n');
        }

        System.out.println(sb.toString().trim());

    }

    private static int sum(String s ){
        int sum =0;
        for(int i=0;i<s.length();i++){
            int num =s.charAt(i)-'0';
            if(0<=num && num<10){
                sum+=num;
            }
        }
        return sum;
    }

    private static char comp(String s, String o){
        for(int i=0;i<s.length();i++){
           if(s.charAt(i) > o.charAt(i)){
               return 's';
           }else if(s.charAt(i) < o.charAt(i)  ){
               return 'o';
           }
        }
        return 'n';
    }
}
