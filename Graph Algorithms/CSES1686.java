import java.util.*;
import java.io.*;
 
public class CSES1686 {
    static PrintWriter pw;
    static int n , m , cnt[] , scc[];
    static ArrayList<Integer> g[] , gT[] , topOrder;
    static boolean vis[];
 
    /*
        We can't straight away apply DP to the graph because
        given graph doesn't have valid topological sort (since not a DAG).

        So at first we need to split graph into its strongly connected
        components and then apply DP to condensed graph.

        Use Kosaraju's algorithm to get the condensed graph.

        On applying DP on condensed graph:
        
        Let DP[i] = Answer for subgraph of i
        DP[i] = Maximum value in subgraph of i (adjacency list of condensed graph)

        Finally answer is maximum value in DP[].
    */

    static void dfs1(int u) {
        vis[u] = true;
        for (Integer v : g[u]) {
            if (!vis[v]) dfs1(v);
        }
        topOrder.add(u);
    }

    static void dfs2(int u , int c) {
        vis[u] = true;
        scc[u] = c;
        for (Integer v : gT[u]) {
            if (!vis[v]) dfs2(v , c);
        }
    }

 
        public static void main(String args[])throws Exception {
            FastScanner f = new FastScanner(System.in);
            pw = new PrintWriter(System.out);
            
            n = f.ni();
            m = f.ni();
            g = new ArrayList[n];
            gT = new ArrayList[n];
            scc = new int[n];
            vis = new boolean[n];
            cnt = new int[n];
            topOrder = new ArrayList<>();

            for (int i = 0; i < n; ++i) {
                g[i] = new ArrayList<>();
                gT[i] = new ArrayList<>();
                cnt[i] = f.ni();
            }

            for (int i = 0; i < m; ++i) { 
                int u = f.ni();
                int v = f.ni();
                g[--u].add(--v);
                gT[v].add(u);
            }

            for (int i = 0; i < n; ++i) {
                if (!vis[i]) {
                    dfs1(i);
                }
            }

            Arrays.fill(vis , false);

            int c = 0;
            for (int i = n - 1; i >= 0; --i) {
                if (!vis[topOrder.get(i)]) {
                    dfs2(topOrder.get(i) , c);
                    ++c;
                } 
            }

            long wt[] = new long[c];

            for (int i = 0; i < n; ++i) wt[scc[i]] += cnt[i];

            ArrayList<Integer> adj_SCC[] = new ArrayList[c];

            for (int i = 0; i < c; ++i) adj_SCC[i] = new ArrayList<>();

            for (int i = 0; i < n; ++i) {
                for (Integer j : gT[i]) {
                    if (scc[j] < scc[i]) adj_SCC[scc[i]].add(scc[j]);
                }
            }

            long dp[] = new long[c];
            long ans = 0;

            for (int i = 0; i < c; ++i) {
                long max = 0l;
                for (Integer j : adj_SCC[i]) {
                    max = Math.max(max , dp[j]);
                }
                dp[i] = max + wt[i];
                ans = Math.max(ans , dp[i]);
            }

            pw.println(ans);

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
