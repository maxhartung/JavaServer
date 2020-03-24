package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import static com.company.Main.generateRandomNumber;

public class ClientHandler extends Thread {
    PrintWriter pw;
    BufferedReader br;
    Socket s;

    public ClientHandler(Socket s, PrintWriter pw, BufferedReader br) {
        this.pw = pw;
        this.br = br;
        this.s = s;
    }

    @Override
    public void run() {
        super.run();
        String in = "";
        String inputLine = "";
        int numberOfTries = 1;
        try {
            while ((inputLine = br.readLine()) != null) {
                int i = generateRandomNumber(1, 100);
                System.out.println("Try no. " + numberOfTries);
                //  System.out.println("------------------------");
                System.out.println("Client: " + inputLine);
                System.out.println("Server: " + i);
                System.out.println("------------------------");
                numberOfTries++;

                int clientResponseNumber = Integer.parseInt(inputLine);

                if (clientResponseNumber == i) {
                    pw.println("The number is correct !");
                } else if (clientResponseNumber > i) {
                    pw.println("The number is smaller !");
                } else if (clientResponseNumber < i) {
                    pw.println("The number is bigger !");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Client sent an invalid number");
        } catch (IOException e) {
            System.out.println("Reading the data failed !");
        }

    }
}
