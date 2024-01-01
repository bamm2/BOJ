import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, map[][], divMap[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int size = N;
        while (true) {
            divMap = new int[size / 2][size / 2];
            solve(0, 0, size);
            map = divMap;
            size /= 2;
            if (size==1) break;
        }

        System.out.println(map[0][0]);
    
    }

    private static void solve(int r, int c, int size) {
        if (size==2) {
            divMap[r / 2][c / 2] = find(r, c);
            return;
        }

        int nextSize = size / 2;
        solve(r, c, nextSize);
        solve(r + nextSize, c, nextSize);
        solve(r, c + nextSize, nextSize);
        solve(r + nextSize, c + nextSize, nextSize);
    }

    private static int find(int r, int c) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(n2, n1));
        int[][] plus = {{1, 0}, {0, 1}, {1, 1}, {0, 0}};
        for (int d = 0; d < 4; d++) {
            pq.offer(map[r + plus[d][0]][c + plus[d][1]]);
        }
        pq.poll();
        return pq.poll();
    }

}