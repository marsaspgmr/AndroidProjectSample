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
    public static final String DEF_SERVERIP = "192.168.1.102"; //Server IP default address
    public static final int DEF_SERVERPORT = 9999; ////Server port default address
    private String ip = null;
    private int port = 0;
    private boolean mRun = false;
    BufferedReader in;


    //constructors
    public SimpleClient(String ip, Integer port) {
        this.ip = ip;
        this.port = port;
    }

    public SimpleClient(){
        setIp(DEF_SERVERIP);
        setPort(DEF_SERVERPORT);
    }

    //methods
    public void run(){

        try {
            InetAddress serverAddr = InetAddress.getByName(ip);
            Log.e("SimpleClient", "Connecting...");

            Socket socket = new Socket(serverAddr, port);
            try {
                //receive the message which the server sends back
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                //in this while the client listens for the messages sent by the server
                serverMessage = in.readLine();
                Log.e("RESPONSE FROM SERVER", "S: Received Message: '" + serverMessage + "'");

            } catch (Exception e) {

                Log.e("TCP", "S: Error", e);

            } finally {
                //the socket must be closed. It is not possible to reconnect to this socket
                // after it is closed, which means a new socket instance has to be created.
                socket.close();
            }

        } catch (Exception e) {

            Log.e("TCP", "C: Error", e);

        }
    }



    //getters and setters
    public void setPort(int port) {
        this.port = port;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
