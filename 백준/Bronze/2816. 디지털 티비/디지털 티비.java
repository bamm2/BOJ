import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static String[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();

        int N=Integer.parseInt(br.readLine());
        arr =new String[N];
        for(int i=0;i<N;i++){
            arr[i]=br.readLine();
        }

        //포인터
        int point=0;

        // KBS1 찾기
        while(true){
            if(arr[point].equals("KBS1")) break;
            sb.append(1);
            point++;
        }
        // KBS1 [0]으로 올리기
        while(true){
            if(point==0) break;
            point--;
            swap(point,point+1);
            sb.append(4);
        }

        // KBS2 찾기
        while(true){
            if(arr[point].equals("KBS2")) break;
            sb.append(1);
            point++;
        }
        // KBS2 [1]으로 올리기
        while(true){
            if(point==1) break;
            point--;
            swap(point,point+1);
            sb.append(4);
        }

        System.out.println(sb);

    }

    private static void swap(int a, int b){
        String tmp=arr[a];
        arr[a]=arr[b];
        arr[b]=tmp;
    }

}