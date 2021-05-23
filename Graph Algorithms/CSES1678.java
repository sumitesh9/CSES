import java.util.*;
import java.io.*;

public class CSES1678 {
    static PrintWriter pw;
    static int n , m , color[] , par[] , cycle_start , cycle_end;
    static ArrayList<Integer> adj[];

    /*
        This problem asks to print any cycle in given directed graph.
        In DFS entry color every unvisited vertex as grey and black on exit,
        if while exploring any white vertex , any grey vertex is encountered
        we are sure to have a cycle in the graph. 
    */

    static boolean dfs(int u) {
        color[u] = 1;
        for (Integer v : adj[u]) {
            if (color[v] == 0) {
                par[v] = u;
                if (dfs(v)) return true;
            }
            else if (color[v] == 1) {
                cycle_start = v;
                cycle_end = u;
                return true;
            }
        }
        color[u] = 2;
        return false;
    }

    public static void main(String args[])throws Exception {
        FastScanner f = new FastScanner(System.in);
        pw = new PrintWriter(System.out);
        n = f.ni();
        m = f.ni();
        adj = new ArrayList[n];
        cycle_start = -1;
        cycle_end = -1;
        par = new int[n];
        color = new int[n];

        for (int i = 0; i < n; ++i) adj[i] = new ArrayList<>();

            int self_Loop = -1;
            for (int i = 0; i < m; ++i) {
                int u = f.ni();
                int v = f.ni();
                adj[--u].add(--v);
                if (u == v) self_Loop = u;
            }

            if (self_Loop >= 0) {
                pw.println(1);
                pw.print(self_Loop + " " + self_Loop);
            }

            else {
                for (int i = 0; i < n; ++i) {
                    if (color[i] == 0 && dfs(i)) break;
                }

                if (cycle_start == -1) {
                    pw.println("IMPOSSIBLE");
                }

                else {
                    ArrayList<Integer> cycle = new ArrayList<>();
                    cycle.add(cycle_start);
                    for (int curr = cycle_end; curr != cycle_start; curr = par[curr]) {
                        cycle.add(curr);
                    }
                    cycle.add(cycle_start);
                    pw.println(cycle.size());
                    for (int i = cycle.size() - 1; i >= 0; --i) pw.print((cycle.get(i) + 1) + " ");
                }
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