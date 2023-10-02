import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int arr[],N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N];

            int[] count =new int[10_001];
            PriorityQueue<Integer> pq =new PriorityQueue<>((o1, o2) -> Integer.compare(o2,o1));
            st=new StringTokenizer(br.readLine()," ");
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                pq.offer(arr[i]);
                count[arr[i]]++;
            }

            long sum = 0;
            int max = pq.poll();
            count[max]--;
            Queue<Integer> q = new ArrayDeque<>();
            for (int i = 0; i < N; i++) {

                if (arr[i] != max) {
                    q.offer(arr[i]);
                    count[arr[i]]--;
                } else {
                    while (!q.isEmpty()) {
                        sum += max-q.poll();
                    }

                    if(count[max]>0){
                        count[max]--;
                    }else{
                        while(!pq.isEmpty()){
                            int poll =pq.poll();
                            if(count[poll]>0){
                                max=poll;
                                count[max]--;
                                break;
                            }
                        }
                    }
                }
            }
            sb.append(sum).append('\n');
        }

        System.out.println(sb);

    }
}