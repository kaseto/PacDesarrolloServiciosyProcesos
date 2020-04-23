package client;

import javax.xml.crypto.Data;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    String arrayTareas[];

    private Socket socket;
    Scanner leer=new Scanner(System.in);


    public Client() throws IOException {
        socket=new Socket("localhost",9876);
    }

    public void connnect () {
        try {

            DataInputStream entrada= new DataInputStream(socket.getInputStream());
            DataOutputStream salida= new DataOutputStream(socket.getOutputStream());

            System.out.println(entrada.readUTF());
            System.out.println(entrada.readUTF());

            salida.writeUTF(leer.next());

            System.out.println(entrada.readUTF());
            int tareas=leer.nextInt();
            salida.write(tareas);


            for (int i=1;i<=tareas;i++){
                System.out.println(entrada.readUTF());
                salida.writeUTF(leer.nextLine());
                System.out.println(entrada.readUTF());
                salida.writeUTF(leer.nextLine());
            }

            System.out.println(entrada.readUTF());

                for (int i =1;i<=tareas;i++){
                    System.out.println(entrada.readUTF());
                }

            salida.close();
            entrada.close();
            socket.close();
        }catch (IOException ex){
            System.out.println("No se ha podido abrir la conexión correstamente");
        }
    }
}