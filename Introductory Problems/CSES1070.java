import java.util.*;
import java.io.*;

class CSES1070 {
	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter pw;

	static int ni() {
		return Integer.parseInt(st.nextToken());
	}

	/*
		One of the constructive solution is to print even numbers
		first and then odd numbers
		For n = 2 , 3 we have no valid solution
	*/ 

	public static void main(String args[])throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(System.out);
        st = new StringTokenizer(br.readLine());
 
        int n = ni();
        if (n == 2 || n == 3) pw.println("NO SOLUTION");

        else {
	        for (int i = 2; i <= n; i += 2) pw.print(i + " ");
	        for (int i = 1; i <= n; i += 2) pw.print(i + " ");
 		}

        br.close();
        pw.close();
	}
}