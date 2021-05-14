import java.util.*;
import java.io.*;

public class CSES1668 {
    static PrintWriter pw;
    static ArrayList<Integer> adj[];
    static boolean vis[];
    static int p[];
    static boolean ok;

    /*
        One constructive algorithm is to assign all node at even
        level with 1 and 0 otherwise and check if there is any
        discrepancy if so then answer is impossible

        Other observation here is , answer always exists if
        there is 'no' cycle of odd length.
        
        There are several ways of implementing this problem but easiest of
        the lot is 'Graph Coloring'

        Now problem can be rephrased to 'Check if given graph is 2 colourable' ?
        Graph is 'm colourable' when there exist a ways of assigning atmost
        m different colors to nodes of the graph such that no adjacent nodes
        have same colour.
    */

    static void dfs(int u , int c) { 
        vis[u] = true;
        p[u] = c;
        for (Integer v : adj[u]) {
            if (!vis[v]) dfs(v , 1 - c);
            else if (p[v] == p[u]) ok = false;
        }
    }

        public static void main(String args[])throws Exception {
            FastScanner f = new FastScanner(System.in);
            pw = new PrintWriter(System.out);
            
            int n = f.ni();
            int m = f.ni();
            adj = new ArrayList[n];
            vis = new boolean[n];
            p = new int[n];
            ok = true;

            for (int i = 0; i < n; ++i) {
                adj[i] = new ArrayList<>();
                p[i] = -1;
            }

            for (int i = 0; i < m; ++i) {
                int u = f.ni();
                int v = f.ni();
                adj[--u].add(--v);
                adj[v].add(u);
            }

            for (int i = 0; i < n; ++i) {
                if (!vis[i]) dfs(i , 0);
            }

            if (!ok) pw.println("IMPOSSIBLE");
            else {
                for (int i = 0; i < n; ++i) pw.print((p[i] + 1) + " ");
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