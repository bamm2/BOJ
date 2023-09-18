import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();
        HashSet<Integer> hs =new HashSet<>();
        StringTokenizer st;

        int N =Integer.parseInt(br.readLine());

        int[] arr =new int[N];
        List<Integer> sortedList =new ArrayList<>();
        st=new StringTokenizer(br.readLine()," ");
        for(int i=0;i<N;i++){
            arr[i]=Integer.parseInt(st.nextToken());
            if(!hs.contains(arr[i])) {
                hs.add(arr[i]);
                sortedList.add(arr[i]);
            }
        }

        Collections.sort(sortedList);

        for(int i=0;i<N;i++){
            int num = arr[i];
            int left= 0;
            int right = sortedList.size()-1;
            while(left<=right){
                int mid = (left+right)/2;
                if(sortedList.get(mid)<num){
                    left=mid+1;
                }else{
                    right=mid-1;
                }
            }
            sb.append(left).append(" ");
        }

        System.out.println(sb);

    }
}