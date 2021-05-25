import java.util.*;
import java.io.*;

public class CSES1676 {
    static PrintWriter pw;
    static int n , m;

    /*
       Use Disjoint set data structures
       and to answer the query , maintain two 
       variables -
       1) maxSize - Maximum size of current components
       2) cc - Number of connected components. 
    */

    static class DSU {
        private int id[] , sz[] , cc , maxSize;

        public DSU(int n) {
            cc = n;
            id = new int[n];
            sz = new int[n];
            maxSize = 1;
            for (int i = 0; i < n; ++i) {
                id[i] = i;
                sz[i] = 1;
            }
        }

        public int root(int a) {
            while (id[a] != a) {
                id[a] = id[id[a]];
                a = id[a];
            }
            return a;
        }

        public boolean unite(int a , int b) {
            int rA = root(a);
            int rB = root(b);
            if (rA == rB) return false;
            --cc;
            if (sz[rA] < sz[rB]) {
                id[rA] = id[rB];
                sz[rB] += sz[rA];
                if (sz[rB] > maxSize) maxSize = sz[rB];
            }

            else {
                id[rB] = id[rA];
                sz[rA] += sz[rB];
                if (sz[rA] > maxSize) maxSize = sz[rA];
            }
            return true;
        }
    }

        public static void main(String args[])throws Exception {
            FastScanner f = new FastScanner(System.in);
            pw = new PrintWriter(System.out);
            
            n = f.ni();
            m = f.ni();

            DSU dsu = new DSU(n);

            for (int i = 0; i < m; ++i) {
                int u = f.ni();
                int v = f.ni();
                dsu.unite(--u , --v);
                pw.println(dsu.cc + " " + dsu.maxSize);
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