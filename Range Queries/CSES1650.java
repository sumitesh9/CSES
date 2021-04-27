import java.util.*;
import java.io.*;
public class CSES1650 {
    static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;
 
    static int ni() {
        return Integer.parseInt(st.nextToken());
    }
 
    static int n , q , a[];
 
    /*  
        Range Xor queries can be efficiently solved using Segment Tree or 
        Square Root Decomposition
 
        RMQ using Segment Tree - Time complexity - O(nlogq)
                                 Space complexity - O(2 * n)
 
        Below is minimal implementation of Segment tree in Java
        Reference - https://www.geeksforgeeks.org/segment-tree-efficient-implementation/
        			https://codeforces.com/blog/entry/18051
    */
 
    static class RMQ {
        private long t[];
 
        public RMQ(int a[]) {
            t = new long[2 * a.length];
            for (int i = 0; i < n; ++i) t[n + i] = a[i];
 
            for (int i = n - 1; i >= 0; --i) t[i] = t[(i << 1)] ^ t[(i << 1) + 1];
        }
 
    	// Answers sum in range [l....r)
        public long query(int l , int r) {
            long ans = 0;
            if (l >= r) return ans;
            for (l += n , r += n; l < r; l /= 2 , r /= 2) {
                if ((l & 1) == 1) ans ^= t[l++];
                if ((r & 1) == 1) ans ^= t[--r];
            }
            return ans;
        }
 
        // Update a[pos] to val
        public void update(int pos , int val) {
        	for (t[pos += n] = val; pos > 1; pos /= 2) t[pos / 2] = t[pos] ^ t[pos ^ 1];
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
            int u = ni();
            int v = ni();
            sb.append(t.query(--u , v) + "\n");
        }
        pw.println(sb);
 
        pw.close();
        br.close();
    }
}