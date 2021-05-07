import java.util.*;
import java.io.*;

public class CSES1732 {
    static BufferedReader br;
    static PrintWriter pw;

    /*
        One of less elegant way of solving this problem is via String Hashing
        (May lead to collisions for some keys , passed CSES test cases though).

        Hashing Technique used - (Polynomial Hashing using ASCII Code).
        Refer to Page 245 of CP Handbook by Antti Laaksonen

        Primes used - 911382323 , 972663749
    */

    static long a , b , p[] , h[];

    public static long getHash(int l , int r) {
        if (l == 0) return h[r];
        long ans = ((h[r] - (h[l - 1] * p[r - l + 1]) % b) + b) % b;
        return ans;
    }

    public static void main(String args[])throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(System.out);

        String s = br.readLine();
        int n = s.length();

        p = new long[n];
        p[0] = 1l;

        a = 911382323l;
        b = 972663749l;

        for (int i = 1; i < n; ++i) {
            p[i] = p[i - 1] * a;
            p[i] %= b;
        }

        h = new long[n];

        h[0] = (int)s.charAt(0);

        for (int i = 1; i < n; ++i) {
            h[i] = ((h[i - 1] * a) + (int)s.charAt(i)) % b;
        }

        for (int i = 0 , j = n - 1; i + 1 < n && j > 0; ++i , --j) {
            if (getHash(0 , i) == getHash(j , j + i)) pw.print((i + 1) + " ");
        }

        pw.close();
        br.close();
    }
}