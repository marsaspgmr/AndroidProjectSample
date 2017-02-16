package com.example.marco.androidprojectsample;

import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Marco on 06/02/2017.
 */

public class SimpleClient {
    private String serverMessage;
    private boolean mRun;
    private MonitorMessage monitorMessage = null;
    public static final String DEF_SERVERIP = "192.168.1.102"; //Server IP default address
    public static final int DEF_SERVERPORT = 9999; ////Server port default address
    public static final int MAX_TRIES_TO_CONNECT = 3;
    public static final int TIMEOUT = 10000;
    private String ip = null;
    private int port = 0;
    BufferedReader in;
    private Socket socket;


    //constructors
    public SimpleClient(String ip, Integer port, MonitorMessage monitorMessage) {
        this.ip = ip;
        this.port = port;
        this.monitorMessage = monitorMessage;
    }

    public SimpleClient(MonitorMessage monitorMessage){
        setIp(DEF_SERVERIP);
        setPort(DEF_SERVERPORT);
        this.monitorMessage = monitorMessage;
    }

    //methods
    public void stopClient(){
        mRun = false;
    }

    //return 100 if connection is successfull
    public Socket run(){

        mRun = true;
        try {
            InetAddress serverAddr = InetAddress.getByName(ip);
            Log.e("SimpleClient", "Connecting...");

            socket = new Socket(serverAddr, port);
            socket.setSoTimeout(TIMEOUT);
            try {
                //receive the message which the server sends back
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                //in this while the client listens for the messages sent by the server
                while (mRun) {
                    serverMessage = in.readLine();

                    if (serverMessage != null && monitorMessage != null) {
                        //call the method messageReceived from MyActivity class
                        monitorMessage.returnMessage(serverMessage);
                    }
                    serverMessage = null;

                }
            } catch (Exception e) {

                Log.e("TCP", "S: Error", e);
                monitorMessage.returnMessage(e.toString());
            } finally {
                //the socket must be closed. It is not possible to reconnect to this socket
                // after it is closed, which means a new socket instance has to be created.
                socket.close();
            }

        } catch (Exception e) {
            Log.e("TCP", "S: Error", e);
            monitorMessage.returnMessage(e.toString());
        }
        return socket;
    }



    //getters and setters
    public void setPort(int port) {
        this.port = port;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
