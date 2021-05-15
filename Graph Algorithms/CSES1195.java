import java.util.*;
import java.io.*;

public class CSES1195 {
    static PrintWriter pw;
    static int n , m;
    static long d1[] , dN[] , oo;
    static ArrayList<Edge> in[] , out[];

    /*
        There exists some DP solution where
        DP[i][0] = Minimum cost to i without discount
        DP[i][1] = Minimum cost to i with discount

        Alternative solution -
        
        Let d(x , y) denote cost of travelling from x to y

        Lets say we want to take discount between A -> B then
        our answer will be d(1 , A) + (cost(A , B) / 2) + d(N , B)

        Hence if we find distance of every city from 1 and distance
        of every city from N, then we can iterate on intermediate city
        and apply discount there and take minimum out of all possible 
        intermediate cities.
    */

        static class Edge {
            int node , wt;

            public Edge(int node , int wt) {
                this.node = node;
                this.wt = wt;
            }
        }

        static int cmp1(int i , int j) {
            if (d1[i] == d1[j]) return i - j;
            if (d1[i] < d1[j]) return -1;
            return 1;
        }

        static int cmpN(int i , int j) {
            if (dN[i] == dN[j]) return i - j;
            if (dN[i] < dN[j]) return -1;
            return 1;
        }

        static void dijkstraStart(int u) {
            for (int i = 0; i < n; ++i) d1[i] = oo;
            d1[u] = 0;
            TreeSet<Integer> q = new TreeSet<>((i , j) -> cmp1(i , j));
            q.add(u);

            while (q.size() > 0) {
                int curr = q.pollFirst();
                for (Edge e : out[curr]) {
                    if (d1[e.node] > d1[curr] + e.wt) {
                        if (d1[e.node] != oo) q.remove(e.node);
                        d1[e.node] = d1[curr] + e.wt;
                        q.add(e.node);
                    }
                }
            }
        }

        static void dijkstraEnd(int u) {
            for (int i = 0; i < n; ++i) dN[i] = oo;
            dN[u] = 0;
            TreeSet<Integer> q = new TreeSet<>((i , j) -> cmpN(i , j));
            q.add(u);

            while (q.size() > 0) {
                int curr = q.pollFirst();
                for (Edge e : in[curr]) {
                    if (dN[e.node] > dN[curr] + e.wt) {
                        if (dN[e.node] != oo) q.remove(e.node);
                        dN[e.node] = dN[curr] + e.wt;
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
                in = new ArrayList[n];
                out = new ArrayList[n];
                d1 = new long[n];
                dN = new long[n];

                for (int i = 0; i < n; ++i) {
                    in[i] = new ArrayList<>();
                    out[i] = new ArrayList<>();
                }

                for (int i = 0; i < m; ++i) {
                    int u = f.ni();
                    int v = f.ni();
                    int w = f.ni();
                    out[--u].add(new Edge(--v , w));
                    in[v].add(new Edge(u , w));
                }

                dijkstraStart(0);
                dijkstraEnd(n - 1);

                long ans = oo;
                for (int i = 0; i < n; ++i) {
                    for (Edge e : out[i]) {
                        ans = Math.min(ans , d1[i] + dN[e.node] + e.wt / 2);
                    }
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