package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *  Server principale, gestore dei thread per ogni utente
 */
public class MultiServer {



    private final int MAX_PORT = 65534;
    private final int MIN_PORT = 1024;

    /**
     *  Porta del server
     */
    private int PORT = 8080;

    /**
     * Costruttore del server con numero di porta fornito come parametro
     *
     * @param port Porta del server
     * @throws IOException Errore nell'avvio del server
     */
    public MultiServer(int port) throws IOException {


        if (port >= MIN_PORT && port <= MAX_PORT){
            this.PORT = port;
            System.out.println("Server started on PORT " + port);
        }
        run();
    }


    /**
     * Costruttore del server con numero di porta di default
     *
     * @throws IOException Errore nell'avvio del server
     */
    public MultiServer() throws  IOException{
        System.out.println("Server started on DEFAULT PORT 8080");
        run();
    }

    /**
     * Mainloop del server
     */
    private void run() throws IOException {
        System.out.println("Server is now active!");
        ServerSocket s = new ServerSocket(PORT);
        try{
            while(true){
                Socket so = s.accept();
                try{
                    new ServerOneClient(so);
                }catch(IOException e){
                    so.close();
                }
            }
        }finally{
            s.close();
        }
    }
}
