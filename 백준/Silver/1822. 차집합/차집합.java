import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");
        int nA=Integer.parseInt(st.nextToken());
        int nB=Integer.parseInt(st.nextToken());

        int[] A=new int[nA];
        int[] B=new int[nB];

        st=new StringTokenizer(br.readLine()," ");
        for(int i=0;i<nA;i++){
            A[i]=Integer.parseInt(st.nextToken());
        }

        st=new StringTokenizer(br.readLine()," ");
        for(int i=0;i<nB;i++){
            B[i]=Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);
        Arrays.sort(B);

        List<Integer> list =new ArrayList<>();

        for(int i=0;i<nA;i++){
            int num = A[i];
            int left=0;
            int right=nB-1;
            while (left<=right){
                int mid = (left+right)/2;
                if(B[mid]<num){
                    left=mid+1;
                }else {
                    right=mid-1;
                }
            }
            if(left>=nB || B[left]!=num) list.add(num);
        }

        if(list.size()==0) sb.append("0");
        else{
            sb.append(list.size()).append('\n');
            for(int i=0;i<list.size();i++){
                sb.append(list.get(i)).append(" ");
            }
        }

        System.out.println(sb);

    }
}