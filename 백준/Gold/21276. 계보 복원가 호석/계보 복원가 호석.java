import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        Map<String, List<String>> map = new HashMap<>();
        Map<String, Integer> counting = new HashMap<>();
        Map<String, List<String>> answer = new TreeMap<>();

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            String name = st.nextToken();
            map.put(name, new ArrayList<>());
            answer.put(name, new ArrayList<>());
            counting.put(name, 0);
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String child = st.nextToken();
            counting.put(child, counting.get(child) + 1);
            String parent = st.nextToken();
            List<String> values = map.get(parent);
            values.add(child);
            map.put(parent, values);
        }

        Queue<String> q = new ArrayDeque<>();
        List<String> roots = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : counting.entrySet()) {
            if (entry.getValue()==0) {
                roots.add(entry.getKey());
                q.offer(entry.getKey());
            }
        }

        while (!q.isEmpty()) {
            String parent = q.poll();

            List<String> children = map.get(parent);
            for (String next : children) {
                Integer parentsCnt = counting.get(next);
                parentsCnt--;
                if (parentsCnt==0) {
                    List<String> child = answer.get(parent);
                    child.add(next);
                    answer.put(parent, child);
                    q.offer(next);
                } else if (parentsCnt < 0) continue;
                counting.put(next, parentsCnt);
            }
        }

        System.out.println(result(roots, answer));
        br.close();
    }

    private static String result(List<String> roots, Map<String, List<String>> answer) {
        StringBuilder sb = new StringBuilder();

        sb.append(roots.size()).append('\n');
        Collections.sort(roots);
        for (String name : roots) {
            sb.append(name).append(" ");
        }
        sb.append('\n');

        for (Map.Entry<String, List<String>> entry : answer.entrySet()) {
            sb.append(entry.getKey()).append(" ");
            List<String> values = entry.getValue();
            sb.append(values.size()).append(" ");
            Collections.sort(values);
            for (String value : values) {
                sb.append(value).append(" ");
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
