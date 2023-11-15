import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static char[][] map;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i <= R - 8; i++) {
            for (int j = 0; j <= C - 8; j++) {
                min = Math.min(search(i, j, 'B'), min);
                min = Math.min(search(i, j, 'W'), min);
            }
        }

        System.out.println(min);

    }
    
    private static int search(int r, int c, char bOrW) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2==0) {
                    if (j % 2==0) {
                        if (map[i + r][j + c]!=bOrW) count++;
                    } else {
                        if (map[i + r][j + c]==bOrW) count++;
                    }
                } else {
                    if (j % 2==0) {
                        if (map[i + r][j + c]==bOrW) count++;
                    } else {
                        if (map[i + r][j + c]!=bOrW) count++;
                    }
                }
            }
        }
        return count;
    }


}