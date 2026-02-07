import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private final int porta;
    private StatoServer stato;

    public Server(int porta) throws IOException {
        this.stato = StatoServer.CREATO;
        this.porta = porta;
    }

    public void avvia() throws IOException {
        if(stato != StatoServer.CREATO)
            throw new IllegalStateException("Server già avviato.");

        serverSocket = new ServerSocket(porta);
        stato = StatoServer.IN_ASCOLTO;
        Colori.PRINT_COMUNICATION("SERVER AVVIATO ALLA PORTA " + porta);
    }

    public Socket attendi() throws IOException{
        if(stato != StatoServer.IN_ASCOLTO)
            throw new IllegalStateException("Server non in ascolto.");


        clientSocket = serverSocket.accept();
        stato = StatoServer.CONNESSO;
        return clientSocket;
    }

    public StatoServer getStato() {return stato;}

    public void setStato(StatoServer stato){
        this.stato = stato;
        printStato();
    }

    public void printStato(){Colori.PRINT_COMUNICATION("STATO SERVER: " + stato);}

    public void scrivi(){}
    public void leggi(){}
    public void chiudi(){}
    public void termina(){}
}
