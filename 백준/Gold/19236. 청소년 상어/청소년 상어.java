import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int ans;
    static int[][] dir = {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}}; // 12시방향부터 반시계 방향으로 회전

    static class Shark {
        int r, c, eat, dir;

        Shark(int r, int c, int eat, int dir) {
            this.r = r;
            this.c = c;
            this.eat = eat;
            this.dir = dir;
        }
    }

    static class Fish {
        int r, c, dir;
        boolean isAlive;

        Fish(int r, int c, int dir, boolean isAlive) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.isAlive = isAlive;
        }
    }

    static int[][] fishMap;
    static Fish[] fishes;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        fishMap = new int[4][4];
        fishes = new Fish[17];

        for (int i = 0; i < 4; i++) {
            String[] s = br.readLine().split(" ");
            for (int j = 0; j < 8; j += 2) {
                int c = j / 2;
                int fishNumber = Integer.parseInt(s[j]);
                int fishDir = Integer.parseInt(s[j + 1]) - 1;
                fishMap[i][c] = fishNumber;
                fishes[fishNumber] = new Fish(i, c, fishDir, true);
            }
        }

        gameStart();

        System.out.println(ans);

    }

    private static void gameStart() {

        int direct = fishes[fishMap[0][0]].dir;
        Shark shark = new Shark(0, 0, fishMap[0][0], direct);
        fishes[fishMap[0][0]].isAlive = false;

        goShark(shark, fishMap, fishes);

    }

    private static void goShark(Shark shark, int[][] fishMap, Fish[] fishes) {

        if (ans < shark.eat) {
            ans = shark.eat;
        }

        moveFish(fishes, fishMap, shark);

        int sharkDir = shark.dir;

        for (int plus = 1; plus <= 3; plus++) {
            int nr = shark.r + dir[sharkDir][0]*plus;
            int nc = shark.c + dir[sharkDir][1]*plus;
            if (isOut(nr, nc) || !fishes[fishMap[nr][nc]].isAlive ) continue;

            int[][] copyFishMap = copyMap(fishMap);
            Fish[] copyFish = copyFishes(fishes);

            Shark moveShark = new Shark(nr, nc, shark.eat + copyFishMap[nr][nc], copyFish[copyFishMap[nr][nc]].dir);
            copyFish[copyFishMap[nr][nc]].isAlive = false;

            goShark(moveShark, copyFishMap, copyFish);
        }
    }

    private static void moveFish(Fish[] fishes, int[][] fishMap, Shark shark) {

        for (int i = 1; i < fishes.length; i++) {
            Fish fish = fishes[i];
            if (!fish.isAlive) continue;
            int direct = fish.dir;
            int nr , nc ;
            int cnt = 0;
            while (true) {
                nr = fish.r + dir[direct][0];
                nc = fish.c + dir[direct][1];
                if (isOut(nr, nc) || (nr == shark.r && nc == shark.c)) {
                    cnt++;
                    direct = (direct + 1) % 8;
                    if (cnt == 7) break;
                } else {
                    break;
                }
            }

            if (cnt != 7) {
                int swapFishNumber = fishMap[nr][nc];
                Fish swapFish = fishes[swapFishNumber];
                int swapFishDir = swapFish.dir;
                boolean swapFishIsAlive = swapFish.isAlive;

                fishes[i] = new Fish(nr, nc, direct, true);
                fishes[swapFishNumber] = new Fish(fish.r, fish.c, swapFishDir, swapFishIsAlive);

                swap(fish.r, fish.c, swapFish.r, swapFish.c, fishMap);
            }else{
                fishes[i]= new Fish(fish.r,fish.c,direct,true);
            }
        }
    }

    private static void swap(int r, int c, int r1, int c1, int[][] fishMap) {
        int tmp = fishMap[r][c];
        fishMap[r][c] = fishMap[r1][c1];
        fishMap[r1][c1] = tmp;
    }

    private static Fish[] copyFishes(Fish[] fishes) {
        Fish[] tmp = new Fish[17];
        for (int i = 1; i < fishes.length; i++) {
            Fish fish = fishes[i];
            tmp[i] = new Fish(fish.r,fish.c, fish.dir, fish.isAlive);
        }
        return tmp;
    }

    private static int[][] copyMap(int[][] fishMap) {
        int[][] tmp = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tmp[i][j] = fishMap[i][j];
            }
        }
        return tmp;
    }


    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= 4 || c >= 4;
    }
}