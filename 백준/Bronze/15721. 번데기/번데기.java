import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int A =Integer.parseInt(br.readLine()); // 사람 수 
		int T =Integer.parseInt(br.readLine()); // 뻔 또는 데기를 말한 횟수 
		int BorD = Integer.parseInt(br.readLine()); // 0: 뻔 , 1: 데기 
		
		int people=0;
		
		int Bcnt=0; // 뻔 횟수 
		int Dcnt=0; // 데기 횟수 
		int cycle=1; // 0101 다음 증가하는 0011 
		
		loop:while(true) {
			
			for(int i=0;i<4;i++) {
				if(i%2==0) {
					Bcnt++;
					people++;
					if( (BorD==0 && Bcnt==T) || (BorD==1 && Dcnt==T))  break loop;
				}else {
					Dcnt++;
					people++;
					if( (BorD==0 && Bcnt==T) || (BorD==1 && Dcnt==T))  break loop;
				}
			}
			
			for(int i=0;i<cycle+1;i++) {
				Bcnt++;
				people++;
				if( (BorD==0 && Bcnt==T) || (BorD==1 && Dcnt==T))  break loop;
			}
			for(int i=0;i<cycle+1;i++) {
				Dcnt++;
				people++;
				if( (BorD==0 && Bcnt==T) || (BorD==1 && Dcnt==T))  break loop;
			}
			
			cycle++;
			
			if( (BorD==0 && Bcnt==T) || (BorD==1 && Dcnt==T))  break;
			
		}
		
		people=people%A-1; // 0번부터 시작 
		if(people==-1) { // 끝 번호일 경우 맨 마지막 수  
			people=A-1;
		}
		System.out.println(people);
		
	}// main
}