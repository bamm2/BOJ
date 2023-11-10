import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Enemy implements Comparable<Enemy> {
        int r, c, distance;

        public Enemy(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Enemy(int r, int c, int distance) {
            this.r = r;
            this.c = c;
            this.distance = distance;
        }

        public void setR(int r) {
            this.r = r;
        }

        @Override
        public int compareTo(Enemy o) {
            if (this.distance==o.distance) {
                return Integer.compare(this.c, o.c);
            }
            return Integer.compare(this.distance, o.distance);
        }
    }

    static List<Enemy> enemies = new ArrayList<>();
    static int[] killer = new int[3];
    static int R, C, limitDistance, map[][], max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        limitDistance = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j]==1) enemies.add(new Enemy(i,j));
            }
        }

        comb(0, 0);
        System.out.println(max);
    }

    private static void comb(int idx, int start) {
        if (idx==3) {
            curPosPlay(copy());
            return;
        }


        for (int i = start; i < C; i++) {
            killer[idx] = i;
            comb(idx + 1, i + 1);
        }
    }

    private static void curPosPlay(List<Enemy> enemies) {

        int count = 0;

        while (enemies.size()!=0) {
            List<Enemy> deadList = new ArrayList<>();

            for (int i = 0; i < 3; i++) {
                int killerR = R;
                int killerC = killer[i];
                PriorityQueue<Enemy> pq = new PriorityQueue<>();
                for (int j = 0; j < enemies.size(); j++) {
                    Enemy enemy = enemies.get(j);
                    int distance = getDistance(killerR, killerC, enemy.r, enemy.c);
                    if (distance <= limitDistance) pq.offer(new Enemy(enemy.r, enemy.c, distance));
                }

                if (!pq.isEmpty()) {
                    Enemy enemy = pq.poll();
                    boolean flag = false;
                    for (int j = 0; j < deadList.size(); j++) {
                        Enemy target = deadList.get(j);
                        if (enemy.r==target.r && enemy.c==target.c) {
                            flag = true;
                        }
                    }
                    if (!flag) {
                        deadList.add(enemy);
                    }
                }
            }

            for (int i = 0; i < deadList.size(); i++) {
                Enemy dead = deadList.get(i);
                for (int j = enemies.size() - 1; j >= 0; j--) {
                    Enemy enemy = enemies.get(j);
                    if(dead.r == enemy.r && dead.c == enemy.c){
                        enemies.remove(j);
                        count++;
                        break;
                    }
                }
            }

            for (int i = enemies.size()-1; i >= 0 ; i--) {
                Enemy enemy = enemies.get(i);
                if(enemy.r+1 ==R) enemies.remove(i);
                else enemy.setR(enemy.r+1);
            }
        }

        max = Math.max(count, max);

    }

    private static List<Enemy> copy() {
        List<Enemy> copy = new ArrayList<>();
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            copy.add(new Enemy(enemy.r,enemy.c));
        }
        return copy;
    }

    private static int getDistance(int r, int c, int enemyR, int enemyC) {
        return (r - enemyR) + Math.abs(c - enemyC);
    }
}
