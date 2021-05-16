import java.util.*;
import java.io.*;

public class CSES1197 {
    static PrintWriter pw;
    static int n , m;
    static int from[] , to[] , w[] , p[];
    static long d[];
    static long oo;

    /*
        Use Bellman - Ford algorithm:

        Do relaxation for n - 1 times and if for nth time relaxation occurs then we are
        sure that there is negative cycle , store last node and iterate in parents
        to find the required cycle.

        Edge case - 'Self loop with negative weight'

        Refer to page 123 of CP Handbook by Antti Laaksonen for detailed proof   
    */
            public static void main(String args[])throws Exception {
                FastScanner f = new FastScanner(System.in);
                pw = new PrintWriter(System.out);
                n = f.ni();
                m = f.ni();
                from =  new int[m];
                to = new int[m];
                w = new int[m];
                d = new long[n];
                p = new int[n];
                int y = -1;

                for (int i = 0; i < n; ++i) p[i] = -1;
                for (int i = 0; i < m; ++i) {
                    from[i] = f.ni() - 1;
                    to[i] = f.ni() - 1;
                    w[i] = f.ni();
                    if (from[i] == to[i] && w[i] < 0) {
                        y = from[i];
                    }
                }

                if (y != -1) {
                    pw.println("YES");
                    pw.print((y + 1) + " " + (y + 1));
                }

                boolean relaxed = false;
                int last = -1;
                for (int i = 0; i < n; ++i) {
                    relaxed = false;
                    last = -1;
                    for (int j = 0; j < m; ++j) {
                        if (d[to[j]] > d[from[j]] + w[j]) {
                            d[to[j]] = d[from[j]] + w[j];
                            p[to[j]] = from[j];
                            relaxed = true;
                            last = to[j];
                        }
                    }
                }

                if (last == -1) {
                    pw.println("NO");
                }

                else {
                    for (int i = 0; i < n; ++i) last = p[last];

                    ArrayList<Integer> cycle = new ArrayList<>();
             
                    for (int node = last; ; node = p[node]) {
                        cycle.add(node);
                        if (node == last && cycle.size() > 1) break;
                    }
             
                    pw.println("YES");
                    for (int i = cycle.size() - 1; i >= 0; --i) pw.print((cycle.get(i) + 1) + " ");
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