import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;
    static int depth;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        depth = Integer.parseInt(br.readLine());

        arr = new int[(int) Math.pow(2, depth) - 1];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        makeTree(arr.length / 2);
        System.out.println(sb);
    }

    private static void makeTree(int rootIdx) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(rootIdx);
        sb.append(arr[rootIdx]).append('\n');
        int diff = (int) Math.pow(2, depth - 2);

        while (!q.isEmpty()) {
            int elements = q.size();
            while (elements-- > 0) {
                Integer parentIdx = q.poll();
                int leftIdx = parentIdx - diff;
                int rightIdx = parentIdx + diff;
                sb.append(arr[leftIdx]).append(" ").append(arr[rightIdx]).append(" ");
                q.offer(leftIdx);
                q.offer(rightIdx);
            }
            diff /= 2;
            if (diff==0) break;
            sb.append('\n');
        }
    }
}