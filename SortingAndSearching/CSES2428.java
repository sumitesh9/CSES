import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class CSES2428 {
    static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;

    static int ni() {
        return Integer.parseInt(st.nextToken());
    }

    /*
     * Implement a sliding window and a multiset to count frequency of each element
     * in a window.
     */
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        pw = new PrintWriter(System.out);
        int n = ni();
        int k = ni();
        st = new StringTokenizer(br.readLine());
        int[] a = new int[n];
        for (int i = 0; i < n; ++i)
            a[i] = ni();
        HashMap<Integer, Integer> mp = new HashMap<>();
        int l = 0;
        int r = 0;
        long ans = 0l;
        while (l < n) {
            while (r < n) {
                if (mp.containsKey(a[r]))
                    mp.put(a[r], mp.get(a[r]) + 1);
                else
                    mp.put(a[r], 1);
                if (mp.size() > k) {
                    mp.put(a[r], mp.get(a[r]) - 1);
                    if (mp.get(a[r]) == 0)
                        mp.remove(a[r]);
                    break;
                }
                ++r;
            }
            ans += (r - l);
            mp.put(a[l], mp.get(a[l]) - 1);
            if (mp.get(a[l]) == 0)
                mp.remove(a[l]);
            ++l;
        }
        pw.println(ans);
        pw.close();
        br.close();
    }
}