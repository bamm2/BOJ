import javafx.geometry.Pos;

import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();

        String num=br.readLine();

        String[] numArray={"000","001","010","011","100","101","110","111"};

        for(int i=0,size=num.length();i<size;i++){
                  sb.append(numArray[num.charAt(i) - '0']);
        }

        int numLen=num.length();
        if(num.equals("0") && numLen==1) System.out.println(num);
        else {
            while (sb.charAt(0) == '0') {
                sb.deleteCharAt(0);
            }
            System.out.println(sb);
        }
    }
}