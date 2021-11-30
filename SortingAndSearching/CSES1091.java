package SortingAndSearching;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class CSES1091 {
    static class Reader {
        private final int bufferSize = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer;
        private int bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[bufferSize];
            bufferPointer = bytesRead = 0;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, bufferSize);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }
    }

    public static void main(String[] args) throws IOException {
        Reader br = new Reader();

        int n = br.nextInt();
        int m = br.nextInt();
        TreeMap<Integer, Integer> price = new TreeMap<>();
        StringBuilder res = new StringBuilder();
        int temp = 0;

        for (int i = 0; i < n; i++) {
            temp = br.nextInt();
            if (price.containsKey(temp))
                price.put(temp, price.get(temp) + 1);
            else
                price.put(temp, 1);
        }
        for (int i = 0; i < m; i++) {
            Map.Entry<Integer, Integer> priceFound;

            temp = br.nextInt();
            priceFound = price.lowerEntry(temp + 1);
            if (priceFound != null) {
                res.append(priceFound.getKey()).append('\n');
                if (priceFound.getValue() == 1)
                    price.remove(priceFound.getKey());
                else
                    price.put(priceFound.getKey(), priceFound.getValue() - 1);
            } else
                res.append("-1\n");
        }

        System.out.println(res);
    }
}