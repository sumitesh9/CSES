import java.util.*;
import java.io.*;

public class CSES1094 {
    static PrintWriter pw;
    static BufferedReader br;
    static StringTokenizer st;

    static int ni() {
        return Integer.parseInt(st.nextToken());
    }

    /*
        Traverse the array from left to right and
        if ith element is strictly lesser than i - 1 th
        element than set ith element to i - 1 th and 
        add abs(a[i] - a[i - 1]) to the cost

        Proof of correctness is pretty simple here , as we 
        are updating every element to its minimal requirement 
        hence this algorithm leads to correct answer in minimal
        operations   
    */

    public static void main(String args[])throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(System.out);
        st = new StringTokenizer(br.readLine());

        int n = ni();
        int a[] = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) a[i] = ni();

        long ans = 0l;
        int p = a[0];

        for (int i = 1; i < n; ++i) {
            if (p > a[i]) {
                ans += p - a[i];
                a[i] = p;
            }
            p = a[i];
        }

        pw.println(ans);

        br.close();
        pw.close();
    }
}