import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Point {
        int num;
        String direction;

        Point(int num, String direction) {
            this.num = num;
            this.direction = direction;
        }
    }

    static char[] message = {'D', 'S', 'L', 'R'};
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st.nextToken());
            int goal = Integer.parseInt(st.nextToken());

            visited = new boolean[10_000];
            visited[A] = true;
            Queue<Point> q = new ArrayDeque<>();
            q.offer(new Point(A, ""));
            while (!q.isEmpty()) {
                Point curr = q.poll();
                if (curr.num==goal) {
                    sb.append(curr.direction).append('\n');
                    break;
                }
                for (int d = 0; d < 4; d++) {
                    int moveNum = move(curr.num, d);
                    if (visited[moveNum]) continue;
                    visited[moveNum] = true;
                    q.offer(new Point(moveNum, curr.direction + message[d]));
                }
            }
        }
        System.out.println(sb);
    }

    private static int move(int num, int moveSwitch) {
        switch (moveSwitch) {
            case 0:
                return (num * 2) % 10_000;
            case 1:
                return num - 1==-1 ? 9999:num - 1;
            case 2:
                return (num % 1000) * 10 + num / 1000;
            case 3:
                return (num % 10) * 1000 + num / 10;
        }
        return -1;
    }
}