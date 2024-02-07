import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static class Number {
        int num, idx;

        public Number(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] ans = new int[N];

        Map<Integer, Integer> countingMap = new HashMap<>();
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
            countingMap.put(num, countingMap.getOrDefault(num, 0) + 1);
        }

        Stack<Number> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            int curr = arr[i];
            if (stack.isEmpty()) stack.push(new Number(curr, i));
            else {
                if (countingMap.get(curr) > countingMap.get(stack.peek().num)) { // 현재 들어가는 수의 오등큰수가 이전 수보다 크면
                    while (!stack.isEmpty()) {
                        Number checker = stack.peek();
                        if (countingMap.get(checker.num) >= countingMap.get(curr)) break;  // 스택안에 있는 오등큰수가 크거나 같으면 종료
                        ans[stack.pop().idx] = curr;
                    }
                }
                stack.push(new Number(curr, i));
            }
        }

        while (!stack.isEmpty()) {
            ans[stack.pop().idx] = -1;
        }

        for(int i=0;i<N;i++) sb.append(ans[i]).append(" ");

        System.out.println(sb);
        br.close();

    }
}