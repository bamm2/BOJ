import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, arr[] ,removeNodeNumber;
    static List<Integer>[] tree;
    static Queue<Integer> q =new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        tree =new ArrayList[N];
        for(int i=0;i<N;i++ ){
            tree[i]=new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if(arr[i] == -1) q.offer(i);
            else {
                tree[arr[i]].add(i);
            }
        }

        removeNodeNumber = Integer.parseInt(br.readLine());

        System.out.println(bfs());
    }

    private static int bfs() {
        int count = 0;
        while (!q.isEmpty()){
            Integer curr = q.poll();
            if(curr == removeNodeNumber) return 0;
            boolean flag = false;
            for(Integer next : tree[curr]){
                if(next == removeNodeNumber) continue;
                q.offer(next);
                flag=true;
            }
            if(!flag) {
                count++;
            }
        }


        return count;
    }
}