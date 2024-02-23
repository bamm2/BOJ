import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static boolean flag;
    static Map<Integer, List<int[]>> hm;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        hm = new HashMap<>();
        solve(A, B, C);

        if (flag) System.out.println(1);
        else System.out.println(0);
        
        br.close();
    }

    private static void solve(int a, int b, int c) {
        if (flag) return;

        if (a==b && b==c) {
            flag = true;
            return;
        }

        int[] curr = Arrays.stream(new int[]{a, b, c}).sorted().toArray();
        List<int[]> value = hm.get(curr[0]);
        if (value==null) {
            ArrayList<int[]> newValue = new ArrayList<>();
            newValue.add(new int[]{curr[1], curr[2]});
            hm.put(curr[0], newValue);
        } else {
            for (int[] next : value) {
                if (next[0]==curr[1] && next[1]==curr[2]) return;
            }
            value.add(new int[]{curr[1], curr[2]});
            hm.put(curr[0], value);
        }

        if (curr[1]!=curr[2]) solve(curr[0], curr[1] + curr[1], curr[2] - curr[1]);
        if (curr[0]!=curr[2]) solve(curr[0] + curr[0], curr[1], curr[2] - curr[0]);
        if (curr[0]!=curr[1]) solve(curr[0] + curr[0], curr[1] - curr[0], curr[2]);

    }
}