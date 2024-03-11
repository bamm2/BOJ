import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        int[] idx = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        int prevIdx = -1; // 짝수 시작지점 -1 표시 , 마지막에는 짝수 끝지점의 idx
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (arr[i] % 2==0) {
                idx[i] = prevIdx;
                prevIdx = i;
            }
        }

        if (prevIdx==-1) { // 짝수가 하나도 없는 경우
            System.out.println(0);
            return;
        }

        int maxCnt = 1; // 최소 하나는 존재
        int minusCnt;
        int evenCnt = 0; // 시작과 끝 사이에 짝수 개수
        int endIdx = prevIdx; // 짝수 끝지점
        int startIdx = idx[endIdx]; // 짝수 시작지점
        while (true) {
            if(startIdx == -1 ) break; // 시작지점이 도달할 수 있는 마지막 위치에 있는 경우 종료
            minusCnt = (endIdx - startIdx - 1) - evenCnt; // 짝수 사이에 존재하는 음수 개수

            if (minusCnt > K) { // 기준보다 음수갯수가 많은경우
                endIdx = idx[endIdx]; // 끝지점을 다음 짝수 인덱스로 이동
                evenCnt--; // 사이에 있는 짝수 갯수 하나 줄여주기
            } else { // 기준에 부합하는 경우
                startIdx = idx[startIdx]; // 시작 지점을 다음 짝수 인덱스로 이동
                maxCnt = Math.max(maxCnt, evenCnt + 2); // 최선을 갱신
                evenCnt++; // 짝수 갯수 증가
            }
            if (endIdx==startIdx) break;
        }

        System.out.println(maxCnt);
        br.close();
    }
}