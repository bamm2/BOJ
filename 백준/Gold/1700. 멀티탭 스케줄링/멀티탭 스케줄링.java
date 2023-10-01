import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] tab = new int[N];

        int[] arr = new int[K];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            tab[i] = arr[i];
        }

        int ans = 0 ;

        for (int i = N; i < K; i++) {
            int push = arr[i];

            boolean isStay =false;
            for(int j=0;j<N;j++){
                if(push==tab[j]){
                    isStay=true;
                    break;
                }
            }

            for(int j=0;j<N-1;j++){
                for(int k=j+1;k<N;k++){
                    if(tab[j]==tab[k]){
                        tab[k]=push;
                        isStay=true;
                        break;
                    }
                }
            }

            if(!isStay) {
                ans++;

                int distance = -1;
                int deleteIdx = -1;
                boolean isFind = false;
                for (int j = 0; j < N; j++) {
                    int comp = tab[j];
                    boolean isRemain =false;
                    for (int k = i + 1; k < K; k++) {
                        if (comp == arr[k]) {
                            if (distance < k) {
                                distance = k;
                                deleteIdx = j;
                                isRemain=true;
                                break;
                            }else{
                                isRemain=true;
                                break;
                            }
                        }
                    }
                    if(!isRemain){
                        tab[j]=push;
                        isFind=true;
                        break;
                    }
                }
                if(!isFind) {
                    tab[deleteIdx]=push;
                }
            }
        }

        System.out.println(ans);

    }
}