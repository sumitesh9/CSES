import java.util.*;
import java.io.*;
public class CSES1651 {
    static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;

    static int ni() {
        return Integer.parseInt(st.nextToken());
    }

    static int n;

    /*
        In range update and point sum queries we can just swap update() and query()
        and rest is same to point update and range sum queries

        Also notice that tree is initialised by actually calling update() , contrary to
        building the tree in previous problems

        Reference -> https://codeforces.com/blog/entry/18051
    */

    static class RMQ {
        private long t[];

        public RMQ(int a[]) {
            t = new long[2 * a.length];
        }

        public long query(int pos) {
            long ans = 0l;
            for (pos += n; pos > 0; pos /= 2) ans += t[pos];
            return ans;
        }

        public void update(int l , int r , int val) {
            if (l >= r) return;
            for (l += n , r += n; l < r; l /= 2 , r /= 2) {
                if ((l & 1) == 1) t[l++] += val;
                if ((r & 1) == 1) t[--r] += val;
            }
        }
    }

    public static void main(String args[])throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        pw = new PrintWriter(System.out);


        n = ni(); int q = ni();
        int a[] = new int[n];
        st = new StringTokenizer(br.readLine());

        RMQ t = new RMQ(a);
        for (int i = 0; i < n; ++i) {
            a[i] = ni();
            t.update(i , i + 1 , a[i]);
        }
        StringBuffer sb = new StringBuffer("");

        for (int i = 0; i < q; ++i) {
            st = new StringTokenizer(br.readLine());
            int type = ni();
            if (type == 1) {
                int l = ni(); int r = ni(); int u = ni();
                t.update(l - 1 , r , u);
            }

            else {
                int k = ni();
                sb.append(t.query(k - 1) + "\n");
            }
        }
        pw.println(sb);
        pw.close();
        br.close();
    }
}