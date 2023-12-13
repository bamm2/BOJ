import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int R, C, sum;

    static class Shark {
        int idx, r, c, size, dir, movePoint;
        boolean isMoved, isKilled;

        public Shark(int idx, int r, int c, int size, int dir, int movePoint, boolean isMoved, boolean isKilled) {
            this.idx = idx;
            this.r = r;
            this.c = c;
            this.size = size;
            this.dir = dir;
            this.movePoint = movePoint;
            this.isMoved = isMoved;
            this.isKilled = isKilled;
        }
    }

    static Shark[][] sharkMap;
    static List<Shark> sharks = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        sharkMap = new Shark[R][C];
        Shark shark;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());
            shark = new Shark(i, r, c, z, d, s, false, false);
            sharks.add(shark);
            sharkMap[r][c] = shark;
        }


        for (int i = 0; i < C; i++) { // 낚시왕의 현재 위치
            searchCloseShark(i);
            moveSharks();
            setGame();
        }

        System.out.println(sum);

    }

    private static void searchCloseShark(int curC) {
        for (int r = 0; r < R; r++) {
            if (sharkMap[r][curC]!=null) {
                sum += sharkMap[r][curC].size; // 합계에 더해주기
                sharks.get(sharkMap[r][curC].idx).isKilled = true; // 상어 리스트에서 죽임 표시
                sharkMap[r][curC] = null; // 지도에서 지우기 , 죽음
                break;
            }
        }
    }

    private static void moveSharks() {
        for (int i = 0; i < sharks.size(); i++) {
            Shark curr = sharks.get(i);
            if (curr.isKilled) continue; // 이미 낚시왕이 잡았다면
             if(sharkMap[curr.r][curr.c].idx == curr.idx) sharkMap[curr.r][curr.c]=null; // 시작 위치가 자기라면 null 처리 
           
            MoveDto movePos = moving(curr); // 움직인 상어 위치
            // 움직인 위치,방향 설정
            curr.r = movePos.r;
            curr.c = movePos.c;
            curr.dir = movePos.dir;
            if (sharkMap[movePos.r][movePos.c]==null || !sharkMap[movePos.r][movePos.c].isMoved) { // 만일 움직이지 않은 상어 이거나 상어가 없다면
                sharkMap[curr.r][curr.c] = curr;
            } else { // 이동한 위치에 이동 완료된 상어가 존재한다면
                if (sharkMap[movePos.r][movePos.c].size < curr.size) { // 맵에 있는 상어가 더 작으면
                    int killedIdx = sharkMap[movePos.r][movePos.c].idx;
                    sharks.get(killedIdx).isKilled = true; // 작은 상어 죽이기
                    sharkMap[movePos.r][movePos.c] = curr; // 기존 상어 덮어씌우기
                } else {  // 맵에 있는 상어가 더 크면
                    curr.isKilled = true;
                }
            }
            curr.isMoved = true; // 움직였음을 표시
        }
    }

    private static MoveDto moving(Shark curr) {

        int turnCount = 1; // 최초로 끝까지 갔을 경우 전환했음을 미리 표시
        int remainMovePoint, nr, nc;
        switch (curr.dir) { // 시작 방향
            case 0: // 상
                if (curr.r >= curr.movePoint) { // 방향전환 필요 없을 경우
                    return new MoveDto(curr.r - curr.movePoint, curr.c, curr.dir);
                }
                // 방향 전환 필요
                remainMovePoint = curr.movePoint - curr.r; // r이 0으로 이동
                turnCount += remainMovePoint / (R - 1); // 방향 전환 갯수
                remainMovePoint = remainMovePoint % (R - 1); // 이동 거리 , 0 또는 R-1부터 출발 ( 방향에 따른 출발위치 )

                if (turnCount % 2==0) { // 방향전환 갯수가 짝수이면 원래의 방향 ( R-1에서 출발 )
                    nr = (R - 1) - remainMovePoint;
                } else { // 홀수이면 반대방향 ( 0에서 출발 )
                    nr = remainMovePoint;
                    curr.dir = 1;
                }
                return new MoveDto(nr, curr.c, curr.dir);
            case 1: // 하
                if (curr.r + curr.movePoint < R) { // 방향전환 필요 없을 경우
                    return new MoveDto(curr.r + curr.movePoint, curr.c, curr.dir);
                }
                // 방향 전환 필요
                remainMovePoint = curr.movePoint - ((R - 1) - curr.r); // r이 r-1으로 이동
                turnCount += remainMovePoint / (R - 1); // 방향 전환 갯수
                remainMovePoint = remainMovePoint % (R - 1); // 이동 거리 , 0 또는 R-1부터 출발 ( 방향에 따른 출발위치 )

                if (turnCount % 2==0) { // 방향전환 갯수가 짝수이면 원래의 방향 ( 0에서 출발 )
                    nr = remainMovePoint;
                } else { // 홀수이면 반대방향 ( R-1에서 출발 )
                    nr = (R - 1) - remainMovePoint;
                    curr.dir = 0;
                }

                return new MoveDto(nr, curr.c, curr.dir);
            case 2: // 우
                if (curr.c + curr.movePoint < C) { // 방향전환 필요 없을 경우
                    return new MoveDto(curr.r, curr.c + curr.movePoint, curr.dir);
                }
                // 방향 전환 필요
                remainMovePoint = curr.movePoint - ((C - 1) - curr.c); // c가 c-1으로 이동
                turnCount += remainMovePoint / (C - 1); // 방향 전환 갯수
                remainMovePoint = remainMovePoint % (C - 1); // 이동 거리 , 0 또는 C-1부터 출발 ( 방향에 따른 출발위치 )

                if (turnCount % 2==0) { // 방향전환 갯수가 짝수이면 원래의 방향 ( 0에서 출발 )
                    nc = remainMovePoint;
                } else { // 홀수이면 반대방향 ( C-1에서 출발 )
                    nc = (C - 1) - remainMovePoint;
                    curr.dir = 3;
                }

                return new MoveDto(curr.r, nc, curr.dir);
            case 3: // 좌
                if (curr.c >= curr.movePoint) { // 방향전환 필요 없을 경우
                    return new MoveDto(curr.r, curr.c - curr.movePoint, curr.dir);
                }
                // 방향 전환 필요
                remainMovePoint = curr.movePoint - curr.c; // c가 0으로 이동
                turnCount += remainMovePoint / (C - 1); // 방향 전환 갯수
                remainMovePoint = remainMovePoint % (C - 1); // 이동 거리 , 0 또는 C-1부터 출발 ( 방향에 따른 출발위치 )

                if (turnCount % 2==0) { // 방향전환 갯수가 짝수이면 원래의 방향 ( C-1에서 출발 )
                    nc = (C - 1) - remainMovePoint;
                } else { // 홀수이면 반대방향 ( 0에서 출발 )
                    nc = remainMovePoint;
                    curr.dir = 2;
                }

                return new MoveDto(curr.r, nc, curr.dir);
        }
        return null;
    }

    private static void setGame() {
        for (int i = sharks.size() - 1; i >= 0; i--) { // 죽은 상어 정리하기
            Shark curr = sharks.get(i);
            if (curr.isKilled) sharks.remove(i);
        }

        sharkMap = new Shark[R][C];
        for (int i = 0; i < sharks.size(); i++) { // idx 재설정, 움직임 여부 재설정
            Shark curr = sharks.get(i);
            curr.isMoved = false;
            curr.idx = i;
            sharkMap[curr.r][curr.c] = curr;
        }
    }

    private static class MoveDto {

        int r, c, dir;

        public MoveDto(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }
}

// sharkMap 하나로 풀어보려다 코드 더러워지고 억지로 답을 맞춤.