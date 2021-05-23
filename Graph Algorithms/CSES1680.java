import java.util.*;
import java.io.*;

public class CSES1680 {
    static PrintWriter pw;
    static int n , m , inD[] , dp[] , par[];
    static ArrayList<Integer> adj[];
    static Queue<Integer> q;

    /*
        Let DP[i] = Most flights to reach node i

        Base Case : DP[0] = 1 (Starting city)
        Recursive Case : DP[i] = max(DP[i] , 1 + DP[j]) (for all j such that i is connected by outgoing edge)

        Graph is traversed by topological ordering so that for every edge u -> v
        v is explored after u , which ensures our DP states are correct.

        Also note that topological ordering always exists in this problem because
        there are no cycles.

        Answer is impossible when destination and sources node are not connected.
    */

        public static void main(String args[])throws Exception {
            FastScanner f = new FastScanner(System.in);
            pw = new PrintWriter(System.out);
            n = f.ni();
            m = f.ni();
            adj = new ArrayList[n];
            inD = new int[n];
            par = new int[n];
            dp = new int[n];
            dp[0] = 1;
            par[0] = 0;
            q = new ArrayDeque<>();

            for (int i = 0; i < n; ++i) adj[i] = new ArrayList<>();

                for (int i = 0; i < m; ++i) {
                    int u = f.ni();
                    int v = f.ni();
                    adj[--u].add(--v);
                    ++inD[v];
                }

                for (int i = 0; i < n; ++i) {
                    if (inD[i] == 0) q.add(i);
                }

                while (q.size() > 0) {
                    int u = q.poll();
                    for (Integer v : adj[u]) {
                        if (dp[u] != 0 && dp[v] < dp[u] + 1) {
                            dp[v] = dp[u] + 1;
                            par[v] = u;
                        }
                        if (--inD[v] == 0) q.add(v);
                    }
                }

                pw.println(dp[n - 1] == 0 ? "IMPOSSIBLE" : dp[n - 1]);

                if (dp[n - 1] != 0) {
                    ArrayList<Integer> path = new ArrayList<>();

                    path.add(n);

                    int curr = n - 1;

                    while (par[curr] != 0) {
                        curr = par[curr];
                        path.add((curr + 1));
                    }

                    path.add(1);
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