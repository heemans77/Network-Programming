import java.io.*;

public class Test3 {
    public static void main(String[] args) {
        readChars();
        System.out.println("\n");
        readCharsBA();
        System.out.println("\n");
        ignoreFirst100Chars();
        System.out.println("\n");
        availMethod();
        System.out.println("\n");
        printCharsUsingMR();
        System.out.println("\n");
    }

    private static void readChars() {
        try (FileInputStream fis = new FileInputStream("input.txt")) {
            int b;
            while ((b = fis.read()) != -1) {
                char c = (char) b;
                System.out.print(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readCharsBA() {
        try (FileInputStream fis = new FileInputStream("input.txt")) {
            byte[] ba = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(ba)) != -1) {
                String s = new String(ba, 0, bytesRead);
                System.out.print(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void ignoreFirst100Chars() {
        try (FileInputStream fis = new FileInputStream("input.txt")) {
            long sb = fis.skip(100);
            byte[] ba = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(ba)) != -1) {
                String s = new String(ba, 0, bytesRead);
                System.out.print(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void availMethod() {
        try (FileInputStream fis = new FileInputStream("input.txt")) {
            System.out.println("Available Bytes initially: " + fis.available());
            fis.read(); 
            System.out.println("Available bytes after reading one byte: " + fis.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printCharsUsingMR() {
        try (
            FileInputStream fis = new FileInputStream("input.txt");
            BufferedInputStream bis = new BufferedInputStream(fis);) {
    
            bis.skip(10);
            bis.mark(500021);
    
            if (bis.markSupported()) {
                System.out.println("Mark and reset supported");
            }else {
                System.out.println("Mark and reset not supported");
                return;
            }
    
            byte[] buf = new byte[20];
            for (int i = 0; i < 10; i++) {
                if (i > 0) {
                    bis.reset();
                }
                int bytesRd = bis.read(buf, 0, 20);
                if (bytesRd == -1) {
                    break;
                }
                String str = new String(buf, 0, bytesRd);
                System.out.println("Iteration No. " + (i + 1) +  ": " + str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    }
    