package busProject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class BusServer extends Thread {

    public static void main(String[] args) {
        ServerSocket ss = null;
        Socket socket = null;
        InputStreamReader isr=null;
        BufferedReader   br=null;

        try {
            ss = new ServerSocket(36464);
            if (ss != null) {
                System.out.println("버스 서버 준비됨");
            }
            BusGUI busGUI=new BusGUI();
            socket = ss.accept();
            System.out.println(socket.getInetAddress()+"연결");
            isr=new InputStreamReader((socket.getInputStream()));
            br = new BufferedReader(isr);


            while (true) {
                String fromClient=br.readLine();
                System.out.println(fromClient);
                busGUI.topDisplay(fromClient);
                //이렇게 그냥 보내는게 아니라 List같은걸로 넣은담에 [0]삭제하고 보내기?
                Thread.sleep(1);
                String fromClient2=br.readLine();
                System.out.println(fromClient2);
                Thread.sleep(1);
                busGUI.bottomDisplay(fromClient2);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (InterruptedException ie) {ie.printStackTrace();}
            finally {
            try {
                br.close();
                isr.close();
                socket.close();
                ss.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

    }


}
