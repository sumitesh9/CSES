import java.util.*;
import java.io.*;
public class CSES1143 {
    static PrintWriter pw;

    static int n;

    /*
        We can use RMQ here on the input array

        Using binary search on segment tree we can find first index i in range [0...n)
        such that a[i] >= r[i]

        If such i exists we need to use update query and decrement a[i] by r[i]
        else answer is 0 
    */

    static class RMQ {
        private int t[];
    
        public RMQ(int a[]) {
            t = new int[2 * a.length];
            for (int i = 0; i < n; ++i) t[n + i] = a[i];

            for (int i = n - 1; i >= 0; --i) t[i] = Math.max(t[(i << 1)] , t[(i << 1) + 1]);
        }

        public void update(int pos , int val) {
            for (t[pos += n] = val; pos > 1; pos /= 2) t[pos / 2] = Math.max(t[pos] , t[pos ^ 1]);
        }

        public int query(int l , int r) {
            int ans = Integer.MIN_VALUE;
            if (l >= r) return ans;
            for (l += n , r += n; l < r; l /= 2 , r /= 2) {
                if ((l & 1) == 1) ans = Math.max(ans , t[l++]);
                if ((r & 1) == 1) ans = Math.max(ans , t[--r]);
            }
            return ans;
        }
    }

    public static void main(String args[])throws Exception {
        FastScanner f = new FastScanner(System.in);
        pw = new PrintWriter(System.out);

        n = f.ni(); int m = f.ni();

        int a[] = new int[n];
        for (int i = 0; i < n; ++i) a[i] = f.ni();

        RMQ t = new RMQ(a);
        for (int i = 0; i < m; ++i) {
            int lo = 0 , hi = n - 1;
            int currAns = -1;
            int req = f.ni();
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                int max = t.query(0 , mid + 1);
                if (max >= req) {
                    hi = mid - 1;
                    currAns = mid;
                }
                else lo = mid + 1;
            }
            ++currAns;
            if (currAns == 0) {
                pw.print(currAns + " ");
                continue;
            }
            pw.print((currAns) + " ");
            a[--currAns] -= req;
            t.update(currAns , a[currAns]);
        }
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
                if (c < '0' || c > '9') throw new InputMismatchException();
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
                if (c < '0' || c > '9') throw new InputMismatchException();
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