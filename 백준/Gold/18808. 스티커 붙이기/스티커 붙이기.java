import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int R, C,cnt;
    static int[][] map, sticker;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken()); // 스티커 개수
        map = new int[R][C];

        for (int t = 0; t < K; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            int stickerR = Integer.parseInt(st.nextToken());
            int stickerC = Integer.parseInt(st.nextToken());
            sticker = new int[stickerR][stickerC];
            for (int i = 0; i < stickerR; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < stickerC; j++) {
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int turnCnt = 0;

            while (!search()) {
                if (++turnCnt==4) break;
                turn();
            }
        }

        System.out.println(cnt);
        br.close();
    }

    private static boolean search() {

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {

                if (isOut(r + sticker.length, c + sticker[0].length)) continue;
                boolean flag = false;
                loop:
                for (int i = 0; i < sticker.length; i++) {
                    for (int j = 0; j < sticker[0].length; j++) {

                        if (map[r + i][c + j]==1 && sticker[i][j]==1) {
                            flag = true;
                            break loop;
                        }
                    }
                }

                if (!flag) {
                    attach(r, c);
                    return true;
                }
            }
        }

        return false;
    }

    private static void attach(int r, int c) {
        for (int i = 0; i < sticker.length; i++) {
            for (int j = 0; j < sticker[0].length; j++) {
                if (sticker[i][j]==0) continue;
                map[r + i][c + j] = sticker[i][j];
                cnt++;
            }
        }
    }

    private static void turn() {
        int R = sticker.length;
        int C = sticker[0].length;
        int[][] tmp = new int[C][R];
        for (int i = 0; i < C; i++) {
            for (int j = R - 1; j >= 0; j--) {
                tmp[i][R - 1 - j] = sticker[j][i];
            }
        }

        sticker = tmp.clone();
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r > R || c > C;
    }
}