import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N,K,arr[],ans;
    static Queue<Integer> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");

        N=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());

        StringBuilder sb =new StringBuilder();
        if(N==K){
            sb.append(0).append('\n').append(N);
        }else{
            arr=new int[100_001];
            Arrays.fill(arr,-1);
            q=new ArrayDeque<>();
            q.offer(N);
            ans =solve();
            sb.append(ans).append('\n');

            Stack<Integer> stack=new Stack<>();
            stack.push(K);
            int idx=K;
            while(true){
                stack.push(arr[idx]);
                idx=arr[idx];
                if(idx==N) break;
            }
            while(!stack.isEmpty()){
                sb.append(stack.pop()).append(" ");
            }
        }
        System.out.println(sb.toString().trim());
    }

    private static int solve(){
        int depth=0;

        while(!q.isEmpty()) {
            depth++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int curr = q.poll();
                int[] dir = {curr + 1, curr - 1, curr * 2};
                for (int d = 0; d < 3; d++) {
                    if(dir[d]>=0 && dir[d]<=100_000 && arr[dir[d]]==-1){
                        arr[dir[d]]=curr;
                        if(dir[d]==K) return depth;
                        q.offer(dir[d]);
                    }
                }
            }
        }
        return -1;
    }

}