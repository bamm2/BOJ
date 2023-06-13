import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();

        int ans = -1 ;

        boolean isPalin = true;
        boolean isAllSame = true;

        for(int i=0 ;i<str.length()/2 ; i++){
            if(str.charAt(i)!=str.charAt(str.length()-i-1)){
                isPalin=false; // 팰린드롬 수가 아니다 !
                break;
            } else if ( str.charAt(i) != str.charAt(i+1)) {
                isAllSame=false; // 팰린드롬 수인지 체크 중인데, 모두 같은 문자가 아니면 false로 변환
            }
        }

        if(isPalin && isAllSame){ // 팰린이면서 모두 같은 문자이면 -1 
            ans = -1;
        }else if(isPalin){ // 팰린이면서 모두 같은 문자가 아니면 문자열의 -1 하면 팰린드롬 수가 아니게 됨 
            ans = str.length()-1;
        }else{ // 팰린드롬수가 아니면 문자열 길이 만큼 
            ans = str.length();
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}
