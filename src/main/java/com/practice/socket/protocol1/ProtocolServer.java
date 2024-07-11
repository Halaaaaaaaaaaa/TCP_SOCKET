package com.practice.socket.protocol1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ProtocolServer extends Thread {

    final static int SERVER_PORT = 1228;
    final static String MESSAGE_TO_SERVER = "Hello, Client. I'm Server";

    public static void main(String[] args) {

        ServerSocket serverSocket = null;

        try {
            //지정된 포트 번호로 서버 소켓 생성
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("======================== ProtocolServer Start ========================");

            while (true) {
                System.out.println("==================== Waiting for Socket Connection ====================");

                //클라이언트 연결 요청 대기하다가 연결 요청 수락
                Socket socket = serverSocket.accept();
                System.out.println("[HOST]\t\t" + socket.getInetAddress() + " | Connection Established Successfully");

                //클라이언트와의 데이터 통신을 위한 입력 스트림 생성
                InputStream is = socket.getInputStream();
                //클라이언트와의 데이터 통신을 보내기 위한 출력 스트림 생성
                OutputStream os = socket.getOutputStream();

                //클라이언트로부터의 메시지를 저장할 배열 생성
                byte[] data = new byte[32];
                //클라이언트로부터 데이터를 읽고 바이트 수 반환
                int n = is.read(data);
                //읽은 데이터를 문자열로 반환
                final String messageFromClient = new String(data, 0, n);

                System.out.println("[Message]\t" + messageFromClient + "(message from client)");

                //클라이언트에게 응답 메시지 전송
                os.write(MESSAGE_TO_SERVER.getBytes());
                //출력 스트림을 비우고 데이터 전송 완료
                os.flush();

                //스트림과 소켓을 닫고 연결 종료
                is.close();
                os.close();
                socket.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}