package com.fredericboisguerin.insa.network.core.service;

import javax.naming.ldap.StartTlsRequest;
import javax.sql.DataSource;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class UDPMessageReceiverService implements MessageReceiverService,Runnable {

    private byte[] dataReception;
    private DatagramSocket datagramSocket;
    private DatagramPacket datagramPacket;
    private IncomingMessageListener monincomingMessageListener;
    @Override
    public void listenOnPort(int port, IncomingMessageListener incomingMessageListener) throws Exception {


        byte[] buffer = new byte[100];
        this.dataReception = new byte[100];


        this.datagramSocket = new DatagramSocket(port);
        this.datagramPacket = new DatagramPacket(buffer, 100);
        this.monincomingMessageListener = incomingMessageListener;
        new Thread(()-> run()).start();

        //throw new UnsupportedOperationException();
    }
    public void affichagemessage(){
        this.dataReception = datagramPacket.getData();
        String chainerecue = new String(dataReception);
        monincomingMessageListener.onNewIncomingMessage(chainerecue);
    }
    @Override
    public void run() {
        boolean threadreceptionactive = true;
        while (threadreceptionactive) {
            try {
                this.datagramSocket.receive(this.datagramPacket);
                this.affichagemessage();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
    }
}
