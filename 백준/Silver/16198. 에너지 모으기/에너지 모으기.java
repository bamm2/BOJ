import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int n, ans ;
    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        n =Integer.parseInt(br.readLine());

        list =new ArrayList<>();

        st=new StringTokenizer(br.readLine()," ");
        for(int i=0;i<n;i++){
           list.add(Integer.parseInt(st.nextToken()));
        }

        solve(0);

        System.out.println(ans);
    }

    private static void solve(int sum){
        if(list.size()<=2){
            if(ans<sum) ans =sum;
            return;
        }

        for(int i=1;i<list.size()-1;i++){
            int curr =list.get(i);
            int multiple = list.get(i-1)*list.get(i+1);
            list.remove(i);
            solve(sum+multiple);
            list.add(i,curr);
        }


    }
}