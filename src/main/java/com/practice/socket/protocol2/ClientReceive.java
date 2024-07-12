package com.practice.socket.protocol2;

import java.io.*;
import java.net.Socket;

/**
 * Client ReceiveThread Class - 서버로부터 메시지 수신하는 스레드 클래스
 */
public class ClientReceive extends Thread {

    private Socket socket;

    @Override
    public void run() {
        super.run();

        try {
            //소켓의 입력 스트림을 통해 데이터를 읽기 위한 BufferedReader를 생성
            /* BufferedReader는 java에서 입력 스트림을 효율적으로 읽기 위해 사용하는 클래스
                주로 파일, 네트워크 소켓 등에서 텍스트 읽을 때 사용하고,
                내부적으로 버퍼를 사용하여 입력을 버퍼링하여 이를 통해 여러 번의 작은 읽기 작업을 최소화하고 성능을 향상 */
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String receiveString;

            while (true) {
                //서버로부터 메시지를 읽고 콘솔에 출력
                //BufferedReader는 readLine() 메서드를 제공하여 한 줄씩 읽는 기능을 제공
                //데이터를 한 줄씩 읽을 때 유용
                receiveString = bufferedReader.readLine();
                System.out.println("[Connected] Server said | " + receiveString);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //소켓을 설정하는 메서드
    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
