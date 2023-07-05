import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int N, map[][], L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;

        for (int i = 0; i < N; i++) {
            if (colChk(i)) ans++;
            if (rowChk(i)) ans++;
        }

        System.out.println(ans);

    }

    private static boolean colChk(int num) {
        Stack<Integer> stack = new Stack<>();
        int comp = map[num][0];
        stack.push(comp);
        for (int i = 1; i < N; i++) {
            int nextNum = map[num][i];
            int size = L;
            if (comp < nextNum) {
                if (nextNum - comp != 1) return false;
                while (size-- > 0) {
                    if (stack.isEmpty()) return false;
                    stack.pop();
                }
                comp = nextNum;
                stack.clear();
                stack.push(nextNum);
            } else if (comp > nextNum) {
                if (comp - nextNum != 1) return false;
                if (i + L > N) return false;
                for (int j = i; j < i + L; j++) {
                    if (map[num][j] != comp - 1) return false;
                }
                i += L - 1;
                comp = nextNum;
                stack.clear();
            } else {
                stack.push(nextNum);
            }
        }
        return true;
    }

    private static boolean rowChk(int num) {
        Stack<Integer> stack = new Stack<>();
        int comp = map[0][num];
        stack.push(comp);
        for (int i = 1; i < N; i++) {
            int nextNum = map[i][num];
            int size = L;
            if (comp < nextNum) {
                if (nextNum - comp != 1) return false;
                while (size-- > 0) {
                    if (stack.isEmpty()) return false;
                    stack.pop();
                }
                comp = nextNum;
                stack.clear();
                stack.push(nextNum);
            } else if (comp > nextNum) {
                if (comp - nextNum != 1) return false;
                if (i + L > N) return false;
                for (int j = i; j < i + L; j++) {
                    if (map[j][num] != comp - 1) return false;
                }
                i += L - 1;
                comp = nextNum;
                stack.clear();
            } else {
                stack.push(nextNum);
            }
        }
        return true;
    }

}