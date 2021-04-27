import java.util.*;
import java.io.*;
public class CSES1649 {
    static PrintWriter pw;

    static int n , q , a[];

    /*  
        Range Min queries can be efficiently solved using Segment Tree or 
        Square Root Decomposition

        RMQ using Segment Tree - Time complexity - O(nlogq)
                                 Space complexity - O(2 * n)

        Below is minimal implementation of Segment tree in Java
        Reference - https://www.geeksforgeeks.org/segment-tree-efficient-implementation/
        			https://codeforces.com/blog/entry/18051
    */

    static class RMQ {
        private int t[];

        public RMQ(int a[]) {
            t = new int[2 * a.length];
            for (int i = 0; i < n; ++i) t[n + i] = a[i];

            for (int i = n - 1; i >= 0; --i) t[i] = Math.min(t[(i << 1)] , t[(i << 1) + 1]);
        }

    	// Answers minimum in range [l....r)
        public int query(int l , int r) {
            int ans = Integer.MAX_VALUE;
            if (l >= r) return ans;
            for (l += n , r += n; l < r; l /= 2 , r /= 2) {
                if ((l & 1) == 1) ans = Math.min(ans , t[l++]);
                if ((r & 1) == 1) ans = Math.min(ans , t[--r]);
            }
            return ans;
        }

        // Update a[pos] to val
        public void update(int pos , int val) {
        	for (t[pos += n] = val; pos > 1; pos /= 2) t[pos / 2] = Math.min(t[pos] , t[pos ^ 1]);
        }
    }

    public static void main(String args[])throws Exception {
        FastScanner f = new FastScanner(System.in);
        pw = new PrintWriter(System.out);

        n = f.ni(); q = f.ni();
        
        a = new int[n];

        for (int i = 0; i < n; ++i) a[i] = f.ni();

        RMQ t = new RMQ(a);

        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < q; ++i) {
            int type = f.ni();
            int u = f.ni();
            int v = f.ni();

            if (type == 1) t.update(u - 1 , v);

            else sb.append(t.query(--u , v) + "\n");
        }
        pw.println(sb);

        pw.close();
    }


    public static class FastScanner {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private FastScanner.SpaceCharFilter filter;
 
        public FastScanner(InputStream stream) {
            this.stream = stream;
        }
 
        public int read() {
            if (numChars == -1)
                throw new InputMismatchException();
 
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
 
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }
 
        public int ni() {
            int c = read();
 
            while (isSpaceChar(c))
                c = read();
 
            int sgn = 1;
 
            if (c == '-') {
                sgn = -1;
                c = read();
            }
 
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            while (!isSpaceChar(c));
 
            return res * sgn;
        }
 
        public long nl() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
 
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            while (!isSpaceChar(c));
            return res * sgn;
        }
 
        public String next() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            }
            while (!isSpaceChar(c));
 
            return res.toString();
        }
 
        public boolean isSpaceChar(int c) {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
 
        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);
 
        }
    }
}