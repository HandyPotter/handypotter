package com.example.and2server;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler();

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView)findViewById(R.id.voice2text);
        Button sendBtn = (Button)findViewById(R.id.send);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClientThread thread = new ClientThread();
                thread.start();
            }
        });

    }

    //서버로 보낼 텍스트 데이터
    String sendMsg = "나는 달리기를 하다가 다쳐서 병원에 갔고, 아버지와 어머니가 걱정이 되어 병원에 방문하였다";

    class ClientThread extends Thread {
        public void run() {
            int port = 9999;
            String host = "3.18.113.12";

            try {

                Socket client = new Socket();
                // 소켓에 접속하기 위한 접속 정보를 선언한다.
                InetSocketAddress ipep = new InetSocketAddress(host, port);
                // 소켓 접속!
                client.connect(ipep);

                // 소켓이 접속이 완료되면 inputstream과 outputstream을 받는다.

                OutputStream sender = client.getOutputStream();
                InputStream receiver = client.getInputStream();

                try {

                    String msg = "java test message - " + sendMsg;
                    // string을 byte배열 형식으로 변환한다.
                    byte[] data = msg.getBytes();
                    // ByteBuffer를 통해 데이터 길이를 byte형식으로 변환한다.
                    ByteBuffer b = ByteBuffer.allocate(4);
                    // byte포멧은 little 엔디언이다.
                    b.order(ByteOrder.LITTLE_ENDIAN);
                    b.putInt(data.length);
                    // 데이터 길이 전송
                    sender.write(b.array(), 0, 4);
                    // 데이터 전송
                    sender.write(data);

                    data = new byte[4];
                    // 데이터 길이를 받는다.
                    receiver.read(data, 0, 4);
                    // ByteBuffer를 통해 little 엔디언 형식으로 데이터 길이를 구한다.
                    ByteBuffer getb = ByteBuffer.wrap(data);
                    getb.order(ByteOrder.LITTLE_ENDIAN);
                    int length = getb.getInt();
                    // 데이터를 받을 버퍼를 선언한다.
                    data = new byte[length];
                    // 데이터를 받는다.
                    receiver.read(data, 0, length);

                    // byte형식의 데이터를 string형식으로 변환한다.
                    msg = new String(data, "UTF-8");

                    // 수화 아이디!
                    System.out.println("수화 아이디 값 : " + msg);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


