package Day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main implements Runnable {
    Thread thread;
    Socket socket;
    BufferedReader reader;
    PrintWriter writer;
    public Main(Socket socket)throws IOException{
        thread=new Thread(this);
        this.socket=socket;
        reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        String[][] grid=new String[3][3];
        int time=0;
    }
    class TicTacToe {
        String[][] grid=new String[3][3];
        int time; //턴 체크 변수
    }
   @Override
    public void run() {
       TicTacToe Game=new TicTacToe();
       Game.time=0; // 처음 턴 수는 0으로 초기화
       set_game(Game.grid);
       while(End(Game.grid)==0){
           set_turn(Game.time,Game.grid);
           Game.time++;
           show_grid(Game.grid);//그리드를 출력한다
           if(End(Game.grid)==1){//o가 이긴다면
               System.out.println("o가 이겼습니다!");
               break;
           }else if(End(Game.grid)==2){//x가 이긴다면
               System.out.println("x가 이겼습니다!");
               break;
           }else if(Game.time==9){
               System.out.println("비겼습니다!");
               break;
           }
       }
   }
    private static void show_grid(String[][] grid) {//그리드 메서드
        for (int i = 0; i < 3; i++) {
            for (int j= 0; j <3  ; j++) {
                System.out.printf("%s",grid[i][j]);
            }
            System.out.println();
        }
    }
    private static void set_game(String[][] grid) {//grid의 메모리 주소를 넣을 것
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = "[ ]";
            }
        }
    }
    private static void set_turn(int time,String[][] grid) {//틱택톡을 두는 경우
        Scanner scanner=new Scanner(System.in);
        int x,y;//좌표 저장
        while(true){
            System.out.println("어디에 두시겠습니까? x,y좌표값을 1~3사이의 정수로 공백을 두고 입력해 주세요");
            Scanner sc=new Scanner(System.in);
            x=scanner.nextInt();
            y=scanner.nextInt();

            if(!Can_set_trun(x,y,grid)){//만약에 그자리에 둘수 없다면 continue로 다시 돌린다는 뜻
                continue;
            }if(time%2==1){ //둘수 있는데 홀수번째 턴이라면 o
                grid[x-1][y-1]="[o]";
            }
            else{
                grid[x-1][y-1]="[x]";//짝수번쨰 턴이라면 x
            }
            break;
        }
    }
    private static boolean Can_set_trun(int x, int y, String[][] grid) {//놓을 수 있는지 체크해주는 메서드

            if (x < 0 || x > 3 || y < 0 || y > 3) {//범위 밖일때
                System.out.println("이 위치에는 놓을 수 없습니다.");
                return false;
            }
            if (!grid[x - 1][y - 1].equals("[ ]")) {
                System.out.println("이 위치는 이미 놓여진 자리입니다.");
                return false;
            }
            return true;

    }
    private static int End(String[][] grid) {//게임이 끝났는지 확인해 주는 gird
        for (int i = 0; i < 3; i++) {
            if(grid[i][0].equals(grid[i][1]) && grid[i][1].equals(grid[i][2]) && grid[i][0].equals("[o]")){
                return 1;
            }
            if(grid[0][i].equals(grid[1][i]) && grid[1][i].equals(grid[2][i]) && grid[0][i].equals("[o]")){
                return 1;
            }
            if(grid[i][0].equals(grid[i][1]) && grid[i][1].equals(grid[i][2]) && grid[i][0].equals("[x]")){
                return 2;
            }
            if(grid[0][i].equals(grid[1][i]) && grid[1][i].equals(grid[2][i]) && grid[0][i].equals("[x]")){
                return 2;
            }
        }
        return 0;
    }
    }
