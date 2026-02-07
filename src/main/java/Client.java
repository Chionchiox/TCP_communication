import java.net.Socket;

public class Client {
    private final String nome;
    private String colore;
    private Socket socket;

    public Client(String nome) {
        this.nome = nome;
    }
    public Client(String nome, String colore) {
        this.nome = nome;
        this.colore = colore;
    }

    public int connetti(String nomeServer, int portaServer){return 0;}

    public void scrivi(){}
    public void leggi(){}
    public void chiudi(){}


}
