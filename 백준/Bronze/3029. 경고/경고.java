import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String A=br.readLine();
		String B=br.readLine();
		
		if(A.equals(B)) {
			System.out.println("24:00:00");
			System.exit(0);
		}
		
		int h=Integer.parseInt(B.split(":")[0])-Integer.parseInt(A.split(":")[0]);
		int m=Integer.parseInt(B.split(":")[1])-Integer.parseInt(A.split(":")[1]);
		int s=Integer.parseInt(B.split(":")[2])-Integer.parseInt(A.split(":")[2]);
		
		
		if(s<0) {
			s+=60;
			--m;
		}
		if(m<0) {
			m+=60;
			--h;
		}
		if(h<0) {
			h+=24;
		}
			System.out.format("%02d:%02d:%02d",h,m,s);
		
		
	}//main
	
}