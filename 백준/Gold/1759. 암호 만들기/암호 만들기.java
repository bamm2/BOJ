import java.io.*;
import java.util.*;

public class Main {

    static char word[],alphabet[];
    static int wordLen,N;
     static char[] vowelArr={'a','e','i','o','u'};
    static List<String> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");
        wordLen=Integer.parseInt(st.nextToken());
        N=Integer.parseInt(st.nextToken());

        st=new StringTokenizer(br.readLine()," ");

        word=new char[wordLen];
        alphabet=new char[N];
        for(int i=0;i<N;i++){
            alphabet[i]=st.nextToken().charAt(0);
        }
        Arrays.sort(alphabet);

        solve(0,0);

        for(int i=0;i<list.size();i++)
            bw.write(list.get(i)+"\n");

        bw.flush();
        bw.close();
        br.close();
    }
    private static void solve(int idx,int start){
        if(idx==wordLen){
            int vowelCnt= vowelChk();
            if(vowelCnt>=1 && wordLen-vowelCnt>=2){
                String s="";
                for(int i=0;i<wordLen;i++){
                    s+=word[i];
                }
                list.add(s);
            }
            return;
        }

        for(int i=start;i<N;i++){
            word[idx]=alphabet[i];
            solve(idx+1,i+1);
        }
    }

    private static int vowelChk(){
        int num=0;
        for(int i=0;i<wordLen;i++){
            for(int j=0;j<5;j++) {
                if (word[i]==vowelArr[j]){
                    num++;
                }
            }
        }
        return num;
    }

}