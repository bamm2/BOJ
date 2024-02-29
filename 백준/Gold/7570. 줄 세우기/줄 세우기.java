import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, idxArr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        idxArr = new int[N + 1];
        st = new StringTokenizer(br.readLine(), " ");

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            idxArr[arr[i]] = i;
        }

        int maxCnt = 1;
        int currIdx = idxArr[1];
        int cnt = 1;
        for (int i = 2; i <= N; i++) {
            if (idxArr[i] > currIdx) { // 현재 숫자의 인덱스 위치가 이전 숫자보다 뒤에 있다면 연속하는 경우
                cnt++;
            } else { // 현재 숫자의 인덱스 위치가 이전 숫자보다 앞에 있다면 연속 증가하지 않으므로 초기화
                if (maxCnt < cnt) maxCnt = cnt;
                cnt = 1;
            }
            currIdx = idxArr[i]; // 현재 숫자의 idx 로 변경  
        }

        System.out.println(N - maxCnt); // 가장 길게 증가하는 부분은 고정되어 있으면 되기에 전체에서 고정된 부분 빼기
        br.close();
    }
}