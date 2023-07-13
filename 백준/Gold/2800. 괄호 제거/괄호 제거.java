import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int size,bracketCnt;
    static boolean[] isBracket;
    static char[] arr;
    static String s;
    static boolean[] visitedBracket;
    static Stack<Integer> stack;
    static HashSet<String> hs;
    static List<Pair> list;
    static List<String> answer;
    static class Pair{
        int lIdx,rIdx;
        Pair(int lIdx,int rIdx){
            this.lIdx=lIdx;
            this.rIdx=rIdx;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s = br.readLine();
        arr = s.toCharArray();

        stack =new Stack<>();
        hs =new HashSet<>();
        size=arr.length;
        list=new ArrayList<>();
        answer= new ArrayList<>();
        isBracket = new boolean[size];

        bracketCnt = 0;
        for(int i=0;i<size;i++){
            char curr = arr[i];
            if(curr=='(' ){
                stack.push(i);
                bracketCnt++;
            }else if(curr==')'){
                list.add(new Pair(stack.pop(),i));
            }
        }

        visitedBracket=new boolean[bracketCnt];
        solve(0);

        Collections.sort(answer);
        StringBuilder sb =new StringBuilder();
        for(String str : answer){
            sb.append(str).append('\n');
        }

        System.out.println(sb);
    }

    private static void solve(int idx){
        if(idx==bracketCnt) {
            for(int i=0;i<bracketCnt;i++){
                int rIdx = list.get(i).rIdx;
                int lIdx = list.get(i).lIdx;
                if(visitedBracket[i]) {
                    isBracket[rIdx] = isBracket[lIdx] = true;
                }else{
                    isBracket[rIdx] = isBracket[lIdx] = false;
                }
            }
            makeString();
            return;
        }

        visitedBracket[idx]=true;
        solve(idx+1);
        visitedBracket[idx]=false;
        solve(idx+1);
    }


    private static void makeString(){
        String str = "";
        for(int i=0;i<size;i++){
            if(isBracket[i]) continue;
            str+=arr[i];
        }
        if(!hs.contains(str) && !str.equals(s)) {
            hs.add(str);
            answer.add(str);
        }
    }
}