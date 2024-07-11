package com.practice.socket.protocol1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client1 {

    final static String SERVER_IP = "127.0.0.1";
    final static int SERVER_PORT = 1228;
    final static String MESSEGE_TO_SERVER = "Hello, Server. I'm Client";

    public static void main(String[] args) {

        Socket socket = null;

        try {
            //소켓 통신 시작 - 지정된 ip, port로 서버에 연결
            socket = new Socket(SERVER_IP, SERVER_PORT);
            System.out.println("======================== ProtocolClient Start ========================");

            //client에서 server로 보내기 위한 출력 스트림(통로) 생성
            OutputStream os = socket.getOutputStream();
            //server에서 보낸 값을 받기 위한 입력 스트림(통로) 생성
            InputStream is = socket.getInputStream();

            //서버로 메시지 전송
            os.write(MESSEGE_TO_SERVER.getBytes());
            //출력 스트림을 비우고 데이터 전송 완료를 명확하게 하기 위함
            os.flush();

            //서버로부터의 응답을 저장할 배열 생성
            byte[] data = new byte[32];
            //서버로부터 데이터를 읽고 읽은 바이트 수 반환
            int n = is.read(data);
            //읽은 데이터를 문자열로 변환
            final String resultFromServer = new String(data, 0, n);

            System.out.println("[Result]\t" + resultFromServer + "(result from server)");

            // 소켓을 닫고 연결 종료
            socket.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
