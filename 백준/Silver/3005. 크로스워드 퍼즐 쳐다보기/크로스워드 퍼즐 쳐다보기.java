import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");

        int R=Integer.parseInt(st.nextToken());
        int C=Integer.parseInt(st.nextToken());

        char[][] map =new char[R][C];
        List<String> list =new ArrayList<>();
        for(int i=0;i<R;i++){
            String s = br.readLine();
            list.add(s);
            for(int j=0;j<C;j++){
                map[i][j]=s.charAt(j);
            }
        }

        for(int i=0;i<C;i++){
            String s="";
            for(int j=0;j<R;j++){
                s+=map[j][i];
            }
            list.add(s);
        }
        List<String> ans = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            String[] split = list.get(i).split("#");
            for(int j=0;j<split.length;j++){
                if(split[j].length()>=2) ans.add(split[j]);
            }
        }

        Collections.sort(ans);

        System.out.println(ans.get(0));

    }
}