

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MyServer {
    static ArrayList<Socket> clientList = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println("Starting server at Port 3000.....");
        try(ServerSocket ss = new ServerSocket(3000)){
            while(true) {
                Socket client = ss.accept();
                clientList.add(client);
                handleRequest(client);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void handleRequest(Socket client) throws Exception {
        System.out.println(client.getInetAddress().getHostAddress() + " connected successfully!!");
        Runnable comms = () -> {
        try(BufferedInputStream in = new BufferedInputStream(client.getInputStream());
            BufferedOutputStream out = new BufferedOutputStream(client.getOutputStream())){
                handleComms(in, out, client);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
           
        };

        new Thread(comms).start();
    }

    public static void handleComms(BufferedInputStream in, BufferedOutputStream out, Socket client) throws Exception {
        byte[] data = new byte[1024];
        int bytesRead = in.read(data);
        while(bytesRead != -1){
            send(data, bytesRead, client);
            bytesRead = in.read(data);
        }
    }

    public static void send(byte[] data, int length, Socket sender) throws Exception {
    for (Socket present : clientList) {
        if (present != sender && !present.isClosed()) {
            try {
                BufferedOutputStream presentOut =
                        new BufferedOutputStream(present.getOutputStream());
                presentOut.write(data, 0, length);
                presentOut.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

}
