package com.practice.socket.protocol2;

import java.io.*;
import java.net.Socket;

/**
 * Client SendThread Class - 서버로 메시지를 전송하는 스레드 클래스
 */
public class ClientSend extends Thread {
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
                //콘솔로부터 메시지를 읽음
                sendString = bufferedReader.readLine();
                if (sendString.equals("exit")) {
                    //만약 "exit"를 입력하면 반복문을 종료
                    break;
                }

                //메시지를 서버로 전송
                printWriter.println(sendString);
                //출력 버퍼를 비움
                printWriter.flush();
            }

            //자원 해제 - bufferedReader, printWriter, socket close
            bufferedReader.close();
            printWriter.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 소켓을 설정하는 메서드
    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
