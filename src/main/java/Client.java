import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
    La classe {@code Client} rappresenta le informazioni e le funzioni di un client. <br>
    Ogni client è identificato da un nome e ha i metodi minimi per stabilire una comunicazione e scambiare informazioni.
 */
public class Client {
    private final String nome;

    private Socket socket;

    private PrintWriter out;
    private BufferedReader in;

    /**
     * Metodo costruttore della classe {@link Client}.
     * @param nome il nome del client
     */
    public Client(String nome) {
        this.nome = nome;
    }

    /**
     * Serve a connettersi con il server.
     * @param nomeServer il nome del server al quale ci si vuole connettere
     * @param portaServer la porta del server al quale ci si vuole connettere
     * @return {@code 1} se è tutto corretto, {@code 0} se si è verificato qualche errore
     */
    public int connetti(String nomeServer, int portaServer){
        try{
            socket = new Socket(nomeServer, portaServer);
            inizializzaStream();

            return 1;
        } catch (UnknownHostException e) {
            Colori.PRINT_ERROR("Host non trovato: " + e.getMessage());
            return 0;
        } catch (IOException e){
            Colori.PRINT_ERROR("Errore di connessione: " + e.getMessage());
            return 0;
        }
    }

    /**
     * Serve a scrivere un messaggio al server
     * @param msg il messaggio che vogliamo inviare
     */
    public void scrivi(String msg){
        if(socket == null || socket.isClosed())
            throw new IllegalStateException("Client non connesso.");

        out.println(msg);
    }

    /**
     * Serve a leggere i messaggi del server
     * @return il messaggio letto
     * @throws IOException il client non è connesso
     */
    public String leggi() throws IOException {
        if (socket == null || socket.isClosed())
            throw new IllegalStateException("Client non connesso.");
        return in.readLine();
    }

    /**
     * Serve a chiudere la connessione tra client e server, disconnettendo il client.
     */
    public void chiudi(){
        try{
            if(in != null) in.close();
            if(out != null) out.close();
            if(!socket.isClosed() && socket != null) socket.close();

            Colori.PRINT_MESSAGE("Client " + nome + " disconnesso.");

        } catch (IOException e) {
            Colori.PRINT_ERROR("Errore I/O: " + e.getMessage());
        }
    }

    /**
     * Inizzializza gli stream di input ({@link BufferedReader}) e output ({@link PrintWriter})
     * @throws IOException
     */
    private void inizializzaStream() throws IOException{
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

}
