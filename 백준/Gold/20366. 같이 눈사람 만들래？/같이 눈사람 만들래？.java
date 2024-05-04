import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Point {
        int idxOne, idxTwo;
        int sum;

        public Point(int idxOne, int idxTwo, int sum) {
            this.idxOne = idxOne;
            this.idxTwo = idxTwo;
            this.sum = sum;
        }

        boolean contains(int compIdxOne, int compIdxTwo) {
            return idxOne==compIdxOne || idxOne==compIdxTwo || idxTwo==compIdxOne || idxTwo==compIdxTwo;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        List<Point> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i==j) continue;
                int sum = arr[i] + arr[j];
                list.add(new Point(i, j, sum));
            }
        }

        list.sort((Comparator.comparingInt(o -> o.sum)));

        int left = 0;
        int right = 0;
        int minDiff = Integer.MAX_VALUE;
        while (right < list.size()) {
            Point L = list.get(left);
            Point R = list.get(right);

            if (left==right) right++;
            else left++;

            if (R.contains(L.idxOne, L.idxTwo)) continue;

            minDiff = Math.min(R.sum - L.sum, minDiff);
            if (minDiff==0L) break;
        }

        System.out.println(minDiff);
        br.close();
    }
}
