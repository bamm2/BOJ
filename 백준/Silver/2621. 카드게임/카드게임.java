import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 1. 5장 같은 색 , 연속 숫자 -> 가장 높은수 + 900
 2. 4장 같은 수 -> 같은 수 + 800
 3. 3장 같은 수 + 나머지 2장 같은 수 -> 3장 같은수 * 10 + 2장 같은 수 + 700
 4. 5장 같은 색 -> 가장 높은 수 + 600
 5. 5장 연속 수 -> 가장 높은 수 + 500
 6. 3장 같은 수 - > 같은 수 + 400
 7. 2장 같은 수 , 나머지 3장 중 2장 같은 수 -> 2장 같은 큰수 *10 + 작은수 + 300
 8. 2장 같은 수 -> 같은 수 + 200
 9. 해당 없음 -> 가장 큰 수 + 100
 */

public class Main {

    static int[] numChk;
    static char[] charArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        numChk =new int[10]; // 1 ~ 9 숫자 체크
        charArr =new char[5];

        for(int i=0;i<5;i++){
            st=new StringTokenizer(br.readLine()," ");
            charArr[i]=st.nextToken().charAt(0);
            numChk[Integer.parseInt(st.nextToken())]++;
        }

        int ans=0;
        if(colorCompare() && !notContiNum()){ // 1
            ans=maxNum()+900;
        }else if(checkNum(4) ){ // 2
            ans=cntNum(4)+800;
        }else if(checkNum(3) && checkNum(2)){ // 3
            ans=cntNum(3)*10 + cntNum(2) + 700;
        }else if(colorCompare()){ // 4
            ans=maxNum()+600;
        }else if (!notContiNum()){ // 5
            ans=maxNum()+500;
        }else if(checkNum(3)){ // 6
            ans=cntNum(3)+400;
        }else if(checkNum(2)){ // 7 , 8
            int num1=cntNum(2);
            int num2=0;
            for(int i=1;i<10;i++){
                if(numChk[i]==2 && i!=num1 ){
                    num2=i;
                }
            }
            if(num2==0) ans=cntNum(2)+200; // 8
            else {
                ans=num1>num2?num1*10+num2+300:num1+num2*10+300; // 7
            }
        }else{ // 9
            ans=maxNum()+100;
        }
        System.out.println(ans);
    }

    // 5장 모두 같은 색 비교
    private static boolean colorCompare(){
        for(int i=0;i<4;i++)
            if(charArr[i]!=charArr[i+1]) return false;
        return true;
    }

    // 연속된 수 아닌지 비교
    private static boolean notContiNum(){
        boolean flag =false;
        int chk=0;
        int cnt=0;
        for(int i=1;i<10;i++){
            if(numChk[i]==1 && chk==0){
                chk=i;
                cnt++;
            }else if(numChk[i]==1){
                if(chk==i-1){
                    flag=false;
                    chk=i;
                    cnt++;
                    if(cnt==5) break;
                }else{
                    flag=true;
                    break;
                }
            }else{
                flag=true;
            }
        }
        return flag;
    }

    // 가장 큰 수 찾기
    private static int maxNum(){
        int num=0;
        for(int i=9;i>0;i--){
            if(numChk[i]>0) {
                num=i;
                break;
            }
        }
        return num;
    }

    // 동일한 숫자 개수 체크
    private static boolean checkNum(int num){
        boolean flag=false;
        for(int i=1;i<10;i++){
            if(numChk[i]==num){
                flag=true;
                break;
            }
        }
        return flag;
    }

    // 동일한 숫자 번호 체크
    private static int cntNum(int num){
        int number=0;
        for(int i=1;i<10;i++){
            if(numChk[i]==num){
                number=i;
                break;
            }
        }
        return number;
    }

}