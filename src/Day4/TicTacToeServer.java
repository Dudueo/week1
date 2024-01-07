package Day4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TicTacToeServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("서버 시작. 클라이언트 연결 대기 중...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("클라이언트가 연결되었습니다: " + socket);

                // 서버 측 게임 로직을 실행하는 부분
                Main game = new Main(socket);
                Thread gameThread = new Thread(game);
                gameThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}