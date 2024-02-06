import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        List<Integer> weights = new ArrayList<>();

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            weights.add(Integer.parseInt(st.nextToken()));
        }

        int M = Integer.parseInt(br.readLine());
        List<Integer> boxes = new ArrayList<>();

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M; i++) {
            boxes.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(weights);
        Collections.sort(boxes);

        int time = 0;
        while (true) {
            if (boxes.size()==0 || weights.size()==0) break;
            time++;

            for (int i = weights.size() - 1; i >= 0; i--) {
                int weight = weights.get(i);
                boolean flag = false;
                for (int j = boxes.size() - 1; j >= 0; j--) {
                    if (weight >= boxes.get(j)) {
                        boxes.remove(j);
                        flag = true;
                        break;
                    }
                }
                if (!flag) weights.remove(i);
            }
        }

        if (boxes.size()!=0) time = -1;

        System.out.println(time);
        br.close();

    }
}