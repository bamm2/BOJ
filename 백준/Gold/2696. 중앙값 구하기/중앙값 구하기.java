import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();
        StringTokenizer st ;

        int T=Integer.parseInt(br.readLine());

        while(T-->0){
            int N =Integer.parseInt(br.readLine());
            int nextLn  = N/10;

            PriorityQueue<Integer> pq =new PriorityQueue<>((num1,num2)->Integer.compare(num1,num2));

            sb.append(N/2+1).append('\n');

            int cnt =0 ;
            for(int i=0;i<=nextLn; i++) {
                st = new StringTokenizer(br.readLine()," ");
                while(st.hasMoreTokens()){
                    int num =Integer.parseInt(st.nextToken());
                    pq.offer(num);
                    List<Integer> tmp =new ArrayList<>();
                    int chk = pq.size()/2 ;
                    if(pq.size()%2!=0){
                        while(chk-->0){
                           tmp.add(pq.poll());
                        }
                        sb.append(pq.peek()).append(" ");
                        for(Integer number : tmp) pq.offer(number);
                        cnt++;
                        if(cnt%10==0) sb.append('\n');
                    }
                }
            }
            sb.append('\n');
        }

        System.out.println(sb);

    }
}