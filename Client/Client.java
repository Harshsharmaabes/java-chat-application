

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Socket s = null;
        try {
            s = new Socket("localhost", 3000);
            BufferedInputStream in = new BufferedInputStream(s.getInputStream());
            BufferedOutputStream out = new BufferedOutputStream(s.getOutputStream());
            Scanner sc = new Scanner(System.in);

            System.out.println("Connected to server successfully...!");
            System.out.print("Enter your name: ");
            String name = sc.nextLine();

            // Thread for reading
            new Thread(() -> {
                try {
                    readAndDisplay(in);
                } catch (Exception e) {
                    System.out.println("Connection closed (reading).");
                }
            }).start();

            // Thread for sending
            new Thread(() -> {
                try {
                    send(out, sc, name);
                } catch (Exception e) {
                    System.out.println("Connection closed (sending).");
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void send(BufferedOutputStream out, Scanner sc, String name) throws Exception {
        while (true) {
            // System.out.print("Enter data that you want to send: ");
            System.out.print("\u001B[31m" + "You: " + "\u001B[0m");
            String data = sc.nextLine();
            out.write((name + ": " +data + "\n").getBytes());
            out.flush();
        }
    }

    public static void readAndDisplay(BufferedInputStream in) throws Exception {
        int dataRecieved;
        int flag = 0;
        int flag2 = 0;
        while ((dataRecieved = in.read())!= -1) {
            if(flag == 0){
                System.out.print("\r"); // move cursor to starting of the line
                System.out.print("\033[K"); // clear the line
                flag++;
            }
            char c = (char) dataRecieved;
            if(flag2 == 0){
                System.out.print("\u001B[32m" + c);
            }
            else {
                System.out.print(c);
            }
            if(c == '\n') {
                flag = 0;
                flag2 = 0;
                System.out.print("\u001B[31m" + "You: " + "\u001B[0m");
            }
            if(c == ':'){
                flag2 = 1;
                System.out.print("\u001B[0m");
            }
        }
    }
}
