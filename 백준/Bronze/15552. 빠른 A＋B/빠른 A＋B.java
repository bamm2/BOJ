import java.io.IOException;
import java.io.StreamTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        StreamTokenizer st = new StreamTokenizer(System.in);
        StringBuilder sb = new StringBuilder();

        st.nextToken();
        int T =(int)st.nval;
        while(T-->0){
            st.nextToken();
            int a = (int)st.nval;
            st.nextToken();
            int b = (int)st.nval;
            sb.append(a + b).append("\n");
        }
        System.out.println(sb);
    }
}