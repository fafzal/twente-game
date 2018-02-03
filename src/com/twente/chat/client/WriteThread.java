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
            System.out.println("<" + userName + "> ");
            text = scanner.nextLine();
            sendCommand(scanner, text);

        } while (!text.equals("bye"));

        try {
            socket.close();
        } catch (IOException ex) {

            System.out.println("Error writing to server: " + ex.getMessage());
        }
    }

    private void sendCommand(Scanner scanner, String text) {

        if (text.equals("hello")) {
            writer.println(text);
        } else if (text.equals("start")) {
            writer.println(text);
        } else if (text.contains("move")) {
            writer.println(text);
        } else if (text.contains("player_left")) {
            writer.println(text);
        } else {
            writer.println(text);
        }
    }
}