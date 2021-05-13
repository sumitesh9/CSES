import java.util.*;
import java.io.*;

public class CSES1667 {
    static PrintWriter pw;
    static ArrayList<Integer> adj[];
    static boolean vis[];
    static int p[] , d[] , oo;

    /*
        We can find shortest path using BFS or Dijkstra here.
        Since graph is unweighted, BFS is easier to implement.
    */

        static void bfs() {
            ArrayDeque<Integer> q = new ArrayDeque<>();
            q.add(0);
            vis[0] = true;
            d[0] = 0;

            while (q.size() > 0) {
                int u = q.poll();
                vis[u] = true;
                for (Integer v : adj[u]) {
                    if (!vis[v]) {
                        vis[v] = true;
                        q.add(v);
                        d[v] = Math.min(d[v] , d[u] + 1);
                        p[v] = u;
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
            p = new int[n];
            d = new int[n];
            oo = 1000_000_00;

            for (int i = 0; i < n; ++i) {
                adj[i] = new ArrayList<>();
                d[i] = oo;
            }

            for (int i = 0; i < m; ++i) {
                int u = f.ni();
                int v = f.ni();
                adj[--u].add(--v);
                adj[v].add(u);
            }

            bfs();

            if (!vis[n - 1]) pw.println("IMPOSSIBLE");
            
            else {
                pw.println(d[n - 1] + 1);
                int curr = n - 1;
                ArrayList<Integer> path = new ArrayList<>();
                path.add(curr + 1);

                while (curr != 0) {
                    curr = p[curr];
                    path.add(curr + 1);
                }

                for (int i = path.size() - 1; i >= 0; --i) pw.print(path.get(i) + " ");
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