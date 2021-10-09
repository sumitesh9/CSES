import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
 
public class CSES1091 {
    static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;
 
        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }
 
        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }
 
        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');
 
            if (neg)
                return -ret;
            return ret;
        }
    }
    public static void main(String[] args) throws IOException {
        Reader br = new Reader();
 
        int n = br.nextInt();
        int m = br.nextInt();
        TreeMap<Integer, Integer> ticket_price= new TreeMap<>();
        StringBuffer res = new StringBuffer();
        int temp=0;
 
        for (int i = 0; i < n; i++) {
            temp = br.nextInt();
            if (ticket_price.containsKey(temp))
                ticket_price.put(temp,ticket_price.get(temp)+1);
            else ticket_price.put(temp,1);
        }
        for (int i = 0; i < m; i++) {
            Map.Entry<Integer, Integer> price_found;

            temp = br.nextInt();
            price_found=ticket_price.lowerEntry(temp+1);
            if (price_found!=null){
                res.append(price_found.getKey()).append('\n');
                if (price_found.getValue() == 1)
                    ticket_price.remove(price_found.getKey());
                else
                    ticket_price.put(price_found.getKey(),price_found.getValue()-1);
            }else
                res.append("-1\n");
        }
 
        System.out.println(res);
    }
}