package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private Socket cliente;
    private final int PUERTO=9876;
    private ServerSocket servidor;
    private String tarea;
    String estado;


    public Server() throws IOException {
        servidor=new ServerSocket(PUERTO);
        cliente=new Socket();
    }
    public void connect() throws IOException {
        System.out.println("Inciando servidor");
        System.out.println("Esperando primer cliente....");

        while (true) {
            cliente=servidor.accept();
            DataInputStream entrada=new DataInputStream(cliente.getInputStream());
            DataOutputStream salida=new DataOutputStream(cliente.getOutputStream());

            salida.writeUTF("Bienvenido cliente");
            salida.writeUTF("Introduzca su nombre:");

            String nombre=(entrada.readUTF());
            System.out.println("El nombre del cliente es: "+nombre);
            salida.writeUTF("¿Cúantas tareas desea realizar?");

            int numTareas=entrada.read();
            System.out.println("Desea realizar "+ numTareas+" tareas.");
            Tarea [] tareas=new Tarea[numTareas];





           for (int i=1;i<=numTareas;i++) {

               salida.writeUTF("¿Cúal es la tarea nº " + i + " que desea realizar?");
               tarea= (entrada.readUTF());
               System.out.println("Tarea "+i+" recibida: "+ tarea);
               salida.writeUTF("¿Cúal es el estado de la tarea?");
               estado=(entrada.readUTF());
               System.out.println("Estado de la tarea "+i+" recibido: "+ estado);

                 tareas[i-1]=new Tarea(tarea,estado);
               System.out.println("Tarea: "+tareas[i-1].toString()+" guardada correctamente.");
           }

           salida.writeUTF("Ya ha terminado, procedemos a enviar el listado de las tareas ");

                    for (int i =1;i<=numTareas;i++){

                        salida.writeUTF("Tarea 1: "+ tareas[i-1].getDescripcion()+ " Estado: "+tareas[i-1].getEstado());
                    }
            String mensajeDeCliente;
            try{
                while(!(mensajeDeCliente=entrada.readUTF()).isEmpty())

                    System.out.println(mensajeDeCliente);
            }catch(EOFException ex) {
                System.out.println("EL cliente ha finalizado la comunicación");

            }
            System.out.println("Esperando un nuevo cliente");

        }
    }
}



