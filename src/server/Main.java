package server;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {

    public  static void main(String[]args) throws IOException {
        Server servidor=new Server();
        servidor.connect();


    }
}
