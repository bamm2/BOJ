import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T =Integer.parseInt(br.readLine());
		
		int sum = 0;
		while(T-->0){
			st = new StringTokenizer(br.readLine()," ");
			int A = Integer.parseInt(st.nextToken());
			char command = st.nextToken().charAt(0);
			int B = Integer.parseInt(st.nextToken());
			sum += calculate(A,command,B);
		}
		
		System.out.println(sum);
	}
	private static int calculate(int A, char cmd, int B){
		switch(cmd){
			case '+':
				return A+B;
			case '-':
				return A-B;
			case '*':
				return A*B;
			case '/':
				return A/B;
		}
		return -1;
	}
}