import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Out {
    PrintWriter writer = null;

    public Out(String filepath) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(filepath, false);
            bw = new BufferedWriter(fw);
            writer = new PrintWriter(bw);
        }catch(Exception e) {
            System.err.println("Out Class - I/O Error: Unable to open file for writing");
        } finally {
            if(fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    System.err.println("Out Class - I/O Error: Unable to close the FileWriter");
                }
            }
            if(bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    System.err.println("Out Class - I/O Error: Unable to close the BufferedWriter");
                }
            }
            if(writer != null) {
                writer.close();
            }
        }
    }

    public Out(String filepath, boolean append) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(filepath, append);
            bw = new BufferedWriter(fw);
            writer = new PrintWriter(bw);
        }catch(Exception e) {
            System.err.println("Out Class - I/O Error: Unable to open file for writing");
        } finally {
            if(fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    System.err.println("Out Class - I/O Error: Unable to close the FileWriter");
                }
            }
            if(bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    System.err.println("Out Class - I/O Error: Unable to close the BufferedWriter");
                }
            }
            if(writer != null) {
                writer.close();
            }
        }
    }

    public void print(String output) {
        writer.print(output);
        writer.flush();
    }
    public void print(Object output) {
        writer.print(output.toString());
        writer.flush();
    }

    public void println(String output) {
        writer.println(output);
        writer.flush();
    }
    public void println(Object output) {
        writer.println(output.toString());
        writer.flush();
    }

    public void close() {
        if(writer != null) {
            writer.flush();
            writer.close();
        }
    }
}
