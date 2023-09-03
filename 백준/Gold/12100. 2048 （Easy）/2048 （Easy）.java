import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int N, map[][], turnTable[], max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        turnTable = new int[5];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0);
        System.out.println(max);
    }

    private static void solve(int cnt) {

        if (cnt == 5) {
            int[][] copyMap = copyMap();
            for (int i = 0; i < 5; i++) {
                int curTurn = turnTable[i]; // 0 오른쪽 1 왼쪽 2 위 3 아래
                 moving(curTurn,copyMap);
            }
            findMax(copyMap);
            return;
        }

        for (int d = 0; d < 4; d++) {
            turnTable[cnt] = d;
            solve(cnt + 1);
        }
    }

    private static int[][] copyMap() {
        int[][] copy = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copy[i][j]=map[i][j];
            }
        }
        return copy;
    }

    private static void findMax(int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(map[i][j], max);
            }
        }
    }

    private static int[][] moving(int d,int[][] map) {
        int r, c;
        Stack<Integer> stack;
        switch (d) {
            case 0:
                r = 0;
                while (r != N) {
                    c = 0;
                    stack = new Stack<>();

                    while (c < N) {
                        if (map[r][c] != 0) stack.push(map[r][c]);
                        c++;
                    }

                    if(!stack.isEmpty()) {
                        int top = stack.pop();
                        if(stack.isEmpty()) map[r][--c]=top;
                        while (!stack.isEmpty()) {
                            if (stack.peek() == top) {
                                map[r][--c] = stack.pop() * 2;
                            } else {
                                map[r][--c] = top;
                            }
                            if(stack.isEmpty()) break;
                            if (stack.size() == 1) map[r][--c] = stack.pop();
                            else top = stack.pop();
                        }
                    }

                    while (c != 0) map[r][--c] = 0;
                    r++;
                }
                break;
            case 1:
                r = 0;
                while (r != N) {
                    c = N - 1;
                    stack = new Stack<>();
                    while (c >= 0) {
                        if (map[r][c] != 0) stack.push(map[r][c]);
                        c--;
                    }

                    if(!stack.isEmpty()) {
                        int top = stack.pop();
                        if(stack.isEmpty()) map[r][++c]=top;
                        while (!stack.isEmpty()) {
                            if (stack.peek() == top) {
                                map[r][++c] = stack.pop() * 2;
                            } else {
                                map[r][++c] = top;
                            }
                            if(stack.isEmpty()) break;
                            if (stack.size() == 1) map[r][++c] = stack.pop();
                            else top = stack.pop();
                        }
                    }

                    while (c != N - 1) map[r][++c] = 0;
                    r++;
                }
                break;
            case 2:
                c = 0;
                while (c != N) {
                    r = N - 1;
                    stack = new Stack<>();
                    while (r >= 0) {
                        if (map[r][c] != 0) stack.push(map[r][c]);
                        r--;
                    }

                    if(!stack.isEmpty()) {
                        int top = stack.pop();
                        if(stack.isEmpty()) map[++r][c]=top;
                        while (!stack.isEmpty()) {
                            if (stack.peek() == top) {
                                map[++r][c] = stack.pop() * 2;
                            } else {
                                map[++r][c] = top;
                            }
                            if(stack.isEmpty()) break;
                            if (stack.size() == 1) map[++r][c] = stack.pop();
                            else top = stack.pop();
                        }
                    }

                    while (r != N - 1) map[++r][c] = 0;
                    c++;
                }
                break;
            case 3:
                c = 0;
                while (c != N) {
                    r = 0;
                    stack = new Stack<>();
                    while (r < N){
                        if (map[r][c] != 0)  stack.push(map[r][c]);
                        r++;
                    }

                    if(!stack.isEmpty()) {
                        int top = stack.pop();
                        if(stack.isEmpty()) map[--r][c]=top;
                        while (!stack.isEmpty()) {
                            if (stack.peek() == top) {
                                map[--r][c] = stack.pop() * 2;
                            } else {
                                map[--r][c] = top;
                            }
                            if(stack.isEmpty()) break;
                            if (stack.size() == 1) map[--r][c] = stack.pop();
                            else top = stack.pop();
                        }
                    }

                    while (r != 0) map[--r][c] = 0;
                    c++;
                }
                break;
        }
        return map;
    }
}