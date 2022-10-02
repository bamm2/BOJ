import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        List<Integer> list =new LinkedList<>();
        int N = Integer.parseInt(br.readLine()); // 학생 수

        st = new StringTokenizer(br.readLine(), " ");
        for (int i =0; i < N; i++) {
            list.add(i-Integer.parseInt(st.nextToken()),i+1);
        }

        for(int i=0;i<list.size();i++){
            System.out.print(list.get(i)+" ");
        }

    }
}