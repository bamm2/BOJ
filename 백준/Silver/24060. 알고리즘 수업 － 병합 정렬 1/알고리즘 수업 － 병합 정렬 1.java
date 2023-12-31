import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] sortedArr;
    static int N, K, time, ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        sortedArr = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        mergeSort(arr, 0, N - 1);

        System.out.println(ans==0 ? -1 : ans);
    }

    private static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) return;

        int mid = (left + right) / 2;

        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);

    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int L = left;
        int R = mid + 1;
        int idx = left;

        while (L <= mid && R <= right) {
            if (arr[L] <= arr[R]) {
                sortedArr[idx++] = arr[L++];
            } else {
                sortedArr[idx++] = arr[R ++];
            }
            time++;
            if (time==K) ans = sortedArr[idx - 1];
        }

        if (L <= mid) {
            while (L <= mid) {
                sortedArr[idx++] = arr[L++];
                time++;
                if (time==K) ans = sortedArr[idx - 1];
            }
        } else {
            while (R <= right) {
                sortedArr[idx++] = arr[R++];
                time++;
                if (time==K) ans = sortedArr[idx - 1];
            }
        }

        for(int i=left;i<=right;i++){
            arr[i] = sortedArr[i];
        }

    }
}