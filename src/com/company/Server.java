package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import static com.company.Main.generateRandomNumber;

public class Server {
    int portNumber;
    Socket socket;
    private OnMessage onMessage;
    private String inputLine;
    private String outputLine;
    private boolean stop;
    private ServerSocket serverSocket;

    public Server(int portNumber) {
        this.portNumber = portNumber;
    }

    public void setOnMessageListener(OnMessage onMessageListener) {
        this.onMessage = onMessageListener;
    }

    public void start() {
        stop = false;
        try {
            serverSocket = new ServerSocket(portNumber);
            socket = serverSocket.accept();
            System.out.println("A new client is connected : " + socket);

            PrintWriter out =
                    new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

                Thread t = new ClientHandler(socket,out, in);
                t.start();
        }
        catch (Exception e){
            start();
        }
    }

    public void stop() {
    }

}