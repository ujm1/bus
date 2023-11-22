package busProject;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class BusClient extends Thread {

    final static String SERVER_IP = "172.30.1.10";
    final static int SERVER_PORT = 36464;


    public static void main(String[] args) {
        Socket socket = null;
        ObjectOutputStream oos = null;

        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            System.out.println("socket 연결");
            oos = new ObjectOutputStream(socket.getOutputStream());
            Bus bus100 = new Bus(100, 50);
            while (true) {


           bus100.setTime(bus100.getTime() - 1);

                System.out.println(bus100.getNumber()+"번 버스가 정류장에 " +
                        "도착하기까지 " +
                        "남은 시간: "+ bus100.getTime());
                if(bus100.getTime()<=0) {
                    System.out.println("버스가 지나갔습니다.");
                    bus100.setTime(50);}
                oos.writeObject(bus100);
                oos.flush();          Thread.sleep(1000);
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } finally {
            try {
                oos.close();
                socket.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    } //main
} //class
