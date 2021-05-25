import java.util.*;
import java.io.*;

public class CSES1681 {
    static PrintWriter pw;
    static int n , m , inD[] , dp[] , mod;
    static ArrayList<Integer> adj[];
    static Queue<Integer> q;

    /*
        Let DP[i] = Number of ways of reaching city i

        Base Case : 
        DP[0] = 1 (One way of reaching city number 1)

        Recursive Case:
        DP[i] = Sum of DP[u] for all u adjacent to i

        Graph is traversed by topological ordering so that for every edge u -> v
        v is explored after u , which ensures our DP states are correct.

        Also note that topological ordering always exists in this problem because
        there are no cycles.

        DP[n - 1] is our final answer.
    */

        public static void main(String args[])throws Exception {
            FastScanner f = new FastScanner(System.in);
            pw = new PrintWriter(System.out);
            n = f.ni();
            m = f.ni();
            adj = new ArrayList[n];
            inD = new int[n];
            dp = new int[n];
            mod = 1000_000_007;
            dp[0] = 1;
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

                dp[0] = 1;
                while (q.size() > 0) {
                    int u = q.poll();
                    for (Integer v : adj[u]) {
                        dp[v] += dp[u];
                        dp[v] %= mod;
                        if (--inD[v] == 0) q.add(v);
                    }
                }

                pw.println(dp[n - 1]);

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