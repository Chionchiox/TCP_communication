import java.io.IOException;
import java.util.Scanner;

public class MainClient {
    static void main(String[] args) {
        String nomeClient = "Fra";
        String hostServer = "localhost";
        int portaServer = 1234;

        Client client = new Client(nomeClient);

        if(client.connetti(hostServer, portaServer) == 0) {
            Colori.PRINT_ERROR("Connessione fallita.");
            return;
        }

        Colori.PRINT_COMUNICATION("Connesso al server.");

        Scanner scan = new Scanner(System.in);
        String msg;

        try {
            while (true) {
                System.out.println("Scrivi un messaggio: ");
                msg = scan.nextLine();

                client.scrivi(msg);

                String risposta = client.leggi();
                System.out.println("Server dice: " + risposta);

                if (msg.equalsIgnoreCase("exit"))
                    break;
            }
        } catch (IOException e) {
            Colori.PRINT_ERROR("Errore I/O: " + e.getMessage());
        } finally {
            client.chiudi();
            scan.close();
        }
    }
}
