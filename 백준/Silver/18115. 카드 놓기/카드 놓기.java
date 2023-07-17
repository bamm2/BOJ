import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Number {
        int idx, num;

        Number(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
    }

    static Deque<Number> deque;
    static List<Number> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        deque = new ArrayDeque<>();
        list= new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            deque.offer(new Number(0, i));
        }

        st = new StringTokenizer(br.readLine(), " ");
        int pos = N;
        for (int i = 0; i < N; i++) {
            pickedCard(Integer.parseInt(st.nextToken()), pos--);
        }

        int[] arr =new int[N+1];
        for(int i=0;i<N;i++){
            arr[list.get(i).num]=list.get(i).idx;
        }
        for(int i=1;i<=N;i++){
            sb.append(arr[i]).append(" ");
        }

        System.out.println(sb);

    }

    private static void pickedCard(int caseNum, int pos) {
        Number pollNum;
        switch (caseNum) {
            case 1:
                pollNum = deque.poll();
                pollNum.idx = pos;
                list.add(pollNum);
                break;
            case 2:
                Number prev = deque.poll();
                pollNum = deque.poll();
                pollNum.idx = pos;
                deque.push(prev);
                list.add(pollNum);
                break;
            case 3:
                pollNum = deque.pollLast();
                pollNum.idx= pos;
                list.add(pollNum);
                break;
        }
    }
}