import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s =br.readLine();

        int[] count = new int[10];
        int sum =0;
        for(int i=0;i<s.length();i++){
            int num = s.charAt(i)-'0';
            count[num]++;
            sum+=num;
        }
        StringBuilder sb =new StringBuilder();
        if(sum%3!=0) sb.append(-1);
        else {
            for(int i=9;i>=0;i--){
                if(count[i]==0) continue;
                while(count[i]-->0){
                    sb.append(i);
                }
            }
            if(sb.charAt(sb.length()-1)!='0'){
                sb.setLength(0);
                sb.append(-1);
            }
        }
        System.out.println(sb);

    }
}