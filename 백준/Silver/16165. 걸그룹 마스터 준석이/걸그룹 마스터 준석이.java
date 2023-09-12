import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");
        int N =Integer.parseInt(st.nextToken());
        int M =Integer.parseInt(st.nextToken());

        Map<String,List<String>> map =new HashMap<>();
        for(int i=0;i<N;i++){
            String teamName = br.readLine();
            int totalCnt = Integer.parseInt(br.readLine());
            map.put(teamName,new ArrayList<>());
            for(int j=0;j<totalCnt;j++){
               String memberName = br.readLine();
                map.get(teamName).add(memberName);
            }
        }

        for(int i=0;i<M;i++){
            String str =br.readLine();
            int num = Integer.parseInt(br.readLine());
            if(num==0){
                List<String> s = map.get(str);
                Collections.sort(s);
                for(int j=0;j<s.size();j++){
                    sb.append(s.get(j)).append('\n');
                }
            }else{
                for(Map.Entry<String,List<String>> entry :map.entrySet()){
                    if(entry.getValue().contains(str)){
                        sb.append(entry.getKey()).append('\n');
                        break;
                    }
                }

            }
        }
        System.out.println(sb);

    }
}
