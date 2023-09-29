import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N =Integer.parseInt(br.readLine());
        
        Deque<Integer> deque =new ArrayDeque<>();
        for(int i=1;i<=N;i++){
            deque.offer(i);
        }

        int time = 1;
        while(deque.size()!=1){
            long turn = (int) (pow(time)%deque.size());

            if(turn==0) deque.pollLast();
            else{
                while(turn>0){
                    turn--;
                    Integer poll = deque.poll();
                    if(turn==0) break;
                    deque.offer(poll);
                }
            }
            time++;
        }
        System.out.println(deque.poll());
    }
    
    private static long pow (long num){
        return (long)Math.pow(num,3);
    }
}