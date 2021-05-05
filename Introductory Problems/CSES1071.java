import java.util.*;
import java.io.*;

public class CSES1071 {
    static PrintWriter pw;
    static BufferedReader br;
    static StringTokenizer st;

    static long nl() {
        return Long.parseLong(st.nextToken());
    }

    static int ni() {
        return Integer.parseInt(st.nextToken());
    }

    public static void main(String args[])throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(System.out);
        st = new StringTokenizer(br.readLine());
        int t = ni();

        for (int i = 0; i < t; ++i) {
            st = new StringTokenizer(br.readLine());
            long r = nl(); long c = nl();
            long ans = 0;
            
            if (c >= r) {
                if (c % 2 == 1) ans = c * c - r + 1;
                else ans = (c - 1) * (c - 1) + r;
            }
            
            else {
                if (r % 2 == 0) ans = r * r - c + 1;
                else ans = (r - 1) * (r - 1) + c;
            }

            pw.println(ans);
        }
        br.close();
        pw.close();
    }
}