import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    N*M 직사각형
    청소기 동서남북 으로 이동가능

    현재 위치 청소 - > 현재 방향 기준 왼쪽으로 차례대로 탐색
    왼쪽방향 청소 안한공간 있으면 왼쪽 회전 후 한칸 전진
    왼쪽 청소 되어 있으면 왼쪽으로 방향만 전환
    네 방향 청소 되어있거나 벽인 경우 바라보는 방향에서 후진 1칸
    네 방향 청소 되어있거나 벽이면서 뒤쪽이 벽이면 끝

    로봇청소기 좌표 r,c  방향 0 북 1 동 2 남 3 서
    빈칸 0 벽 1
 */


public class Main {

    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 북 동 남 서
    static int N, M, R, C, Robotdir, map[][], ans;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken()); // 로봇청소기 행
        C = Integer.parseInt(st.nextToken()); // 로봇청소기 열
        Robotdir = Integer.parseInt(st.nextToken()); // 방향 0 북 1 동 2 남 3 서


        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        ans = 1;
        dfs(R, C, Robotdir);
        System.out.println(ans);

    }

    private static void dfs( int r, int c ,int direct){
        visited[r][c]=true;

        for(int d=0;d<4;d++){
            direct=(direct+3)%4; // 북동남서 -> 서북동남
            int nr=r+dir[direct][0];
            int nc=c+dir[direct][1];
            if(isOut(nr,nc)) continue;
            if(map[nr][nc]==0 && !visited[nr][nc]){
                ans++;
                dfs(nr,nc,direct);
                return;
            }
        }

        int back=(direct + 2) % 4;
        int backR=r+dir[back][0];
        int backC=c+dir[back][1];

        if(!isOut(backR,backC) && map[backR][backC]!=1){
            dfs(backR,backC,direct);
        }
    }
    private static boolean isOut(int r,int c){
        if(r<0 || c<0 || r>=N || c>=M) return true;
        return false;
    }

}