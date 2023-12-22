import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if (N==K) { // 0초에 같은 위치일 경우
            System.out.println(0);
            return;
        }

        boolean[][] visited = new boolean[2][500_001];

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(N);
        visited[0][N] = true; // 짝수

        int time = 0;
        while (!q.isEmpty()) {
            time++; // 1초 지났다.
            K += time;
            if (K > 500_000) break;

            int flag = time % 2; // 홀,짝 ( -1,1 로 홀또는 짝 번째에 방문했으면 항상 홀 또는 짝 회차에 방문 가능 )

            int size = q.size();
            while (size-- > 0) {
                Integer curr = q.poll();
                int[] dir = {curr + 1, curr - 1, curr * 2};
                for (int d = 0; d < 3; d++) {
                    int np = dir[d];
                    if (isOut(np) || visited[flag][np]) continue; // 아직 (홀,짝) 방문하지 않았더라면
                    visited[flag][np] = true;
                    q.offer(np);
                }
            }

            if (visited[flag][K]) { // 만일 동생이 수빈이가 방문한 위치에 도달했다면
                System.out.println(time);
                return;
            }

        }

        System.out.println(-1);
    }

    private static boolean isOut(int pos) {
        return pos < 0 || pos > 500_000;
    }
}