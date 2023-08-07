import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();

        int T =Integer.parseInt(br.readLine());

        PriorityQueue<Integer> smallerPq = new PriorityQueue<>((n1,n2)->Integer.compare(n2,n1));
        PriorityQueue<Integer> biggerPq =new PriorityQueue<>((n1,n2)->Integer.compare(n1,n2));

        int cnt = 0;

        while(T-->0) {
            int num = Integer.parseInt(br.readLine());

            cnt++;

            if (cnt == 1) {
                sb.append(num).append('\n');
                smallerPq.offer(num);
            } else if( cnt == 2 ){
                if(num>=smallerPq.peek()){
                    biggerPq.offer(num);
                }else{
                   biggerPq.offer(smallerPq.poll());
                   smallerPq.offer(num);
                }
                sb.append(smallerPq.peek()).append('\n');
            }else {
                if(num > biggerPq.peek()){
                    biggerPq.offer(num);
                }else{
                    smallerPq.offer(num);
                }

                if(biggerPq.size()==smallerPq.size()){
                }else if(biggerPq.size()>smallerPq.size()){
                    smallerPq.offer(biggerPq.poll());
                }else if(smallerPq.size()-2==biggerPq.size()){
                    biggerPq.offer(smallerPq.poll());
                }
                sb.append(smallerPq.peek()).append('\n');

            }
        }

        System.out.println(sb);

    }
}