import java.util.*;
import java.io.*;

public class CSES1683 {
    static PrintWriter pw;
    static int n , m , scc[];
    static ArrayList<Integer> adj[] , radj[];
    static ArrayList<Integer> order , component;
    static boolean vis[];

    /*
        Use Kosaraju's algorithm to find SCCs of the given directed graph
        Refer to page 158 of CP Handbook by Antti Laaksonen
    */

    static void dfs1(int u) {
        vis[u] = true;
        for (Integer v : adj[u]) {
            if (!vis[v]) dfs1(v);
        }
        order.add(u);
    }

    static void dfs2(int u , int idx) {
        vis[u] = true;
        scc[u] = idx;
        for (Integer v : radj[u]) {
            if (!vis[v]) dfs2(v , idx);
        }
    }

        public static void main(String args[])throws Exception {
            FastScanner f = new FastScanner(System.in);
            pw = new PrintWriter(System.out);
            
            n = f.ni();
            m = f.ni();
            adj = new ArrayList[n];
            radj = new ArrayList[n];
            order = new ArrayList<>();
            vis = new boolean[n];
            scc = new int[n];
            component = new ArrayList<>();

            for (int i = 0; i < n; ++i) {
                adj[i] = new ArrayList<>();
                radj[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; ++i) {
                int u = f.ni(); int v = f.ni();
                adj[--u].add(--v);
                radj[v].add(u);
            }

            for (int i = 0; i < n; ++i) {
                if (!vis[i]) {
                    dfs1(i);
                }
            }

            Arrays.fill(vis , false);
            Collections.reverse(order);
            int idx = 1;
            for (Integer u : order) {
                if (!vis[u]) {
                    dfs2(u , idx++);
                    component.clear();
                }
            }

            pw.println(--idx);
            for (int i = 0; i < n; ++i) pw.print(scc[i] + " ");


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