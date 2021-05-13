import java.util.*;
import java.io.*;

public class CSES1072 {
    static PrintWriter pw;
    static BufferedReader br;
    static StringTokenizer st;

    static long nl() {
        return Long.parseLong(st.nextToken());
    }

    /*
        Using IEP here:
        Number of ways of choosing two squares in n * n chessboard = C(n * n , 2)
        Now we need to subtract number of ways of choosing two squares such that
        two knights attack each other when placed on them

        When two knights attack each other they form 2 * 3 or 3 * 2 chess board and
        count of number of 2 * 3 or 3 * 2 chess board = 

        (n - 1) * (n - 2) + (n - 2) * (n - 1) = 2 * (n - 1) * (n - 2)

        (n - 1) ways of picking topleft square as if we cant pick last row (Think Why ?)
        (n - 2) ways of picking bottom right sqaure

        and for each 2 * 3 or 3 * 2 chess board we have two ways of putting 
        knights so 

        Total ways = 2 * 2 (n - 1) * (n - 2)

        Final answer = C(n * n , 2) - 4 * (n - 1) * (n - 2)
    */

        public static void main(String args[])throws Exception {
            br = new BufferedReader(new InputStreamReader(System.in));
            pw = new PrintWriter(System.out);
            st = new StringTokenizer(br.readLine());
            long t = nl();

            for (long i = 1; i <= t; ++i) {
                long totalWays = ((i * i) * ((i * i) - 1)) / 2;
                long toRemove = 4 * (i - 1) * (i - 2);
                long ans = totalWays - toRemove;
                pw.println(ans);
            }

            br.close();
            pw.close();
        }
    }