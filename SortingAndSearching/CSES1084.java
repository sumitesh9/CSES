package SortingAndSearching;

import java.util.*;
import java.io.*;
 
public class CSES1084 {
 
    static BufferedReader br;
    static PrintWriter pw;
    static StringTokenizer st;
 
    static int ni() {
        return Integer.parseInt(st.nextToken());
    }

    /*
        First sort both the arrays required and available and then assign
        greedily the first valid apartment that matches requirement by scanning
        from left to right.

        Proof of correctness
        For every ith requirement we are assigning minimum size (first valid solution) that matches
        it and hence we are not wasting any apartment.
    */

    // Immune to TLE
    static void sort(int a[]) {
        ArrayList<Integer> l = new ArrayList<>();
        for (int x : a) l.add(x);
        Collections.sort(l);
        for (int i = 0; i < a.length; ++i) a[i] = l.get(i);
    }
 
    public static void main(String args[]) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(System.out);
        st = new StringTokenizer(br.readLine());

        int n = ni();
        int m = ni();
        int k = ni();

        int req[] = new int[n];
        int sz[] = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) req[i] = ni();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; ++i) sz[i] = ni();

        sort(req);
        sort(sz);

        int ptr = 0 , ans = 0;
        for (int i = 0; i < n && ptr < m; ++i) {
            for (; ptr < m; ++ptr) {
                if (sz[ptr] > req[i] + k) break;
                if (sz[ptr] <= req[i] + k && sz[ptr] >= req[i] - k) {
                    ++ans;
                    ++ptr;
                    break;
                }
            }
        }

        pw.println(ans);

        pw.close();
        br.close();
    }   
}