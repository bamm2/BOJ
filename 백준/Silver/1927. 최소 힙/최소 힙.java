import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();

        PriorityQueue<Integer> pq =new PriorityQueue<>((num1,num2)->Integer.compare(num1,num2));

        int T =Integer.parseInt(br.readLine());

        while(T-->0){
            int num=Integer.parseInt(br.readLine());

            if(num==0){
                if(pq.isEmpty()){
                    sb.append("0").append('\n');
                }else{
                    sb.append(pq.poll()).append('\n');
                }
            }else{
                pq.offer(num);
            }
        }

        System.out.println(sb);

    }
}