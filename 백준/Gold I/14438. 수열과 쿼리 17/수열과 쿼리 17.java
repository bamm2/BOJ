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

        int N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        initTree(N);

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int command = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            switch (command) {
                case 1:
                    update(1, N, 1, from, to);
                    break;
                case 2:
                    sb.append(findMin(1, N, from, to, 1)).append('\n');
                    break;
            }
        }

        System.out.println(sb);
        br.close();
    }

    private static void initTree(int len) {
        int level = (int) Math.ceil(Math.log(len) / Math.log(2));
        int size = (int) Math.pow(2, level + 1);
        tree = new int[size];

        init(1, 1, len);
    }

    private static int init(int node, int start, int end) {
        if (start==end) {
            return tree[node] = arr[start];
        }

        int mid = (start + end) / 2;
        return tree[node] = Math.min(init(node * 2, start, mid), init(node * 2 + 1, mid + 1, end));
    }

    private static int update(int start, int end, int node, int idx, int change) {
        if (idx < start || idx > end) return tree[node];

        if (start==idx && idx==end) {
            return tree[node] = change;
        }

        int mid = (start + end) / 2;
        return tree[node] = Math.min(update(start, mid, node * 2, idx, change), update(mid + 1, end, node * 2 + 1, idx, change));

    }

    private static int findMin(int start, int end, int left, int right, int node) {
        if (left > end || start > right) return Integer.MAX_VALUE;

        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        return Math.min(findMin(start, mid, left, right, node * 2), findMin(mid + 1, end, left, right, node * 2 + 1));
    }

}