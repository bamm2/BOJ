import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int left, right;

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    static int N, cnt;
    static Node[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        nodes = new Node[N + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int v = Integer.parseInt(st.nextToken());
            int leftChild = Integer.parseInt(st.nextToken());
            int rightChild = Integer.parseInt(st.nextToken());
            nodes[v] = new Node(leftChild, rightChild);
        }

        search(1);

        rightSearch(1);

        System.out.println(cnt);
        br.close();
    }

    private static void search(int v) {
        if (nodes[v].left!=-1) {
            cnt += 2;
            search(nodes[v].left);
        }
        if (nodes[v].right!=-1) {
            cnt += 2;
            search(nodes[v].right);
        }
    }

    private static void rightSearch(int v) {
        if(nodes[v].right!=-1){
            cnt--;
            rightSearch(nodes[v].right);
        }
    }
}