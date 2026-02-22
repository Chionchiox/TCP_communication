import java.io.IOException;
import java.net.BindException;
import java.net.Socket;

public class MainServer {
    public static void main(String[] args) {
        int porta = 1234;
        Server server = null;

        try
        {
            server = new Server(porta);
            server.avvia();

            Socket socket = server.attendi();
            Colori.PRINT_MESSAGE("CLIENT CONNESSO: " + socket.getInetAddress().getHostName());

            // CON IL FOR
            /*
            int NUM_MESSAGGI = 3;

            for(int i=NUM_MESSAGGI; i>=0; i--){
                Colori.PRINT_COMUNICATION("Messaggi rimanenti: " + i);
                String msg = server.leggi();

                if(msg == null || msg.equalsIgnoreCase("exit"))
                    break;

                Colori.PRINT_MESSAGE("Ricevuto messaggio: " + msg);
                server.scrivi("Ho ricevuto " + msg + " [messaggi rimanenti: " + i + "]");
            }

            server.chiudi();
            */

            // CON IL WHILE
            while(true) {

                String msg = server.leggi();

                if(msg == null || msg.equalsIgnoreCase("exit"))
                    break;

                Colori.PRINT_COMUNICATION("Ricevuto messaggio: " + msg);
                server.scrivi("Ho ricevuto " + msg);
            }

            server.chiudi();

        }catch (BindException e)
        {
            Colori.PRINT_ERROR("Porta già occupata");
            System.exit(1);
        }
        catch (IOException e)
        {
            Colori.PRINT_ERROR("Errore I/O: " + e.getMessage());
            System.exit(1);
        } finally {
            if(server != null)
                server.termina();
        }
    }
}
