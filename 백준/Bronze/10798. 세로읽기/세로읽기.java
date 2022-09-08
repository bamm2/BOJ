import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] arr =new String[5];
		int maxlen=0;
		for(int i=0;i<5;i++) {
			arr[i]=br.readLine();
			if(arr[i].length()>maxlen) {
				maxlen=arr[i].length();
			}
		}
		
		char[][] carr =new char[5][maxlen];
		for(int i=0;i<5;i++) {
			for(int j=0;j<arr[i].length();j++) {
				carr[i][j]=arr[i].charAt(j);
			}
		}
		
		
		
		String ans="";
		
		for(int i=0;i<maxlen;i++) {
			for(int j=0;j<5;j++) {
				if(carr[j][i]!='\u0000') {
					ans+=arr[j].charAt(i);
				}else {
					continue;
				}
			}
		}
		System.out.println(ans);
	}// main
}