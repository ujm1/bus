package busProject;

import java.io.*;
import java.net.Socket;

public class BusClientLogic {
    final static String SERVER_IP = "172.30.1.10";
    final static int SERVER_PORT = 36464;

    Bus bus;
    Socket socket;
    OutputStream os = null;
    OutputStreamWriter osw = null;

    BusClientLogic() {
    }

    BusClientLogic(Bus bus) {
        this.bus = bus;
    }

    void check() {
        try {   socket = new Socket(SERVER_IP, SERVER_PORT);
            System.out.println("socket 연결");
            os = socket.getOutputStream();
            osw = new OutputStreamWriter(os);
            while (true) {

                bus.setTime(bus.getTime() - 1);
                String str = null;
                if (bus.getTime() > 0) {

                    str = bus.getNumber() + "번 버스가 정류장에 " +
                            "도착하기까지 " +
                            "남은 시간: " + bus.getTime() / 60 + "분 "
                            + bus.getTime() % 60 + "초";

                    if(bus.getTime()/60==0) {

                    }
                }
                 else if (bus.getTime() < 0) {
                    str = "버스가 지나갔습니다";
                    bus.setTime(200);
                }
                osw.write(str);
                osw.flush();
                System.out.println(str);
                Thread.sleep(1000);
               

            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } finally {
            try {
                osw.close();
                os.close();
                socket.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
