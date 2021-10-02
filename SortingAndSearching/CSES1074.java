import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class CSES1074 {
    static BufferedReader br;
    static StringTokenizer st;
    static int ni() {
        return Integer.parseInt(st.nextToken());
    }

    /* Answer is always median of the array , with even sized array you can check for both
    the middle candidates. 
    */
    public static void main(String[] args)throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        int n = ni();
        st = new StringTokenizer(br.readLine());
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            a.add(ni());
        }
        if (n == 1) System.out.println(0);
        else {
            Collections.sort(a);
            long ans = Long.MAX_VALUE;
            long curr = 0l;
            long with = a.get(n / 2);
            for (int i = 0; i < n; ++i) {
                curr += Math.abs(with - a.get(i));
            }
            ans = Math.min(ans , curr);
            with = a.get((n / 2) - 1);
            curr = 0l;
            for (int i = 0; i < n; ++i) {
                curr += Math.abs(with - a.get(i));
            }
            ans = Math.min(ans , curr);
            System.out.println(ans);
        }
    }
}
