import java.util.*;
import java.io.*;

public class CSES1694 {
    static PrintWriter pw;
    static int n , m;
    static int par[];
    static long g[][] , oo;

    /*
        Implement Dinic's Algorithm to find maximum flow in the given graph
        References - 1) https://cp-algorithms.com/graph/dinic.html
                     2) CP Handbook - Page 181
    */

    static boolean bfs() {
        boolean vis[] = new boolean[n];
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(0);
        vis[0] = true;

        while(q.size() > 0) {
            int u = q.remove();
            for (int v = 0; v < n; ++v) {
                if (v != u && !vis[v] && g[u][v] != 0) {
                    vis[v] = true;
                    par[v] = u;
                    q.add(v);
                }
            }
        }
        return vis[n - 1];
    }

    public static void main(String args[])throws Exception {
        FastScanner f = new FastScanner(System.in);
        pw = new PrintWriter(System.out);

        n = f.ni();
        m = f.ni();
        oo = Long.MAX_VALUE / 5;

        g = new long[n][n];
        par = new int[n];

        for (int i = 0; i < m; ++i) {
            int u = f.ni();
            int v = f.ni();
            int c = f.ni();
            g[--u][--v] += c; 
        }

        int u = 0 , v = 0;
        long maxFlow = 0;

        while(bfs()) {
            long flow = oo;
            for (v = n - 1; v > 0; v = par[v]) {
                u = par[v];
                flow = Math.min(flow, g[u][v]);
            }
            maxFlow += flow;
            for (v = n - 1; v > 0; v = par[v]) {
                u = par[v];
                g[u][v] -= flow;
                g[v][u] += flow;
            }
        }

        pw.print(maxFlow);
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
