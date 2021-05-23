import java.util.*;
import java.io.*;

public class CSES1679 {
    static PrintWriter pw;
    static int n , m , inD[];
    static ArrayList<Integer> adj[];

    /*
        We can schedule the courses in accordance to Topological Ordering of
        these courses. If no valid topological ordering exists then we can't
        schedule the courses as per requirements.

        Using Kahn's Algorithm -
        1) Enqueue all nodes in queue with 0 indegree
        2) Dequeue current node and explore its neighbour while removing
           edges and enequeue all nodes whose indegree becomes 0.
        3) If any node is unvisited then topological ordering doesn't exist
        4) Otherwise print the answer.
    */

    public static void main(String args[])throws Exception {
        FastScanner f = new FastScanner(System.in);
        pw = new PrintWriter(System.out);
        n = f.ni();
        m = f.ni();
        adj = new ArrayList[n];
        inD = new int[n];
        

        for (int i = 0; i < n; ++i) adj[i] = new ArrayList<>();

            for (int i = 0; i < m; ++i) {
                int u = f.ni();
                int v = f.ni();
                adj[--u].add(--v);
                ++inD[v];
            }
            
            int q[] = new int[n];
            int head = 0 , cnt = 0;

            for (int i = 0; i < n; ++i) {
                if (inD[i] == 0) q[cnt++] = i;
            }

            while (cnt > 0) {
                int u = q[head++];
                --cnt;
                for (Integer v : adj[u]) {
                    --inD[v];
                    if (inD[v] == 0) q[head + cnt++] = v;
                }
            }

            if (head == n) {
                for (int i = 0; i < n; ++i) pw.print((q[i] + 1) + " ");
            }
            else pw.println("IMPOSSIBLE");

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