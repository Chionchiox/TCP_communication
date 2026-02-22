import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * La classe {@code Server} rappresenta le informazioni e le funzioni di un server. <br>
 * Ogni server ha la propria porta univoca.
 */
public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;

    private PrintWriter out;
    private BufferedReader in;

    private final int porta;
    private StatoServer stato;

    /**
     * Metodo costruttore della classe {@link Server} <br>
     * Lo stato del server è "CREATO"
     * @param porta la porta del client
     */
    public Server(int porta){
        this.stato = StatoServer.CREATO;
        this.porta = porta;
    }

    /**
     * Avvia il server istanziando il serverSocket con la porta scelta. <br>
     * Il server è ora in stato di "IN_ASCOLTO".
     * @throws IOException
     */
    public void avvia() throws IOException {
        if(stato != StatoServer.CREATO)
            throw new IllegalStateException("SERVER GIA' AVVIATO.");

        serverSocket = new ServerSocket(porta);
        stato = StatoServer.IN_ASCOLTO;
        Colori.PRINT_COMUNICATION("SERVER IN ASCOLTO SULLA PORTA " + porta);
    }

    /**
     * Attende le richiese da parte dei client e le accetta, stabilendo così una connessione. <br>
     * Lo stato del server passa a "CONNESSO".
     * @return la socket del client
     * @throws IOException
     */
    public Socket attendi() throws IOException{
        if(stato != StatoServer.IN_ASCOLTO)
            throw new IllegalStateException("SERVER NON IN ASCOLTO.");

        clientSocket = serverSocket.accept();
        inizializzaStream();
        stato = StatoServer.CONNESSO;
        return clientSocket;
    }

    /**
     * Inizzializza gli stream di input ({@link BufferedReader}) e output ({@link PrintWriter})
     * @throws IOException
     */
    private void inizializzaStream() throws IOException {
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    /**
     * Serve a scrivere un messaggio al client
     * @param msg il messaggio che vogliamo inviare
     */
    public void scrivi(String msg){
        if(stato != StatoServer.CONNESSO)
            throw new IllegalStateException("SERVER NON CONNESSO.");

        out.println(msg);
    }

    /**
     * Serve a leggere i messaggi del client
     * @return il messaggio letto
     * @throws IOException il server non è connesso
     */
    public String leggi() throws IOException {
        if(stato != StatoServer.CONNESSO)
            throw new IllegalStateException("SERVER NON CONNESSO.");

        return in.readLine();
    }

    /**
     * Serve a chiudere la connessione con un client. <br>
     * Chiude tutti gli stream e la socket del client. <br>
     * Lo stato del server torna a "IN_ASCOLTO"
     */
    public void chiudi() {
        try {
            if(in != null) in.close();
            if(out != null) out.close();

            if (clientSocket != null && !clientSocket.isClosed())
                clientSocket.close();

            stato = StatoServer.IN_ASCOLTO;
            Colori.PRINT_MESSAGE("CLIENT DISCONNESSO.");
        } catch (IOException e) {
            Colori.PRINT_ERROR("ERRORE I/O: " + e.getMessage());
        }
    }

    /**
     * Spegne il server chiudendo la {@link ServerSocket}. <br>
     * Lo stato del server passa a "TERMINATO".
     */
    public void termina(){
        try{
            if(stato == StatoServer.CONNESSO)
                chiudi();

            if(serverSocket != null && !serverSocket.isClosed())
                serverSocket.close();

            stato = StatoServer.TERMINATO;
            Colori.PRINT_COMUNICATION("SERVER TERMINATO.");
        } catch (IOException e) {
            Colori.PRINT_ERROR("ERRORE I/O: " + e.getMessage());
        }
    }
}
