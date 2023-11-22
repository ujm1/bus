package busProject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BusServer extends Thread {

    public static void main(String[] args) {
        ServerSocket ss = null;
        Socket socket = null;
        ObjectInputStream ois = null;
        Bus bus = null;
        try {
            ss = new ServerSocket(36464);
            if (ss != null) {
                System.out.println("버스 서버 준비됨");
            }
            socket = ss.accept();
            System.out.println("접속 버스 아이피: " + socket.getInetAddress());

            ois = new ObjectInputStream(socket.getInputStream());

            while (true) {
                bus=(Bus) ois.readObject();
                System.out.println(bus.getTime());
                int minute = (bus.getTime()) / 60;
                int second = (bus.getTime()) % 60;
                    if (bus.getTime() > 0) {
                        System.out.println(bus.getNumber() + "번 버스가 " + minute + "분 "
                                + second + "초 후에 도착 예정입니다");
                    } else if (bus.getTime() == 0) {
                        System.out.println(bus.getNumber() + "번 버스가 도착하였습니다");
                    } else if (bus.getTime() < 0) {
                        System.out.println(bus.getNumber() + "번 버스가 정류장을 지나갔습니다");
                    }
                    Thread.sleep(1000);


            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } finally {
            try {
                ois.close();
                socket.close();
                ss.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

    }


}
