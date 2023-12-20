package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.Paths;


public class ServerMain 
{
    public static void main( String[] args ) throws IOException
    {
        Server s1 = new Server();

        s1.openServer();

        while (true){

            s1.accept();
            String req = s1.read().split(" ")[1];

            if(req.equals("/")){
                req = "/index.html";
            }

            File file = new File("src/main/resources" + req);
            
            if(file.exists() && file.isFile()){
                s1.reply("HTTP/1.1 200 OK\r\n".getBytes());
                s1.reply("Content-type: text/html\r\n\r\n".getBytes());
                s1.reply(Files.readAllBytes(file.toPath()));
            }else{
                System.out.println("Non ho questa pagina scusa!");
                s1.reply("HTTP/1.1 404 Not Found\r\n".getBytes());
                s1.reply("Content-type: text/html\r\n\r\n".getBytes());
                s1.reply(Files.readAllBytes(Paths.get("src/main/resources/error.html")));
            }
        }
    }
}
