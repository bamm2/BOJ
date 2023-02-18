import java.io.*;
import java.util.*;

public class Main {

    static class Human implements Comparable<Human> {
        String name;
        int ko, eng, math;

        Human(String name, int ko, int eng, int math) {
            this.name = name;
            this.ko = ko;
            this.eng = eng;
            this.math = math;
        }

        @Override
        public int compareTo(Human o) {
            if (this.ko == o.ko && this.eng != o.eng) {
                return this.eng - o.eng;
            } else if (this.ko == o.ko && this.eng == o.eng && this.math != o.math) {
                return o.math - this.math;
            } else if (this.ko == o.ko && this.eng == o.eng && this.math == o.math) {
                return this.name.compareTo(o.name);
            } else {
                return o.ko - this.ko;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N=Integer.parseInt(br.readLine());

        Human[] humans=new Human[N];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine()," ");
            humans[i]=new Human(st.nextToken(),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(humans);

        StringBuilder sb =new StringBuilder();
        for(Human human : humans){
            sb.append(human.name).append('\n');
        }

        System.out.println(sb.toString().trim());
    }
}