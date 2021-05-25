import java.util.*;
import java.io.*;

public class CSES1750 {
    static PrintWriter pw;
    static int n , q;
    static int par[][];

    /*
        Let par[i][j] denote (1 << j)th ancestor of i (This technique is binary lifting)
        Now lets' say we want to find kth ancestor of i

        Assume k = 45 (without any loss of generality)
        45 = 32 + 8 + 4 + 1
        so we need to lift only 4 times to calculate our answer

        Time complexity - (Maximum number of set bits) = 32 iterations atmost per query

    */
        public static void main(String args[])throws Exception {
            FastScanner f = new FastScanner(System.in);
            pw = new PrintWriter(System.out);
            
            n = f.ni();
            q = f.ni();

            int k = 30;
            par = new int[n][k];

            for (int i = 0; i < n; ++i) {
                int u = f.ni();
                par[i][0] = --u;
            }

            for (int j = 1; j < k; ++j) {
                for (int i = 0; i < n; ++i) {
                    par[i][j] = par[par[i][j - 1]][j - 1];
                }
            }

            for (int i = 0; i < q; ++i) {
                int u = f.ni() - 1; int v = f.ni();
                for (int j = k - 1; j >= 0; --j) {
                    if (((1 << j) & v) > 0) {
                        v -= (1 << j);
                        u = par[u][j];
                    }
                }
                pw.println(++u);
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