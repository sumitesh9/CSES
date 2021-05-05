import java.util.*;
import java.io.*;

public class CSES1090 {
    static PrintWriter pw;
    static BufferedReader br;
    static StringTokenizer st;

    static int ni() {
        return Integer.parseInt(st.nextToken());
    }

    /*
        Sort the array in non decreasing order
        Assign greedily :-
        Since we can't have more than two children in same gondola so for every
        ith children with weight w we have to find maximum weight of child to the
        right with weight w - x , if it doesn't exist then we have to take only ith
        child in current gondola.
    */

    // Immune to TLE
    static void sort(int a[]) {
        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 0; i < a.length; ++i) al.add(a[i]);
        Collections.sort(al);
        int i = 0;
        for (Integer x : al) a[i++] = x;
    }

    public static void main(String args[])throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(System.out);
        st = new StringTokenizer(br.readLine());
        
        int n = ni();
        int x = ni();

        int a[] = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) a[i] = ni();

        sort(a);

        int ans = n;
        
        int r = n - 1;
        for (int l = 0; l < n; ++l) {
            while (r > l) {
                if (a[l] + a[r] <= x) {
                    --r;
                    --ans;
                    break;
                }
                --r;
            }
        }

        pw.println(ans);

        br.close();
        pw.close();
    }
}