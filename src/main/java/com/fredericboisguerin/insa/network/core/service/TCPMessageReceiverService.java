package com.fredericboisguerin.insa.network.core.service;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPMessageReceiverService implements MessageReceiverService {
    private ServerSocket serverSocket;
    private BufferedReader reader;
    private InputStreamReader stream;
    private  IncomingMessageListener monincomingMessageListener;
    private  Socket chatsocket;
    @Override
    public void listenOnPort(int port, IncomingMessageListener incomingMessageListener) throws Exception {
        this.serverSocket = new ServerSocket(port);
        this.monincomingMessageListener = incomingMessageListener;
        this.chatsocket = serverSocket.accept();

        this.stream = new InputStreamReader(chatsocket.getInputStream());
        this.reader = new BufferedReader(this.stream);
        new Thread(() -> run()).start();
    }

        public void run() {
            try {
                boolean threadreceptionactive = true;
                while (threadreceptionactive) {
                    String message = this.reader.readLine();
                    if (message != null) {
                        this.monincomingMessageListener.onNewIncomingMessage(message);
                    }
                }
            }catch(Exception e){
                    e.getStackTrace();
                }
            }
        }

