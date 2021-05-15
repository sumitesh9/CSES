import java.util.*;
import java.io.*;

public class CSES1671 {
    static PrintWriter pw;
    static int n , m;
    static long d[] , oo;
    static ArrayList<Edge> adj[];

    /*
        Use Dijkstra's Algoritm to find length of shortest route
        to every city.

        **Use TreeSet with custom comparator over PriorityQueue
        if implementing in Java to avoid TLE**
    */

        static class Edge {
            int node , wt;

            public Edge(int node , int wt) {
                this.node = node;
                this.wt = wt;
            }
        }

        static int cmp(int i , int j) {
            if (d[i] == d[j]) return i - j;
            if (d[i] < d[j]) return -1;
            return 1;
        }

        static void dijsktra(int u) {
            d[u] = 0;
            TreeSet<Integer> q = new TreeSet<>((i , j) -> cmp(i , j));
            q.add(u);

            while (q.size() > 0) { 
                int curr = q.pollFirst();
                for (Edge e : adj[curr]) {
                    if (d[e.node] > d[curr] + e.wt) {
                        if (d[e.node] != oo) q.remove(e.node);
                        d[e.node] = d[curr] + e.wt;
                        q.add(e.node);
                    }
                }
            }
        }

        public static void main(String args[])throws Exception {
            FastScanner f = new FastScanner(System.in);
            pw = new PrintWriter(System.out);
            n = f.ni();
            m = f.ni();
            oo = Long.MAX_VALUE / 5;
            adj = new ArrayList[n];
            d = new long[n];

            for (int i = 0; i < n; ++i) {
                adj[i] = new ArrayList<>();
                d[i] = oo;
            }

            for (int i = 0; i < m; ++i) {
                int u = f.ni();
                int v = f.ni();
                int w = f.ni();
                adj[--u].add(new Edge(--v , w));
            }

            dijsktra(0);

            for (int i = 0; i < n; ++i) pw.print(d[i] + " ");


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