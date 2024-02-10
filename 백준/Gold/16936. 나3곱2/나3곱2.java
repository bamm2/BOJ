import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static boolean sign;
    static long[] arr, findArr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        findArr = new long[N];

        List<Long> list = new ArrayList<>();
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            list.add(Long.parseLong(st.nextToken()));
        }

        Collections.sort(list);

        long first = list.get(0);

        long cur = list.remove(0);
        sb.append(cur).append(" ");
        while (list.size()!=0) {
            long next = cur * 2;
            if (list.contains(next)) {
                list.remove(next);
                cur = next;
                sb.append(next).append(" ");
            } else if (cur % 3==0 && list.contains(cur / 3)) {
                next = cur / 3;
                list.remove(next);
                cur = next;
                sb.append(next).append(" ");
            } else {
                break;
            }
        }

        cur = first;
        while (list.size()!=0) {
            long next = cur * 3;
            if (list.contains(next)) {
                list.remove(next);
                cur = next;
                sb.insert(0, next + " ");
            } else if (cur % 2==0 && list.contains(cur / 2)){
                next = cur / 2 ;
                list.remove(next);
                cur = next;
                sb.insert(0,next + " ");
            }
        }

        System.out.println(sb);
        br.close();
    }

}