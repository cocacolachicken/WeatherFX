import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class In {
    Scanner reader;
    FileInputStream inputStream;

    public In(String path) {
        // File-based
        try {
            inputStream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            System.err.println("In Class - File I/O Error: Unable to read file");
            e.printStackTrace();
        } catch(IOException e) {
            System.err.println("In Class - File I/O Error: Unable to read file");
            e.printStackTrace();
        } finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.err.println("In Class - File I/O Error: Unable to close the input stream");
                }
            }
            if(reader != null) {
                reader.close();
            }
        }
    }

    public boolean hasNext() { return reader.hasNext(); }
    public boolean hasNextInt() { return reader.hasNextInt(); }
    public boolean hasNextDouble() { return reader.hasNextDouble(); }
    public boolean hasNextBoolean() { return reader.hasNextBoolean(); }
    public boolean hasNextByte() { return reader.hasNextByte(); }
    public boolean hasNextFloat() { return reader.hasNextFloat(); }
    public boolean hasNextLine() { return reader.hasNextLine(); }
    public boolean hasNextLong() { return reader.hasNextLong(); }
    public boolean hasNextShort() { return reader.hasNextShort(); }
    public boolean eof() { return !hasNext(); }

    public String next() { return reader.next(); }
    public String nextLine() { return reader.nextLine(); }
    public boolean nextBoolean() { return reader.nextBoolean(); }
    public byte nextByte() { return reader.nextByte(); }
    public double nextDouble() { return reader.nextDouble(); }
    public float nextFloat() { return reader.nextFloat(); }
    public int nextInt() { return reader.nextInt(); }
    public long nextLong() { return reader.nextLong(); }
    public short nextShort() { return reader.nextShort(); }

    public void close() {
        if(inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                System.err.println("In Class - File I/O Error: Unable to close the input stream");
            }
        }
        if(reader != null) {
            reader.close();
        }
    }
}
