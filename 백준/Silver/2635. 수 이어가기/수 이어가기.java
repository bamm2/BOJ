import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i <= N; i++) {
            search(N, i, new ArrayList<>());
        }

        System.out.println(result());
        br.close();
    }

    private static String result() {
        StringBuilder sb = new StringBuilder();
        sb.append(list.size()).append('\n');
        for (Integer elements : list) {
            sb.append(elements).append(" ");
        }
        return sb.toString();
    }

    private static void search(int N, int num, ArrayList<Integer> candidates) {
        if (N >= 0) candidates.add(N);
        else {
            if (list.size() < candidates.size()) {
                list.clear();
                list.addAll(candidates);
            }
            return;
        }
        search(num, N - num, candidates);
    }
}
