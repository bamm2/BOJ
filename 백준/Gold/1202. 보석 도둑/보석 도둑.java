import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Product implements Comparable<Product> {
        int weight, price;

        public Product(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }

        @Override
        public int compareTo(Product o) {
            return this.weight==o.weight ? Integer.compare(o.price, this.price):Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Product[] products = new Product[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            products[i] = new Product(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(products);

        int[] bags = new int[K];
        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(bags);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int idx = 0;
        long sum = 0L;
        for (int i = 0; i < K; i++) {
            int maxWeight = bags[i];
            for (int j = idx; j < N; j++) {
                if (maxWeight < products[j].weight) {
                    idx = j;
                    break;
                }
                pq.offer(products[j].price);
                if (j==N - 1) idx = N;
            }
            if (!pq.isEmpty()) {
                sum += pq.poll();
                continue;
            }
            if (idx==N) break;
        }

        System.out.println(sum);
        br.close();
    }
}