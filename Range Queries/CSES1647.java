import java.util.*;
import java.io.*;
public class CSES1647 {
    static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;
 
    static int ni() {
        return Integer.parseInt(st.nextToken());
    }
 
    static int n , q , a[];
 
    /*  
        Range Minimum queries can be efficiently solved using Segment Tree or 
        Square Root Decomposition
 
        RMQ using Segment Tree - Time complexity - O(nlogq)
                                 Space complexity - O(2 * n)
 
        Below is minimal implementation of Segment tree in Java (without update query)
    */
 
    static class RMQ {
        private int t[];
 
        public RMQ(int a[]) {
            t = new int[2 * a.length];
            for (int i = 0; i < n; ++i) t[n + i] = a[i];
 
            for (int i = n - 1; i >= 0; --i) t[i] = Math.min(t[(i << 1)] , t[(i << 1) + 1]);
        }
 
        public int query(int l , int r) {
            int ans = Integer.MAX_VALUE;
            if (l >= r) return ans;
            for (l += n , r += n; l < r; l /= 2 , r /= 2) {
                if ((l & 1) == 1) ans = Math.min(ans , t[l++]);
                if ((r & 1) == 1) ans = Math.min(ans , t[--r]);
            }
            return ans;
        }
    }
 
    public static void main(String args[])throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        pw = new PrintWriter(System.out);
 
        n = ni(); q = ni();
        
        a = new int[n];
 
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) a[i] = ni();
 
        RMQ t = new RMQ(a);
 
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < q; ++i) {
            st = new StringTokenizer(br.readLine());
            int u = ni() - 1;
            int v = ni();
 
            sb.append(t.query(u , v) + "\n");
        }
        pw.println(sb);
 
        pw.close();
        br.close();
    }
}