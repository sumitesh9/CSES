import java.util.*;
import java.io.*;

public class CSES1669 {
    static PrintWriter pw;
    static ArrayList<Integer> adj[];
    static boolean vis[];
    static boolean ok;
    static int par[];

    /*
        Run a DFS to detect and print any cycle in the graph
        or print impossible if there is no cycle.
    */

        static void dfs(int u , int p) { 
            vis[u] = true;
            if (ok) return;
            for (Integer v : adj[u]) {
                if (!vis[v]) {
                    par[v] = u;
                    dfs(v , u);
                }
                else {
                    if (v != p) {
                        if (ok) return;
                        ok = true;
                        int curr = u;
                        ArrayList<Integer> cycle = new ArrayList<>();
                        while (curr != v) {
                            cycle.add(curr);
                            curr = par[curr];
                        }
                        cycle.add(v);
                        cycle.add(u);
                        pw.println(cycle.size());
                        for (Integer j : cycle) pw.print((j + 1) + " ");
                            return;
                    } 
                }
            }
        }

        public static void main(String args[])throws Exception {
            FastScanner f = new FastScanner(System.in);
            pw = new PrintWriter(System.out);
            
            int n = f.ni();
            int m = f.ni();
            adj = new ArrayList[n];
            vis = new boolean[n];
            par = new int[n];
            ok = false;

            for (int i = 0; i < n; ++i) {
                adj[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; ++i) {
                int u = f.ni();
                int v = f.ni();
                adj[--u].add(--v);
                adj[v].add(u);
            }

            for (int i = 0; i < n && !ok; ++i) {
                if (!vis[i]) dfs(i , -1);
            }

            if (!ok) pw.println("IMPOSSIBLE");
            
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