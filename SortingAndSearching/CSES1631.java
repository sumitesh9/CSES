package SortingAndSearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/* Sort the array and greedily consume the books from both ends and only case either
guy waits occurs when last element is strictly greater than sum of all other elements,
add that overhead to answer else answer is sum of books */

public class CSES1631 {
    static StringTokenizer st;
    static BufferedReader br;
    static PrintWriter pw;
    static int n;

    static int ni() {
        return Integer.parseInt(st.nextToken());
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        pw = new PrintWriter(System.out);
        n = ni();
        st = new StringTokenizer(br.readLine());
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < n; ++i)
            a.add(ni());
        Collections.sort(a);
        long[] p = new long[n];
        long[] s = new long[n];
        for (int i = 0; i < n; ++i)
            p[i] = i > 0 ? p[i - 1] + a.get(i) : a.get(i);
        for (int i = n - 1; i >= 0; --i)
            s[i] = i + 1 == n ? a.get(i) : s[i + 1] + a.get(i);
        if (n == 1) {
            pw.println(p[0] * 2);
        } else {
            long ans = p[n - 1];
            if (s[n - 1] > p[n - 2])
                ans += s[n - 1] - p[n - 2];
            pw.println(ans);
        }
        pw.close();
        br.close();
    }
}
