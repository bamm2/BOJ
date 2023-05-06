import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

/*
    뻔 : 0  , 데기 : 1
 */
    static int zero,one,idx,N,turn,BorD;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N=Integer.parseInt(br.readLine()); // 인원 수
        turn=Integer.parseInt(br.readLine()); // 구하고자 하는 번째
        BorD=Integer.parseInt(br.readLine()); // 뻔 or 데기

         idx=0;
         zero=0;
         one=0 ;

        int loopIdx = 2;
        loop:
        while(true) {
            if(zero()) break;
            if(one()) break;
            if(zero()) break;
            if(one()) break;

            for (int i = 0; i < loopIdx; i++) {
                if(zero()) break loop;
            }
            for (int i = 0; i < loopIdx; i++) {
                if(one()) break loop;
            }

            loopIdx++;
        }

        System.out.println((idx-1)%N);

    }
    private static boolean zero(){
        zero++;
        idx++;
        if( isOk() ){
            return true;
        }
        return false;
    }

    private static boolean one(){
        one++;
        idx++;
        if( isOk() ){
            return true;
        }
        return false;
    }

    private static boolean isOk(){
        return (BorD == 0 && zero == turn)  || (BorD == 1 && one ==turn) ;
    }
}