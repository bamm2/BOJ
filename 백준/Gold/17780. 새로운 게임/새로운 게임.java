import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, K, map[][];
    static int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    static class Point {
        int r, c, dir;
        boolean isMovable;

        public Point(int r, int c, int dir, boolean isMovable) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.isMovable = isMovable;
        }
    }

    static List<Point> points = new ArrayList<>();
    static List<Integer>[][] horseMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        horseMap = new LinkedList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                horseMap[i][j] = new LinkedList<>();
            }
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1; // 우, 좌, 상, 하
            points.add(new Point(r, c, d, true));
            horseMap[r][c].add(i);
        }

        int cnt = 0;
        while (++cnt <= 1000) {
            if (isSatisfied()) break;
        }

        if (cnt==1001) cnt = -1;

        System.out.println(cnt);
        br.close();

    }

    private static boolean isSatisfied() {
        for (int i = 0; i < K; i++) {
            Point point = points.get(i);
            if (!point.isMovable) continue; // 가장 아래칸이 아니라면

            boolean isRed = false;
            int prevR = point.r;
            int prevC = point.c;
            List<Integer> prevPosList = horseMap[prevR][prevC]; // 현재 자신과 위에 있는 말들

            int nr = point.r + dir[point.dir][0];
            int nc = point.c + dir[point.dir][1];

            if (isOut(nr, nc) || map[nr][nc]==2) { // 나가거나 파란색이면
                int backDir = point.dir % 2==0 ? point.dir + 1:point.dir - 1;
                int br = point.r + dir[backDir][0];
                int bc = point.c + dir[backDir][1];
                if (isOut(br, bc) || map[br][bc]==2) { // 뒤돌았는데도 움직일 수 없는 상황이라면
                    point.dir = backDir; // 방향만 바꿔주기
                } else {
                    if (map[br][bc]==1) isRed = true;
                    point.dir = backDir;
                    point.r = br;
                    point.c = bc;
                }
            } else if (map[nr][nc]==1) { // 빨간색이라면
                isRed = true;
                point.r = nr;
                point.c = nc;
            } else { // 흰색이라면
                point.r = nr;
                point.c = nc;
            }

            if (horseMap[point.r][point.c].size()==0) { // 이동한 곳에 기존에 말들이 존재하지 않을 경우
                if (isRed) { // 순서 뒤집기
                    Integer prevBottom = prevPosList.get(0); // 뒤집혀 top
                    Integer newBottom = prevPosList.get(prevPosList.size() - 1); // 뒤집혀 bottom
                    points.get(prevBottom).isMovable = false; // 이동 가능 여부 변경
                    points.get(newBottom).isMovable = true; // 이동 가능 여부 변경
                    for (int k = prevPosList.size() - 1; k >= 0; k--) {
                        Integer number = prevPosList.get(k);
                        points.get(number).r = point.r; // 이동할 위치로 모두 이동 시켜주기
                        points.get(number).c = point.c; // 이동할 위치로 모두 이동 시켜주기
                        horseMap[point.r][point.c].add(number);
                    }
                } else {
                    for (int k = 0; k < prevPosList.size(); k++) {
                        Integer number = prevPosList.get(k);
                        points.get(number).r = point.r;
                        points.get(number).c = point.c;
                        horseMap[point.r][point.c].add(number);
                    }
                }
            } else { // 이동한 곳에 기존에 말들이 존재할 경우
                if (prevR==point.r && prevC==point.c) continue; // 이전의 위치와 동일할 경우 (움직이지 못한 경우)
                if (horseMap[point.r][point.c].size() + prevPosList.size() >= 4) return true; // 4개 이상이 모였을 경우 바로 종료
                Integer preBottom = prevPosList.get(0);
                points.get(preBottom).isMovable = false; // 이동한 곳에 이미 존재하니까

                if (isRed) { // 뒤집기
                    for (int k = prevPosList.size() - 1; k >= 0; k--) {
                        Integer number = prevPosList.get(k);
                        points.get(number).r = point.r;
                        points.get(number).c = point.c;
                        horseMap[point.r][point.c].add(number);
                    }
                } else {
                    for (int k = 0; k < prevPosList.size(); k++) {
                        Integer number = prevPosList.get(k);
                        points.get(number).r = point.r;
                        points.get(number).c = point.c;
                        horseMap[point.r][point.c].add(number);
                    }
                }
            }

            horseMap[prevR][prevC].clear(); // 이전 위치 정리
        }

        return false;
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= N;
    }
}