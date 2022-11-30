import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));

        while(true){
            String s =br.readLine();
            if(s.equals("0")) break;

            char[] arr=s.toCharArray();
            boolean chk=false;
            for(int i=0;i<s.length()/2;i++){
                if(!(arr[i]==arr[s.length()-1-i])){
                    chk=true;
                    break;
                }
            }
            if(chk) System.out.println("no");
            else System.out.println("yes");
        }
        br.close();
    }
}
