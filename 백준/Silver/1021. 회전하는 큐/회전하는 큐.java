import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static LinkedList<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        st=new StringTokenizer(br.readLine()," ");
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());

        int[] arr=new int[M];

        st=new StringTokenizer(br.readLine()," ");
        for(int i=0;i<M;i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }

        list=new LinkedList<>();

        for(int i=1;i<=N;i++) {
            list.add(i);
        }

        int cnt=0;

        for(int i=0;i<M;i++){
            int num=arr[i];
            if(chk(num)){
                while(!list.isEmpty()){
                    if(list.peek()==num){
                        list.poll();
                        break;
                    }
                    int tmp=list.pollFirst();
                    list.addLast(tmp);
                    cnt++;
                }
            }else{
                while(!list.isEmpty()){
                    if(list.peek()==num){
                        list.poll();
                        break;
                    }
                    int tmp=list.pollLast();
                    list.addFirst(tmp);
                    cnt++;
                }
            }
        }
        System.out.println(cnt);

    }
    private static boolean chk(int num){
        for(int i=0;i<=list.size()/2;i++) {
            if(num==list.get(i)){
                return true;
            }
        }
        return false;
    }
}