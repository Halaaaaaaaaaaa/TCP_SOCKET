package com.practice.socket.protocol2;

import java.io.IOException;
import java.net.*;

/**
 * Client Class
 */
public class Client2 {

    public static void main(String[] args) {
        try {
            //서버와의 연결을 위해 host와 port로 소켓 생성
            Socket socket = new Socket("localhost", 1111);

            //서버로부터 메시지 수신하는 clientReceive 객체 생성하고, 소켓 설정
            ClientReceive clientReceive = new ClientReceive();
                clientReceive.setSocket(socket);

            //서버로 메시지 전송하는 clientSend 객체 생성하고, 소켓 설정
            ClientSend clientSend = new ClientSend();
                clientSend.setSocket(socket);

            //서버로 메시지 송수신을 위한 스레드를 시작
            clientSend.start();
            clientReceive.start();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
