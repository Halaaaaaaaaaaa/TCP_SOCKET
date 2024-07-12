package com.practice.socket.protocol2;

import java.io.*;
import java.net.Socket;

/**
 * Server SendThread Class
 * 클라이언트로 데이터를 전송하는 스레드 클래스
 */
public class ServerSend extends Thread{

    private Socket socket;

    @Override
    public void run() {
        super.run();

        try {
            //콘솔에서 입력을 받기 위한 BufferedReader를 생성
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            //소켓의 출력 스트림을 통해 데이터를 전송하기 위한 PrintWriter를 생성
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            String sendString;

            while (true) {
                //콘솔로부터 메시지 일어
                sendString = bufferedReader.readLine();
                //읽어들인 메시지를 클라이언트로 전송
                printWriter.println(sendString);
                //출력 버퍼를 비워
                printWriter.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //소켓을 설정하는 메서드
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

}
