import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] arr;
    static boolean[] visited;
    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        list = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            visited[i] = true;
            solve(i, i);
            visited[i] = false;
        }
        
        sb.append(list.size()).append('\n');
        Collections.sort(list);
        for (int num : list) {
            sb.append(num).append('\n');
        }

        System.out.println(sb.toString().trim());

    }

    private static void solve(int from, int target) {
        if (!visited[arr[from]]) {
            visited[arr[from]] = true;
            solve(arr[from], target);
            visited[arr[from]] = false;
        }
        if (target == arr[from]) {
            list.add(target);
        }
    }
}