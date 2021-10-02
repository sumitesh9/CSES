import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/* This is classic Lazy Propogation problem , maintain two lazy arrays for increment and
assignment updates and solve using Segment tree. */

public class CSES1735 {
    static FastScanner f;
    static PrintWriter pw;
    static int n;
    static int[] a;
    static int sz;
    static long[] tr;
    static long[] lazyInc;
    static long[] lazySet;

    static void build(int k, int l, int r) {
        if (r - l == 1) {
            tr[k] = a[l];
            return;
        }
        int mid = (l + r) / 2;
        int k1 = k * 2 + 1;
        int k2 = k * 2 + 2;
        build(k1, l, mid);
        build(k2, mid, r);
        tr[k] = tr[k1] + tr[k2];
    }

    static void push(int k, int l, int r) {
        int m = (l + r) / 2;
        int k1 = k * 2 + 1;
        int k2 = k * 2 + 2;
        if (lazySet[k] != 0) {
            update(k1, l, m, l, m, 2, lazySet[k]);
            update(k2, m, r, m, r, 2, lazySet[k]);
            lazySet[k] = 0;
        } else if (lazyInc[k] != 0) {
            update(k1, l, m, l, m, 1, lazyInc[k]);
            update(k2, m, r, m, r, 1, lazyInc[k]);
            lazyInc[k] = 0;
        }
    }

    static void update(int k, int l, int r, int ql, int qr, int t, long x) {
        if (qr <= l || r <= ql)
            return;
        if (ql <= l && r <= qr) {
            if (t == 1) {
                tr[k] += (r - l) * x;
                if (lazySet[k] == 0)
                    lazyInc[k] += x;
                else
                    lazySet[k] += x;
            } else {
                tr[k] = (r - l) * x;
                lazySet[k] = x;
                lazyInc[k] = 0;
            }
            return;
        }
        push(k, l, r);
        int mid = (l + r) / 2;
        int k1 = k * 2 + 1;
        int k2 = k * 2 + 2;
        update(k1, l, mid, ql, qr, t, x);
        update(k2, mid, r, ql, qr, t, x);
        tr[k] = tr[k1] + tr[k2];
    }

    static long query(int k, int l, int r, int ql, int qr) {
        if (qr <= l || r <= ql)
            return 0;
        if (ql <= l && r <= qr)
            return tr[k];
        push(k, l, r);
        int mid = (l + r) / 2;
        int k1 = k * 2 + 1;
        int k2 = k * 2 + 2;
        return query(k1, l, mid, ql, qr) + query(k2, mid, r, ql, qr);
    }

    public static void main(String[] args) throws Exception {
        f = new FastScanner(System.in);
        pw = new PrintWriter(System.out);
        n = f.ni();
        int q = f.ni();
        a = new int[n];
        for (int i = 0; i < n; ++i)
            a[i] = f.ni();
        sz = 1;
        while (sz < n)
            sz *= 2;
        tr = new long[sz * 2];
        lazyInc = new long[sz * 2];
        lazySet = new long[sz * 2];
        build(0, 0, n);
        while (q-- > 0) {
            int t = f.ni();
            int l = f.ni() - 1;
            int r = f.ni();
            if (t == 3)
                pn(query(0, 0, n, l, r));
            else {
                int x = f.ni();
                update(0, 0, n, l, r, t, x);
            }
        }
        pw.close();
    }

    public static class FastScanner {
        InputStream is;

        FastScanner(InputStream is) {
            this.is = is;
        }

        byte[] bb = new byte[1 << 15];
        int k;
        int l;

        byte getc() throws IOException {
            if (k >= l) {
                k = 0;
                l = is.read(bb);
                if (l < 0)
                    return -1;
            }
            return bb[k++];
        }

        byte skip() throws IOException {
            byte b;
            while ((b = getc()) <= 32)
                ;
            return b;
        }

        int ni() throws IOException {
            int n = 0;
            for (byte b = skip(); b > 32; b = getc())
                n = n * 10 + b - '0';
            return n;
        }
    }

    public static void pn(Object... o) {
        for (int i = 0; i < o.length; ++i)
            pw.print(o[i] + (i + 1 < o.length ? " " : "\n"));
    }

    public static void p(Object... o) {
        for (int i = 0; i < o.length; ++i)
            pw.print(o[i] + (i + 1 < o.length ? " " : ""));
    }
}