import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, col, maxLevel;

    static class Node {
        int number, left, right;

        public Node(int number, int left, int right) {
            this.number = number;
            this.left = left;
            this.right = right;
        }
    }

    static Node[] nodes;
    static List<List<Integer>> levels;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        nodes = new Node[N + 1];
        levels = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            levels.add(new ArrayList<>());
        }

        HashSet<Integer> children = new HashSet<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int v = Integer.parseInt(st.nextToken());
            int leftChild = Integer.parseInt(st.nextToken());
            int rightChild = Integer.parseInt(st.nextToken());
            children.add(leftChild);
            children.add(rightChild);
            nodes[v] = new Node(v, leftChild, rightChild);
        }

        int rootV = 0;
        for (int i = 1; i <= N; i++) {
            if (!children.contains(i)) {
                rootV = i;
                break;
            }
        }

        dfs(rootV, 1);

        int level = 0;
        int maxWidth = 0;
        for (int i = maxLevel; i > 0; i--) {
            List<Integer> currLevels = levels.get(i);
            Collections.sort(currLevels);
            int width = currLevels.get(currLevels.size() - 1) - currLevels.get(0) + 1;
            if (maxWidth <= width) {
                maxWidth = width;
                level = i;
            }
        }

        System.out.println(level + " " + maxWidth);
        br.close();
    }

    private static void dfs(int v, int level) {
        if (nodes[v].left!=-1) {
            dfs(nodes[v].left, level + 1);
        }

        levels.get(level).add(++col);
        maxLevel = Math.max(level, maxLevel);

        if (nodes[v].right!=-1) {
            dfs(nodes[v].right, level + 1);
        }
    }

}