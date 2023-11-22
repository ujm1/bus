package busProject;

import java.io.*;
import java.net.Socket;

public class BusClient extends Thread {
    final static String SERVER_IP = "172.30.1.10";
    final static int SERVER_PORT = 36464;
    final static int SECOND=5;

    static void check(int number, int time) {
            Socket socket = null;
            BufferedWriter bw = null;

            try {
                socket = new Socket(SERVER_IP, SERVER_PORT);
                System.out.println("socket 연결");
                bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                Bus bus = new Bus(number, time);
                while (true) {

                    bus.setTime(bus.getTime() - SECOND);
                    String str1 = null;
                    String str2 = "";
//                    int almostBusNum=0;
                    if (bus.getTime() > 0) {

                        str1 = bus.getNumber() + "번 버스가 정류장에 " +
                                "도착하기까지 " +
                                "남은 시간: " + bus.getTime() / 60 + "분 "
                                + bus.getTime() % 60 + "초";
                        if(bus.getTime()/60==0) {str2 =bus.getNumber()+"번";}

                    } else if (bus.getTime() == 0) {
                        str1 = bus.getNumber()+"번 버스가 도착했습니다";
                    }

                    else if (bus.getTime() < 0) {
                        str1 = bus.getNumber()+"번 버스가 지나갔습니다";
                        bus.setTime(200);
                    }
                    bw.write(str1);
//                    if(bus.getTime()/60==0) { bw.write(almostBusNum); }
                    bw.newLine(); //이게 없어서 안된듯?
                    bw.flush();
                    Thread.sleep(1);
                    if(str2!=null) {bw.write(str2); bw.newLine(); bw.flush();}
                    System.out.println(str1);
                   if(str2!=null) {System.out.println(str2);}
                    Thread.sleep(SECOND*1000);
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            } finally {
                try {
                    bw.close();
                    socket.close();

                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }


