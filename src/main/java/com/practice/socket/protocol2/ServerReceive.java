package com.practice.socket.protocol2;

import java.io.*;
import java.net.Socket;

/**
 * Server ReceiveThread Class
 * 클라이언트로부터 데이터를 수신하는 스레드 클래스
 */
public class ServerReceive extends Thread {

    private Socket socket;

    @Override
    public void run() {

        super.run();

        try {
            //소켓의 입력 스트림을 통해 데이터를 읽기 위한 BufferedReader를 생성
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String receiveString;

            while (true) {
                //클라이언트로부터 메시지를 읽어
                receiveString = bufferedReader.readLine();
                if (receiveString == null) {
                    //클라이언트가 연결을 종료한 경우
                    System.out.println("[Disconnected]");
                    socket.close();
                    break;
                } else {
                    //클라이언트로부터 받은 메시지를 콘솔에 출력
                    System.out.println("[Connected] | " + receiveString);
                }
            }

            /*
            Server2 클래스의 main 메서드를 호출. 이는 새로운 서버 인스턴스를 시작하게 된다.
            이 때문에 클라이언트가 연결을 끊은 후에도 서버가 다시 시작되어 다른 클라이언트의 연결을 기다릴 수 있다.
             */
            Server2.main(null);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //소켓을 설정하는 메서드
    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
