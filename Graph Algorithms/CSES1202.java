import java.util.*;
import java.io.*;

public class CSES1202 {
    static PrintWriter pw;
    static int n , m , ways[] , min[] , max[] , mod;
    static ArrayList<Pair> adj[];
    static long d[] , oo;
    static TreeSet<Integer> q;

    /*
        This problem can be solved using Dijkstra's algorithm
        Let ways[i] = Number of ways of reaching i using shortest path
            min[i] = Minimum flights to reach i using minimum cost
            max[i] = Maximum flights to reach i using minimum cost
            d[i] = Minimum distance to reach i

        All of this can be evaluated in single Dijkstra

        If d[newCity] > d[currCity] + cost
            1) d[newCity] = d[currCity] + cost
            2) ways[newCity] = ways[currCity]
            3) min[newCity] = min[currCity] + 1;
            4) max[newCity] = max[currCity] + 1;

        If d[newCity] == d[currCity] + cost (Found another cheapest cost route)
            1) ways[newCity] += ways[currCity]
            2) min[newCity] = min(min[newCity] , min[currCity] + 1);
            3) max[newCity] = max(max[newCity] , max[currCity] + 1);

    */

    static class Pair {
        int node , wt;
        public Pair(int node , int wt) {
            this.node = node;
            this.wt = wt;
        }
    }

    static int get(int a , int b) {
        if (d[a] == d[b]) return a - b;
        if (d[a] < d[b]) return -1;
        return 1;
    }

    static void dijkstra() {
        for (int i = 0; i < n; ++i) {
            d[i] = oo;
        }
        d[0] = 0;
        min[0] = 0;
        max[0] = 0;
        ways[0] = 1;

        q.add(0);

        while (q.size() > 0) {
            int curr = q.pollFirst();
            for (Pair p : adj[curr]) {
                if (d[p.node] >= d[curr] + p.wt) {
                    if (d[p.node] != oo) q.remove(p.node);
                    if (d[p.node] == d[curr] + p.wt) {
                        ways[p.node] += ways[curr];
                        ways[p.node] %= mod;
                        min[p.node] = Math.min(min[p.node] , min[curr] + 1);
                        max[p.node] = Math.max(max[p.node] , max[curr] + 1);
                    }

                    else {
                        d[p.node] = d[curr] + p.wt;
                        ways[p.node] = ways[curr];
                        min[p.node] = min[curr] + 1;
                        max[p.node] = max[curr] + 1;
                    }
                    q.add(p.node);
                }
            }
        }
        pw.println(d[n - 1] + " " + ways[n - 1] + " " + min[n - 1] + " " + max[n - 1]);
    }

        public static void main(String args[])throws Exception {
            FastScanner f = new FastScanner(System.in);
            pw = new PrintWriter(System.out);
            
            n = f.ni();
            m = f.ni();

            ways = new int[n];
            d = new long[n];
            min = new int[n];
            max = new int[n];
            mod = 1000_000_007;
            oo = Long.MAX_VALUE / 3;
            adj = new ArrayList[n];
            q = new TreeSet<>((a , b) -> get(a , b));

            for (int i = 0; i < n; ++i) adj[i] = new ArrayList<>();
            for (int i = 0; i < m; ++i) {
                int u = f.ni(); int v = f.ni();
                int w = f.ni();
                adj[--u].add(new Pair(--v , w));

            }

            dijkstra();
            
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