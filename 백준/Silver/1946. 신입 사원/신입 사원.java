import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Person implements Comparable<Person> {
        int document,interview;
        Person(int document,int interview){
            this.document=document;
            this.interview=interview;
        }
        @Override
        public int compareTo(Person o) {
            return Integer.compare(this.document,o.document);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T=Integer.parseInt(br.readLine());
        for(int tc=1;tc<=T;tc++) {
            ArrayList<Person> list= new ArrayList<>();
            int N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                list.add(new Person(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()))) ;
            }

            Collections.sort(list);

            int ans = 1;  // 자기 자신
            int comp=list.get(0).interview;
            for (int i = 1; i < list.size(); i++) {
                int chk=list.get(i).interview;
                if(comp>chk) {
                    comp=chk;
                    ans++;
                }
            }
            System.out.println(ans);
        }
        br.close();
    }
}