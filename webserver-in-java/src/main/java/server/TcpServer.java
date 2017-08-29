package server;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(8080);
             FileOutputStream fos = new FileOutputStream("server_recv.txt");
             FileInputStream fis = new FileInputStream("server_send.txt")) {

            System.out.println("クライアントからの接続を待ちます。");
            Socket socket = server.accept();
            System.out.println("クライアント接続。");

            int ch;

            // クライアントから受け取った内容を、server_recv.txtに出力
            // クライアントは、終了のマークとして0を送付してくる。
            InputStream input = socket.getInputStream();
            while ((ch = input.read()) != 0) {
                fos.write(ch);
            }
            fos.flush();

            // server_send.txtの内容を、クライアントへ送付
            OutputStream output = socket.getOutputStream();
            while ((ch = fis.read()) != -1) {
                output.write(ch);
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
        サーバは、まず8080番ポートで待ち受けるために ServeSocket クラスのインスタンスを作成。
        そして ServerSocket#accept メソッドにより、クライアントからの接続を待ちます。
        (この時、main メソッドは accept メソッドによりブロックされる。）

        クライアントからの接続があれば、acceptメソッドのブロッキングが解除されて Socket を取得できます。
        後は、Socket から入出力ストーリムを取得して、双方向通信を行います。

     */
}
