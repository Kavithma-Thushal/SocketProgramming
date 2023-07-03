import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1)){

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            String msg = "";
            String rly;

            while (!msg.equals("bye")) {
                System.out.print("Client : ");
                msg = bufferedReader.readLine();
                dataOutputStream.writeUTF(msg);
                dataOutputStream.flush();

                rly = dataInputStream.readUTF();
                System.out.println("Server : " + rly);
            }

            dataInputStream.close();
            dataOutputStream.close();
            bufferedReader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
