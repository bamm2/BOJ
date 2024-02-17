import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static class Point {
        long number;
        String command;

        public Point(long number, String command) {
            this.number = number;
            this.command = command;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        System.out.println(bfs(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));

        br.close();
    }

    private static String bfs(int from,int to) {
        if(from==to) return "0";

        long[] np;
        Set<Long> hs =new HashSet<>();
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(from, ""));
        hs.add((long)from);

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                Point curr = q.poll();
                if(curr.number == to) {
                    return curr.command;
                }
                if(curr.number==0) continue;
                np = new long[]{curr.number * curr.number, curr.number + curr.number, 0, 1};
                for (int d = 0; d < 4; d++) {
                    if(!hs.add(np[d])) continue;
                    if( (hs.contains(0) || hs.contains(1)) && np[d]>to) continue;
                    q.offer(new Point(np[d],curr.command+findCommand(d)));
                }
            }
        }

        return "-1";
    }

    private static String findCommand(int d) {
        switch (d){
            case 0: return "*";
            case 1: return "+";
            case 2: return "-";
            default: return "/";
        }
    }

}