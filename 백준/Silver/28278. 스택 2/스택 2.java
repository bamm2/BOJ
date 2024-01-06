import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] stack = new int[1_000_000];
    static int pointer = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int method = Integer.parseInt(st.nextToken());
            switch (method) {
                case 1:
                    int number =Integer.parseInt(st.nextToken());
                    add(number);
                    break;
                case 2:
                    sb.append(pop()).append('\n');
                    break;
                case 3:
                    sb.append(size()).append('\n');
                    break;
                case 4:
                    sb.append(isEmpty()).append('\n');
                    break;
                case 5:
                    sb.append(peek()).append('\n');
                    break;
            }
        }

        System.out.println(sb);
    }


    private static void add(int x) {
        stack[++pointer] = x;
    }

    private static int pop() {
        if (pointer==-1) return -1;
        return stack[pointer--];
    }

    private static int size() {
        return pointer + 1;
    }

    private static int isEmpty() {
        if (pointer==-1) return 1;
        return 0;
    }

    private static int peek() {
        if (pointer==-1) return -1;
        return stack[pointer];
    }
}