package client;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TcpClient {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 80);
             FileOutputStream fos = new FileOutputStream("client_recv.txt");
             FileInputStream fis = new FileInputStream("client_send.txt")) {

            int ch;

            // client_send.txtの内容をサーバに送信
            OutputStream output = socket.getOutputStream();
            while ((ch = fis.read()) != -1){
                output.write(ch);
            }

            // 終了を示すため、0を送信
            //output.write(0);

            // サーバからの返信を、client_recv.txtに出力
            InputStream input = socket.getInputStream();
            while((ch = input.read()) != -1){
                fos.write(ch);
            }
            fos.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
        クライアントは、localhostの8080番ポートを対象に Socket を生成します。
        これによりサーバ側では、acceptメソッドの待ちが解除され、戻り値として Socket を受け取ります。
        以降は、Socket から取得した入出力ストリームによって双方向通信が可能になります。

     */
}
