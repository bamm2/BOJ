import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, goal[];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        goal = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            goal[i] = Integer.parseInt(st.nextToken());
        }

        solve();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void solve() {
        List<Integer> list = new ArrayList<>();
        int tmp = -1;
        int idx = -1;
        for (int i = goal.length - 1; i >= 0; i--) {
            if (tmp < goal[i]) {
                list.add(goal[i]);
                tmp = goal[i];
            } else { //뒤쪽으로 오름차순이 아닌 경우 스왑해야 다음 순서를 찾을 수 있음
                Collections.sort(list); // 오름차순으로 모아놓은 수 중에서 가장 작은 수 찾기
                int removeIdx = 0;
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j) > goal[i]) {
                        removeIdx = j;
                        break;
                    }
                }
                int swapNumber = list.get(removeIdx); // 오름차순 수 중에서 현재 스왑위치보다는 크면서 가장 작은 수
                idx = i ;
                list.remove(removeIdx); // 이미 넣은 가장 작은 수 제거
                list.add(goal[i]); // 스왑할 숫자 리스트에 넣어주기
                goal[i] = swapNumber; // 현재위치에 들어갈 수 있는 가장 작은 수 넣기 ( 스왑 )
                break;
            }
        }
        if (idx==-1) sb.append(-1); // 가장 마지막 수 일 경우
        else {

            Collections.sort(list); // 남은 수 중 가장 작은 수부터 정렬
            for (int i = 0; i <= idx; i++) { // 앞에 존재했던 수는 그대로 유지 ( 스왑 이미 진행 )
                sb.append(goal[i]).append(" ");
            }
            for (Integer next : list) {
                sb.append(next).append(" ");
            }
        }
    }

}