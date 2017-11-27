package com.fredericboisguerin.insa.network.core.service;

import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPMessageSenderService implements MessageSenderService {
    private Socket chatSocket;
    private PrintWriter writer;
    @Override
    public void sendMessageOn(String ipAddress, int port, String message) throws Exception {
        chatSocket = new Socket(ipAddress, port);

        writer = new PrintWriter(chatSocket.getOutputStream());
        writer.println(message);
        writer.close();
    }

}
