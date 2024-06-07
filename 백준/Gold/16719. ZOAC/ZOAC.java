import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static String str;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str = br.readLine();
        visited = new boolean[str.length()];

        recur(0, str.length());

        System.out.println(sb);
        br.close();
    }

    private static void recur(int from, int to) {
        if (from >= to) return;
        int idx = visit(from, to);
        makeString();

        recur(idx + 1, to);
        recur(from, idx);
    }

    private static void makeString() {
        for (int i = 0, len = str.length(); i < len; ++i) {
            if (visited[i]) sb.append(str.charAt(i));
        }
        sb.append('\n');
    }

    private static int visit(int from, int to) {
        int minChar = 100;
        int idx = -1;
        for (int i = from; i < to; i++) {
            int curr = str.charAt(i);
            if (minChar > curr) {
                minChar = curr;
                idx = i;
            }
        }

        visited[idx] = true;
        return idx;
    }
}