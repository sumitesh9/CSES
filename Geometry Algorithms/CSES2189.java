import java.util.*;
import java.io.*;

public class CSES2189 {
    static PrintWriter pw;

    /*
        Simplest way to solve this problem is to find 
        cross product of two vectors
        Assume First vector as (x1 , y1) -> (x3 , y3)
        and Second vector as (x2 , y2) -> (x3 , y3);
        
        If cross product of two vectors is positive then point lies left of line
        or if cross product is negative then point lies right of line else 
        point lies on the line itself.

        Reference - https://www.geeksforgeeks.org/direction-point-line-segment/

    */

    public static void main(String args[])throws Exception {
       FastScanner f = new FastScanner(System.in);
        pw = new PrintWriter(System.out);
        int t = f.ni();

        for (int i = 0; i < t; ++i) {
            long x1 = f.nl();
            long y1 = f.nl();
            long x2 = f.nl();
            long y2 = f.nl();
            long x3 = f.nl();
            long y3 = f.nl();

            // Shift x1 , y1 to origin
            x2 -= x1;
            y2 -= y1;
            x3 -= x1;
            y3 -= y1;

            long cp = (x2 * y3) - (y2 * x3);
            if (cp == 0l) pw.println("TOUCH");
            else if (cp < 0l) pw.println("RIGHT");
            else pw.println("LEFT");
        }

        pw.close();
    }

    public static class FastScanner {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private FastScanner.SpaceCharFilter filter;
 
        public FastScanner(InputStream stream) {
            this.stream = stream;
        }
 
        public int read() {
            if (numChars == -1)
                throw new InputMismatchException();
 
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
 
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }
 
        public int ni() {
            int c = read();
 
            while (isSpaceChar(c))
                c = read();
 
            int sgn = 1;
 
            if (c == '-') {
                sgn = -1;
                c = read();
            }
 
            int res = 0;
            do {
                if (c < '0' || c > '9') throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            while (!isSpaceChar(c));
 
            return res * sgn;
        }
 
        public long nl() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
 
            do {
                if (c < '0' || c > '9') throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            while (!isSpaceChar(c));
            return res * sgn;
        }
 
        public String next() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            }
            while (!isSpaceChar(c));
            return res.toString();
        }
 
        public boolean isSpaceChar(int c) {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
 
        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);
        }
    }
}