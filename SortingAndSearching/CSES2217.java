import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

/* The key observation here is that if we swap l and r then inversions of {l - 1 , l} , 
{l , l + 1} , {r - 1 , r} , {r , r + 1} are affected. 
Hence first we undo the inversions and then swap l & r and count inversions again */

public class CSES2217 {
    static PrintWriter pw;
 
    // Pair class in Java , overrides equals()
    static class Pair {
        int x;
        int y;
 
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
 
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Pair other = (Pair) obj;
            return this.x == other.x && this.y == other.y;
        }
 
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + x;
            result = prime * result + y;
            return result;
        }
    }
 
    public static void main(String[] args) throws IOException {
        pw = new PrintWriter(System.out);
        FastScanner f = new FastScanner();
        int n = f.ni();
        int m = f.ni();
        int[] a = new int[n + 1];
        int[] pos = new int[n + 1];
        int ans = 1;
        for (int i = 1; i <= n; ++i) {
            a[i] = f.ni();
            pos[a[i]] = i;
        }
        for (int i = 1; i < n; ++i)
            ans = pos[i] > pos[i + 1] ? ans + 1 : ans;
        HashSet<Pair> h = new HashSet<>();
        for (int i = 1; i <= m; ++i) {
            int u = f.ni();
            int v = f.ni();
            if (a[u] + 1 <= n)
                h.add(new Pair(a[u], a[u] + 1));
            if (a[u] - 1 >= 1)
                h.add(new Pair(a[u] - 1, a[u]));
            if (a[v] + 1 <= n)
                h.add(new Pair(a[v], a[v] + 1));
            if (a[v] - 1 >= 1)
                h.add(new Pair(a[v] - 1, a[v]));
            for (Pair p : h)
                ans = pos[p.x] > pos[p.y] ? ans - 1 : ans;
            int t = a[u];
            a[u] = a[v];
            a[v] = t;
            pos[a[u]] = u;
            pos[a[v]] = v;
            for (Pair p : h)
                ans = pos[p.x] > pos[p.y] ? ans + 1 : ans;
            pw.println(ans);
            h.clear();
        }
        pw.close();
    }
 
    static class FastScanner {
        private final int bs = 1 << 25;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;
 
        public FastScanner() {
            din = new DataInputStream(System.in);
            buffer = new byte[bs];
            bufferPointer = bytesRead = 0;
        }
 
        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, bs);
            if (bytesRead == -1)
                buffer[0] = -1;
        }
 
        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }
 
        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
 
        public int ni() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
 
            if (neg)
                return -ret;
            return ret;
        }
    }
}