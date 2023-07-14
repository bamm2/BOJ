import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        int N =Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq =new PriorityQueue<>((a,b)->Integer.compare(b,a));

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<N;j++){
                int num =Integer.parseInt(st.nextToken());
                pq.offer(num);
            }
        }

        int ans = 0;
        while(N-->1){
            pq.poll();
        }

        ans=pq.peek();

        System.out.println(ans);

    }
}