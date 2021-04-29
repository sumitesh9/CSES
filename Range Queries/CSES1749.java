import java.util.*;
import java.io.*;

public class CSES1749 {
    static PrintWriter pw;

    static int a[] , p[] , n;

    /*
        Naive solutions straight away leads to TLE

        Optimised solution (Using segment tree) :

        Consider array p[] where p[i] = 1 if ith index is not yet deleted otherwise 0
        Initially p[i] = 1 for all valid i

        Lets say we want to delete the ith index of original array , we need to find the
        new index corresponding to ith index because of the shifts in the array caused by
        deletions

        Lets assume j correspond to correct ith index
        So it implies that sum of p[k] for all k in [0...j] will be i and p[j] = 1

        Proof of correctness:
        1) p[j] = 1 (This is because we can't delete a element if it is already deleted)
        2) Sum of p[k] for all k in [0...j] will be i (This needs to hold true because if j is
        ith index after previous deletion queries than there must be exactly i times 1 in range
        [0....j] and exactly (j - i) times 0

        So all we need to do is binary search in order to find correct j using Range Sum query
        on p[]

        After every query p[j] is set to 0 (jth element is deleted)
        Time complexity - O(nlog(n)log(n))
    */ 

    static class RMQ {
        private int t[];

        public RMQ(int a[]) {
            t = new int[2 * a.length];
            for (int i = 0; i < n; ++i) t[n + i] = a[i];

            for (int i = n - 1; i >= 0; --i) t[i] = t[(i << 1)] + t[(i << 1) + 1];
        }

        public int query(int l , int r) {
            int ans = 0;
            for (l += n , r += n; l < r; l /= 2 , r /= 2) {
                if ((l & 1) == 1) ans += t[l++];
                if ((r & 1) == 1) ans += t[--r];
            }
            return ans;
        }

        public void update(int pos , int val) {
            for (t[pos += n] = val; pos > 1; pos /= 2) t[pos / 2] = t[pos] + t[pos ^ 1];
        }
    }

    public static void main(String args[])throws Exception {
       FastScanner f = new FastScanner(System.in);
        pw = new PrintWriter(System.out);

        n = f.ni();
        a = new int[n];
        p = new int[n];

        

        for (int i = 0; i < n; ++i) {
            a[i] = f.ni();
            p[i] = 1;
        }

        RMQ t = new RMQ(p);
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < n; ++i) {
            int pos = f.ni();
            int lo = 0 , hi = n - 1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                int cnt = t.query(0 , mid + 1);
                if (cnt < pos) lo = mid + 1;
                else if (cnt > pos) hi = mid - 1;
                else {
                    if (p[mid] == 1) {
                        lo = mid;
                        break;
                    }
                    else hi = mid - 1;
                }
            }
            p[lo] = 0;
            t.update(lo , 0);
            sb.append(a[lo] + " ");
        }

        pw.print(sb);

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