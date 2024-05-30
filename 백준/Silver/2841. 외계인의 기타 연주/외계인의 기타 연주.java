import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        st.nextToken();

        int cnt = 0;
        Map<Integer, Stack<Integer>> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int line = Integer.parseInt(st.nextToken());
            int pl = Integer.parseInt(st.nextToken());
            Stack<Integer> value = map.get(line);
            if (value==null) {
                Stack<Integer> newValue = new Stack<>();
                newValue.add(pl);
                map.put(line, newValue);
                cnt++;
            } else {
                while (!value.isEmpty()) {
                    Integer top = value.peek();
                    if (top > pl) {
                        cnt++;
                        value.pop();
                    } else if (top==pl) {
                        break;
                    } else {
                        value.push(pl);
                        cnt++;
                        break;
                    }
                }
                if (value.isEmpty()) {
                    cnt++;
                    value.push(pl);
                }
            }
        }

        System.out.println(cnt);
        br.close();
    }
}