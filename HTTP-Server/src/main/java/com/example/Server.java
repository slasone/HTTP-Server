package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Server {

    private ServerSocket serverSocket;
    private Socket client;
    private BufferedReader input;
    private DataOutputStream output;

    public void accept(){
        try {
            client = serverSocket.accept();
            input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            output = new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
            
            System.out.println("Errore!");
            e.printStackTrace();
        }
    }
     

    public void openServer() {
        try {
            serverSocket = new ServerSocket(8080);
            System.out.println("Server in ascolto sulla porta 8080!");
        } catch (IOException e) {
            System.out.println("Errore durante l'avvio del ServerSocket");
            e.printStackTrace();
        }
    }


    public String read(){
        try {
            return input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    } 
    
    public void reply(byte [] parametro){
        try {
            output.write(parametro);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
