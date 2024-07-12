package com.practice.socket.protocol2;

import java.io.IOException;
import java.net.*;

/**
 * Server Class
 * 클라이언트의 연결을 수락하고,
 * 각 클라이언트마다 ServerReceive와 ServerSend 스레드를 생성하여 데이터 송수신을 담당
 */
public class Server2 {

    public static void main(String[] args) {
        try {
            //서버 소켓을 포트 번호 1111로 생성
            ServerSocket serverSocket = new ServerSocket(1111);

            while (true) {
                //클라이언트의 연결 대기.
                //연결이 수립되면 클라이언트와의 통신을 담당할 소켓을 생성
                Socket socket = serverSocket.accept();

                //각 클라이언트에 대해 데이터를 수신하는 ServerReceive 객체 생성
                ServerReceive serverReceive = new ServerReceive();
                    serverReceive.setSocket(socket);

                //데이터를 전송하는 ServerSend 객체를 생성
                ServerSend serverSend = new ServerSend();
                    serverSend.setSocket(socket);

                //각각의 스레드를 시작하여 클라이언트와의 실시간 데이터 송수신을 관리
                serverReceive.start();
                serverSend.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
