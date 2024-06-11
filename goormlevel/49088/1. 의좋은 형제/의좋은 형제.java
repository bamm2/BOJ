import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =new StringTokenizer(br.readLine()," ");
		int J = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		int D = Integer.parseInt(br.readLine());
		int day = 1;
		do{
			int amount;
			if(day%2 == 1){ // 진우 
				amount = J%2 ==0 ? J/2 : J/2+1;
				J-=amount;
				S+=amount;
			}	else{ // 선우 
					amount = S%2 ==0 ? S/2 : S/2+1;
				S-=amount;
				J+=amount;
			}
			++day;
		}while(day <= D);
		
		System.out.println(J+ " " + S);
	}
}