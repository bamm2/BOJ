import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T= Integer.parseInt(br.readLine());
        String[] vowel={"a","e","i","o","u"};

        for(int tc=1;tc<=T;tc++){
                String ans= br.readLine();
                for(int j=0;j<vowel.length;j++){
                    ans=ans.replace(vowel[j],"");
                }
            System.out.println("#"+tc+" "+ans);
        }//tc for
    }//main
}