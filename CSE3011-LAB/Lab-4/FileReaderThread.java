import java.io.*;

public class FileReaderThread implements Runnable {
    private BufferedReader bufferedReader;
    private int start, length;

    public FileReaderThread(BufferedReader bufferedReader, int start, int length) {
        this.bufferedReader = bufferedReader;
        this.start = start;
        this.length = length;
    }

    @Override
    public void run() {
        synchronized (bufferedReader) {
            try {
                bufferedReader.reset();
                bufferedReader.skip(start);
                int character;
                int read = 0;
                while ((character = bufferedReader.read()) != -1 && read < length) {
                    System.out.print((char) character);
                    read++;
                }
                System.out.println();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("test.txt"));
            bufferedReader.mark(1024); // Assuming the file is not larger than 1024 characters
            Thread johnThread = new Thread(new FileReaderThread(bufferedReader, 0, 5));
            Thread tomThread = new Thread(new FileReaderThread(bufferedReader, 5, 5));

            johnThread.start();
            tomThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

