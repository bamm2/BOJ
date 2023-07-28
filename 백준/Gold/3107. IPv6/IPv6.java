import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        String[] arr = s.split(":");

        StringBuilder sb =new StringBuilder();
        if(arr.length==8){
            sb.append(makeIPv6(arr));
            sb.deleteCharAt(0);
            System.out.println(sb);
        }else{
            int idx = s.indexOf("::");
           String[] frontStr = s.substring(0,idx).split(":");
           String[] backStr = s.substring(idx+2).split(":");
           int loopSize = 8 - frontStr.length - backStr.length;
           sb.append(makeIPv6(frontStr));
           sb.append(makeZero(loopSize));
           sb.append(makeIPv6(backStr));
           sb.deleteCharAt(0);
            System.out.println(sb);
        }

    }

    private static StringBuilder makeIPv6(String[] arr){
        StringBuilder tmp =new StringBuilder();
        for(int i=0;i<arr.length;i++){
            if(arr[i].length()!=4){
                tmp.append(":").append(makeStr(arr[i]));
            }else{
                tmp.append(":").append(arr[i]);
            }
        }
        return tmp;
    }

    private static StringBuilder makeStr(String s){
        StringBuilder tmp =new StringBuilder();
        for(int i=0;i<4-s.length();i++){
            tmp.append("0");
        }
        tmp.append(s);
        return tmp;
    }

    private static StringBuilder makeZero(int size){
        StringBuilder tmp =new StringBuilder();
        for(int i=0;i<size;i++){
            tmp.append(":").append("0000");
        }
        return tmp;
    }
}