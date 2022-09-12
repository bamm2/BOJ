import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main { // 백준

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s =br.readLine();
		
		String[] arr=s.split(" ");
		
		StringBuilder sb =new StringBuilder();
		
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[i].length();j++) {
				if('A'<=arr[i].charAt(j) && arr[i].charAt(j) <='Z') {
					if(arr[i].charAt(j)+13>=91) {
						sb.append((char)(arr[i].charAt(j)+13-26));
					}else {
						sb.append((char)(arr[i].charAt(j)+13));
					}
				}else if ('a'<=arr[i].charAt(j) && arr[i].charAt(j) <='z') {
					if(arr[i].charAt(j)+13>=123) {
						sb.append((char)(arr[i].charAt(j)+13-26));
					}else {
						sb.append((char)(arr[i].charAt(j)+13));
					}
				}else {
					sb.append(arr[i].charAt(j));
				}
			}
			sb.append(" ");
		}
		System.out.println(sb);
		
		
	} // main
}