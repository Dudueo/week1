package Day4;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TicTacToeClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
            System.out.println("서버에 연결되었습니다.");

            // 클라이언트 측 게임 로직 실행
            Main clientGame = new Main(socket);
            Thread clientGameThread = new Thread(clientGame);
            clientGameThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}