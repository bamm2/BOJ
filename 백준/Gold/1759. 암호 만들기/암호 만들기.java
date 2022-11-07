import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int L , C ;
    static char[] arr,ans;

    static char[] vowel= { 'a', 'e', 'i', 'o', 'u'};
    static List<String> list =new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");
        L=Integer.parseInt(st.nextToken()); // 문자 길이
        C=Integer.parseInt(st.nextToken()); // 문자 개수

        arr =new char[C];
        ans =new char[L];

        st=new StringTokenizer(br.readLine()," ");
        for(int i=0;i<C;i++){
            arr[i]=st.nextToken().charAt(0);
        }
        Arrays.sort(arr);

        comb(0,0);

        for(int i=0;i<list.size();i++)
            bw.write(list.get(i)+"\n");

        bw.flush();
        bw.close();
        br.close();
    }
    private static void comb(int idx, int start){
        if(idx==L){
            if(chk()){
                list.add(makeString());
            }
            return;
        }

        for(int i=start; i<C ; i++){
            ans[idx]=arr[i];
            comb(idx+1,i+1);
        }
    }

    private static String makeString(){
        String s="";
        for(int i=0;i<L;i++){
            s+=ans[i];
        }
        return s;
    }
    private static boolean chk(){
       int cnt=0;
        for(int i=0;i<L;i++){
            for(int d=0;d<5;d++){
                if(ans[i]==vowel[d]){
                    cnt++;
                    break;
                }
            }
        }
        if(cnt>=1 && L-cnt>=2) return true;
        return false;
    }
}
