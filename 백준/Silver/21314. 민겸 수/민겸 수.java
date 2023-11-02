import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

    static StringBuilder maxValue = new StringBuilder();
    static StringBuilder minValue = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        StringBuilder currMaxStr = new StringBuilder();
        StringBuilder currMinStr = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            currMinStr = makeMinValue(currMinStr,c);
            currMaxStr = makeMaxValue(currMaxStr,c);
        }

        if(currMinStr.length()!=0){
            minValue.append(currMinStr);
        }
        if(currMaxStr.length()!=0){
            for(int i =0 ; i< currMaxStr.length();i++){
                maxValue.append("1");
            }
        }
        BigInteger max = new BigInteger(maxValue.toString());
        BigInteger min = new BigInteger(minValue.toString());
        System.out.println(max);
        System.out.println(min);

    }

    private static StringBuilder makeMinValue(StringBuilder sb, char c) {
        if (sb.length()==0) {
            if (c=='M') {
                return sb.append("1");
            } else {
                minValue.append("5");
                return new StringBuilder();
            }
        } else {
            if (c=='M') {
                return sb.append("0");
            } else {
                minValue.append(sb).append("5");
                return new StringBuilder();
            }
        }
    }

    private static StringBuilder makeMaxValue(StringBuilder sb, char c) {
        if (sb.length()==0) {
            if (c=='M') {
                return sb.append("1");
            } else {
                maxValue.append("5").append(sb);
                return new StringBuilder();
            }
        } else {
            if (c=='M') {
                return sb.append("0");
            } else {
                sb.deleteCharAt(0);
                sb.append("0");
                maxValue.append("5").append(sb);
                return new StringBuilder();
            }
        }
    }
}