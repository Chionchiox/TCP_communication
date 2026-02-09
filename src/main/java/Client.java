import java.io.*;
import java.net.Socket;

public class Client {
    private final String nome;

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public Client(String nome) {
        this.nome = nome;
    }

    public int connetti(String nomeServer, int portaServer){
        try{
            socket = new Socket(nomeServer, portaServer);
            inizializzaStream();

            return 1;
        } catch (IOException e){
            Colori.PRINT_ERROR("Errore di connessione: " + e.getMessage());
            return 0;
        }
    }

    public void scrivi(String msg){
        if(socket == null || socket.isClosed())
            throw new IllegalStateException("Client non connesso.");

        out.println(msg);
    }

    public String leggi() throws IOException{
        if(socket == null || socket.isClosed())
            throw new IllegalStateException("Client non connesso.");
        return in.readLine();
    }
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

    private void inizializzaStream() throws IOException{
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

}
