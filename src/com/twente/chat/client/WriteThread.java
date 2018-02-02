package com.twente.chat.client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class WriteThread extends Thread {
    private PrintWriter writer;
    private Socket socket;
    private ChatClient client;

    public WriteThread(Socket socket, ChatClient client) {
        this.socket = socket;
        this.client = client;

        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch (IOException ex) {
            System.out.println("Error getting output stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void run() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your name: ");

        String userName = scanner.nextLine();

        client.setUserName(userName);
        writer.println(userName);

        String text;

        do {
            System.out.println("[" + userName + "]: ");
            System.out.println("Enter command: ");
            text = scanner.nextLine();
            switch (text) {

                case "hello":
                    writer.println(text);
                    break;

                case "start":
                    writer.println(text);
                    break;

                case "move":
                    text = scanner.nextLine();
                    writer.println("move," + text);
                    break;

                case "player_left":
                    writer.println(text);
                    break;
            }
            writer.println(text);

        } while (!text.equals("bye"));

        try {
            socket.close();
        } catch (IOException ex) {

            System.out.println("Error writing to server: " + ex.getMessage());
        }
    }
}