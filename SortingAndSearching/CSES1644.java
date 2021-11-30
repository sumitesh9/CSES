package SortingAndSearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

/*
    Create a prefix sum array , now it is easy to see that for position i :
    We want to find j < i and (j - i + 1) lies in [a , b] such that prefix[i] - prefix[j] is max.
    This can be done by implementing sliding window of size b
*/

public class CSES1644 {
    static StringTokenizer st;
    static BufferedReader br;
    static PrintWriter pw;

    static int ni() {
        return Integer.parseInt(st.nextToken());
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        pw = new PrintWriter(System.out);
        int n = ni();
        int a = ni();
        int b = ni();
        long oo = Long.MIN_VALUE;
        int[] ar = new int[n + 1];
        long[] p = new long[n + 1];
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; ++i) {
            ar[i] = ni();
            p[i] = ar[i];
            p[i] += p[i - 1];
        }

        TreeMap<Long, Integer> mp = new TreeMap<>();
        long ans = -oo;

        for (int i = a; i <= n; ++i) {
            if (i > b) {
                mp.put(p[i - b - 1], mp.get(p[i - b - 1]) - 1);
                if (mp.get(p[i - b - 1]) == 0)
                    mp.remove(p[i - b - 1]);
            }
            if (mp.get(p[i - a]) != null)
                mp.put(p[i - a], mp.get(p[i - a]) + 1);
            else
                mp.put(p[i - a], 1);
            ans = Math.max(ans, p[i] - mp.firstKey());
        }
        pw.println(ans);
        pw.close();
        br.close();
    }
}
