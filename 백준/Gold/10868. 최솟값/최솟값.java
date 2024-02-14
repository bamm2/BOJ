import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] arr, tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        initTree(N);
        makeMinTree(1, 1, N);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            sb.append(findMin(1, N, from, to, 1)).append('\n');
        }

        System.out.println(sb);
        br.close();
    }

    private static void initTree(int len) {
        int level = (int) Math.ceil(Math.log(len) / Math.log(2));
        int size = (int) Math.pow(2, level + 1);
        tree = new int[size];
    }

    private static int makeMinTree(int node, int start, int end) {
        if (start==end) {
            return tree[node] = arr[start];
        }

        int mid = (start + end) / 2;
        return tree[node] = Math.min(makeMinTree(node * 2, start, mid), makeMinTree(node * 2 + 1, mid + 1, end));
    }

    private static int findMin(int start, int end, int left, int right, int node) {
        if (end < left || start > right) return Integer.MAX_VALUE;

        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        return Math.min(findMin(start, mid, left, right, node * 2), findMin(mid + 1, end, left, right, node * 2 + 1));
    }

}