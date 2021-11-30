package SortingAndSearching;

import java.util.*;
import java.io.*;

public class CSES1621 {

    static BufferedReader br;
    static PrintWriter pw;
    static StringTokenizer st;

    static int ni() {
        return Integer.parseInt(st.nextToken());
    }

    public static void main(String args[]) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(System.out);
        st = new StringTokenizer(br.readLine());

        int n = ni();
        HashSet<Integer> h = new HashSet<>();

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; ++i) h.add(ni());

        pw.println(h.size());
        pw.close();
        br.close();
    }   
}