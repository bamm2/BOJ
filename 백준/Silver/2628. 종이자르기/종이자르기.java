import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int C = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        LinkedList<Integer> rlist= new LinkedList<>();
        LinkedList<Integer> clist= new LinkedList<>();

        int N = Integer.parseInt(br.readLine()); // 자르는 종이 횟수

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine()," ");
            if(Integer.parseInt(st.nextToken())==0){
                rlist.add(Integer.parseInt(st.nextToken()));
            }else{
                clist.add(Integer.parseInt(st.nextToken()));
            }
        }

        rlist.add(R);
        clist.add(C);
        rlist.add(0);
        clist.add(0);

        Collections.sort(rlist);
        Collections.sort(clist);
        int maxr=0;
        for(int i= rlist.size()-1;i>0;i--){
            int tmp=rlist.get(i)-rlist.get(i-1);
            if(maxr<tmp){
               maxr=tmp;
            }
        }

        int maxc=0;
        for(int i= clist.size()-1;i>0;i--){
            int tmp=clist.get(i)-clist.get(i-1);
            if(maxc<tmp){
                maxc=tmp;
            }
        }
        System.out.println(maxr*maxc);
    }
}