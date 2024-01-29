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

    static int N, arr[];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solve();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void solve() {
        List<Integer> candidates =new ArrayList<>();
        boolean flag =false;
        int comp = Integer.MAX_VALUE;
        int idx = 0;
        for (int i = N - 1; i >= 0; i--) { // 끝에서부터 내림차수인지 체크
            if (comp > arr[i]) {
                comp = arr[i];
                candidates.add(arr[i]);
            } else { // 내림 차수가 아닌 수가 등장
                flag=true;
                Collections.sort(candidates);
                int tmp ;
                for(int j=candidates.size()-1;j>=0;j--){
                    if(arr[i]>candidates.get(j)){
                        idx = i;
                        tmp = arr[i];
                        arr[i]=candidates.get(j);
                        candidates.remove(j);
                        candidates.add(tmp);
                        break;
                    }
                }
                break;
            }
        }

        if(!flag) sb.append("-1");
        else {
            for(int i=0;i<=idx;i++){
                sb.append(arr[i]).append(" ");
            }
            Collections.sort(candidates,Collections.reverseOrder());
            for(Integer next : candidates){
                sb.append(next).append(" ");
            }
        }
    }

}