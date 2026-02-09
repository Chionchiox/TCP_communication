import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;

    private PrintWriter out;
    private BufferedReader in;

    private final int porta;
    private StatoServer stato;

    public Server(int porta){
        this.stato = StatoServer.CREATO;
        this.porta = porta;
    }

    public void avvia() throws IOException {
        if(stato != StatoServer.CREATO)
            throw new IllegalStateException("SERVER GIA' AVVIATO.");

        serverSocket = new ServerSocket(porta);
        stato = StatoServer.IN_ASCOLTO;
        Colori.PRINT_COMUNICATION("SERVER IN ASCOLTO SULLA PORTA " + porta);
    }

    public Socket attendi() throws IOException{
        if(stato != StatoServer.IN_ASCOLTO)
            throw new IllegalStateException("SERVER NON IN ASCOLTO.");

        clientSocket = serverSocket.accept();
        inizializzaStream();
        stato = StatoServer.CONNESSO;
        return clientSocket;
    }

    private void inizializzaStream() throws IOException {
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    public void scrivi(String msg){
        if(stato != StatoServer.CONNESSO)
            throw new IllegalStateException("SERVER NON CONNESSO.");

        out.println(msg);
    }

    public String leggi() throws IOException {
        if(stato != StatoServer.CONNESSO)
            throw new IllegalStateException("SERVER NON CONNESSO.");

        return in.readLine();
    }

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

    public void printStato(){Colori.PRINT_COMUNICATION("STATO SERVER: " + stato);}
}
