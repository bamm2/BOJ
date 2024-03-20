import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map, tmp;
    static int goalSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = 1 << Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int command = Integer.parseInt(st.nextToken());
            goalSize = 1 << Integer.parseInt(st.nextToken());
            go(command);
        }

        System.out.println(result());
        br.close();
    }

    private static String result() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    private static void go(int command) {
        tmp = new int[N][N];

        switch (command) {
            case 1:
                commandInside(0, 0, N, 1);
                break;
            case 2:
                commandInside(0, 0, N, 2);
                break;
            case 3:
                commandInside(0, 0, N, 3);
                break;
            case 4:
                commandInside(0, 0, N, 4);
                break;
            case 5:
                commandHoriOrVerti(0, true);
                break;
            case 6:
                commandHoriOrVerti(0, false);
                break;
            case 7:
                commandSetTurnAround(0, 0, N, false, true);
                break;
            case 8:
                commandSetTurnAround(0, 0, N, false, false);
                break;
        }

        copy();
    }

    private static void commandInside(int r, int c, int size, int command) {
        if (size==goalSize) {
            switch (command) {
                case 1:
                    commandOne(r, c, size);
                    break;
                case 2:
                    commandTwo(r, c, size);
                    break;
                case 3:
                    commandThree(r, c, size);
                    break;
                case 4:
                    commandFour(r, c, size);
                    break;
            }
            return;
        }

        size /= 2;

        commandInside(r, c, size, command);
        commandInside(r + size, c, size, command);
        commandInside(r, c + size, size, command);
        commandInside(r + size, c + size, size, command);
    }

    private static void commandOne(int r, int c, int size) {
        for (int i = c; i < c + size; i++) {
            for (int j = r; j < r + size; j++) {
                tmp[j][i] = map[r + r + size - j - 1][i];
            }
        }
    }

    private static void commandTwo(int r, int c, int size) {
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                tmp[i][j] = map[i][c + c + size - j - 1];
            }
        }
    }

    private static void commandThree(int r, int c, int size) {
        int tmpR = r;
        int tmpC = c;
        for (int i = c; i < c + size; i++) {
            for (int j = r; j < r + size; j++) {
                tmp[tmpR][tmpC++] = map[r + r + size - j - 1][i];
            }
            tmpR++;
            tmpC = c;
        }
    }

    private static void commandFour(int r, int c, int size) {
        int tmpR = r;
        int tmpC = c;
        for (int i = c + size - 1; i >= c; i--) {
            for (int j = r; j < r + size; j++) {
                tmp[tmpR][tmpC++] = map[j][i];
            }
            tmpR++;
            tmpC = c;
        }
    }

    private static void commandHoriOrVerti(int v, boolean isVertical) {
        if (v==N) return;

        int halfPos = N / 2;

        int loop = 1;

        while (true) {
            int rangeStandardOne = loop * goalSize;
            int rangeStandardTwo = (loop - 1) * goalSize;

            int leftStartRange = halfPos - rangeStandardOne;
            int leftEndRange = halfPos - rangeStandardTwo;
            int rightStartRange = halfPos + rangeStandardTwo;
            int rightEndRange = halfPos + rangeStandardOne;

            if (leftStartRange < 0 || rightEndRange > N) break;

            for (int i = leftStartRange, j = 0; i < leftEndRange; i++, j++) {
                if (isVertical) tmp[rightStartRange + j][v] = map[i][v];
                else tmp[v][rightStartRange + j] = map[v][i];
            }
            for (int i = rightStartRange, j = 0; i < rightEndRange; i++, j++) {
                if (isVertical) tmp[leftStartRange + j][v] = map[i][v];
                else tmp[v][leftStartRange + j] = map[v][i];
            }

            loop++;
        }

        commandHoriOrVerti(v + 1, isVertical);
    }


    private static void commandSetTurnAround(int r, int c, int size, boolean isMove, boolean isSeven) {

        if (size < goalSize) return;

        if (isMove) {
            for (int i = r; i < r + size; i++) {
                for (int j = c; j < c + size; j++) {
                    if (isSeven) tmp[i][j] = map[i + size][j];
                    else tmp[i][j] = map[i][j + size];
                }
            }
            for (int i = r; i < r + size; i++) {
                for (int j = c + size; j < c + 2 * size; j++) {
                    if (isSeven) tmp[i][j] = map[i][j - size];
                    else tmp[i][j] = map[i + size][j];
                }
            }
            for (int i = r + size; i < r + 2 * size; i++) {
                for (int j = c; j < c + size; j++) {
                    if (isSeven) tmp[i][j] = map[i][j + size];
                    else tmp[i][j] = map[i - size][j];
                }
            }
            for (int i = r + size; i < r + 2 * size; i++) {
                for (int j = c + size; j < c + 2 * size; j++) {
                    if (isSeven) tmp[i][j] = map[i - size][j];
                    else tmp[i][j] = map[i][j - size];
                }
            }
            for (int i = r; i < r + 2 * size; i++) {
                for (int j = c; j < c + 2 * size; j++) {
                    map[i][j] = tmp[i][j];
                }
            }
        }

        size /= 2;
        commandSetTurnAround(r, c, size, true, isSeven);
        commandSetTurnAround(r, c + size, size, false, isSeven);
        commandSetTurnAround(r + size, c, size, false, isSeven);
        commandSetTurnAround(r + size, c + size, size, false, isSeven);
    }

    private static void copy() {
        for (int i = 0; i < N; i++) {
            System.arraycopy(tmp[i], 0, map[i], 0, N);
        }
    }

}