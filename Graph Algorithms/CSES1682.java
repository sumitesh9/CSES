import java.util.*;
import java.io.*;

public class CSES1682 {
    static PrintWriter pw;
    static int n , m;
    static ArrayList<Integer> adj[] , radj[];
    static ArrayList<Integer> order , component;
    static boolean vis[];

    /*
        Use Kosaraju's algorithm to find SCC of the given directed graph
        and print any pair of vertices that are not part of same SCC.
    */

    static void dfs1(int u) {
        vis[u] = true;
        order.add(u);
        for (Integer v : adj[u]) {
            if (!vis[v]) dfs1(v);
        }
    }

    static void dfs2(int u) {
        vis[u] = true;
        component.add(u);
        for (Integer v : radj[u]) {
            if (!vis[v]) dfs2(v);
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

            dfs1(0);

            boolean ok = true;
            for (int i = 0; i < n; ++i) {
                if (!vis[i]) {
                    pw.println("NO");
                    ok = false;
                    pw.print(1 + " " + (i + 1));
                }
            }
            
            if (ok) {
                Arrays.fill(vis , false);
                dfs2(0);
                for (int i = 0; i < n; ++i) {
                    if (!vis[i]) {
                        pw.println("NO");
                        pw.println((i + 1) + " 1");
                        ok = false;
                    }
                }
            }

            if (ok) pw.print("YES");
            
            
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