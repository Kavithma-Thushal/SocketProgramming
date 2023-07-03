import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1)){
            System.out.println("Server is listening for clients .....   ");
            Socket socket = serverSocket.accept();
            System.out.println("Client is connected!");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            String msg = "";
            String rly;

            while (!msg.equals("bye")) {
                rly = dataInputStream.readUTF();
                System.out.println("Client : " + rly);

                System.out.print("Server : ");
                msg = bufferedReader.readLine();
                dataOutputStream.writeUTF(msg);
                dataOutputStream.flush();
            }

            dataInputStream.close();
            dataOutputStream.close();
            bufferedReader.close();
            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
