import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N , M ,map[][] ,select[];
    static int answer= Integer.MAX_VALUE;
    static class ChickenStore{
        int r,c;
        ChickenStore(int r,int c){
            this.r=r;
            this.c=c;
        }
    }
    static List<ChickenStore> storeList;

    static class House{
        int r,c;
        House(int r,int c){
            this.r=r;
            this.c=c;
        }
    }

    static List<House> houseList;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        map=new int[N][N];
        select=new int[M];
        storeList=new ArrayList<>();
        houseList=new ArrayList<>();

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<N;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
                if(map[i][j]==2) storeList.add(new ChickenStore(i,j));
                if(map[i][j]==1) houseList.add(new House(i,j));
            }
        }

        solve(0,0);

        System.out.println(answer);

    }

    private static void solve(int start, int idx){
        if(idx==M){
            answer = Math.min(answer,findDistance());
            return;
        }

        for(int i=start;i<storeList.size();i++){
            select[idx]=i;
            solve(i+1,idx+1);
        }
    }

    private static int findDistance() {
        int sum = 0;

        for(int i=0;i< houseList.size();i++){
            House curr = houseList.get(i);
            int min =Integer.MAX_VALUE;
            for(int j=0;j<select.length;j++){
                ChickenStore chickenStore = storeList.get(select[j]);
                int distance = getDistance(curr.r, curr.c, chickenStore.r, chickenStore.c);
                min = Math.min(min,distance);
            }
            sum+=min;
        }
        return sum;
    }

    private static int getDistance(int hR,int hC , int sR, int sC ){
        return Math.abs(hR-sR)+Math.abs(hC-sC);
    }
}
