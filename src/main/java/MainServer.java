import java.io.IOException;
import java.net.BindException;
import java.net.Socket;

public class MainServer {
    public static void main(String[] args) {
        int porta = 1234;

        try
        {
            Server server = new Server(porta);
            server.avvia();
            Socket socket = server.attendi();

            System.out.println(socket.getInetAddress().getHostName());
        }catch (BindException e)
        {
            Colori.PRINT_ERROR(e.getMessage());
            System.exit(1);
        }
        catch (IOException e)
        {
            Colori.PRINT_ERROR(e.getMessage());
            System.exit(0);
        }
    }
}
