package client;


import java.io.IOException;
import java.util.concurrent.Callable;

public class Main {

    public static void main(String[]args) throws IOException {
        Client clienteSocket=new Client();
        clienteSocket.connnect();

    }
}
