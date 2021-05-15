import java.util.*;
import java.io.*;

public class CSES1672 {
    static PrintWriter pw;
    static int n , m , q;
    static long d[][] , oo;
    static ArrayList<Edge> adj[];

    /*
        Use Floyd Warshall's Algorithm to compute all pair's shortest path
        Refer to page 129 of CP Handbook by Antti Laaksonen
    */

        static class Edge {
            int node , wt;

            public Edge(int node , int wt) {
                this.node = node;
                this.wt = wt;
            }
        }

            static void floydWarshall() {
                for (int i = 0; i < n; ++i) d[i][i] = 0;

                    for (int k = 0; k < n; ++k) {
                        for (int i = 0; i < n; ++i) {
                            for (int j = 0; j < n; ++j) {
                                d[i][j] = Math.min(d[i][j] , d[i][k] + d[k][j]);
                            }
                        }
                    }
                }

            public static void main(String args[])throws Exception {
                FastScanner f = new FastScanner(System.in);
                pw = new PrintWriter(System.out);
                n = f.ni();
                m = f.ni();
                q = f.ni();
                oo = Long.MAX_VALUE / 5;
                adj = new ArrayList[n];
                d = new long[n][n];

                for (int i = 0; i < n; ++i) {
                    adj[i] = new ArrayList<>();
                    Arrays.fill(d[i] , oo);
                }

                for (int i = 0; i < m; ++i) {
                    int u = f.ni();
                    int v = f.ni();
                    int w = f.ni();
                    adj[--u].add(new Edge(--v , w));
                    adj[v].add(new Edge(u , w));
                    d[u][v] = Math.min(d[u][v] , w);
                    d[v][u] = Math.min(d[v][u] , w);
                }

                floydWarshall();

                for (int i = 0; i < q; ++i) {
                    int u = f.ni();
                    int v = f.ni();
                    pw.println(d[--u][--v] == oo ? -1 : d[u][v]);
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