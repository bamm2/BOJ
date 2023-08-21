import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<Long> list;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine()," ");

        long start = Long.parseLong(st.nextToken());
        long end = Long.parseLong(st.nextToken());

        list =new ArrayList<>();
        visited= new boolean[10_000_001];

        for(int i=2;i<=10_000_000;i++){
            for(int j=i*2 ; j<=10_000_000;j+=i){ // 최소 2의 배수부터 시작이니 *2 / 소수아닌수 제거해주기
                visited[j]=true;
            }
        }

        for(int i=2;i<=10_000_000;i++){
            if(end<i) break;
            if(visited[i]) continue;
            addNumber(i,end);
        }

        Collections.sort(list);
        int left = 0;
        int right = list.size()-1;
        while(left<=right){
            int mid = (left+right)/2;
            if(list.get(mid)<start){
                left=mid+1;
            }else{
                right=mid-1;
            }
        }

        System.out.println(list.size()-left);


    }

    private static void addNumber(int num ,long end){
        int number = num ;

        for(int i=2 ; ; i++){
            long powNum =(long)Math.pow(num, i);
            if(powNum>end) break;
            list.add(powNum);
        }

    }

}