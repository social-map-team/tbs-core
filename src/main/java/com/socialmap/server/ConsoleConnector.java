package com.socialmap.server;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.socialmap.server.share.SosRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by yy on 3/7/15.
 */
@Component
public class ConsoleConnector {
    public static final Logger log = LogManager.getLogger();
    private ConsoleInfo consoleInfo;
    //@Value("${port.listening.gui}")
    private Integer guiListeningPort = 19674;
    private ObjectMapper mapper = new ObjectMapper();
    private Thread sos = new Thread(new Runnable() {
        @Override
        public void run() {

        }
    });

    public ConsoleConnector() {
        // 启动一个在9090端口监听的线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    log.info("将要在本地9091端口监听");
                    ServerSocket listener = new ServerSocket(guiListeningPort);
                    while (true) {
                        Socket console;
                        log.info("等待请求的到来");
                        console = listener.accept();
                        try {
                            //consoleInfo = mapper.readValue(is, ConsoleInfo.class);
                            String str = new DataInputStream(console.getInputStream()).readUTF();
                            consoleInfo = mapper.readValue(str, ConsoleInfo.class);
                        } catch (JsonGenerationException e) {
                            e.printStackTrace();
                        } catch (JsonMappingException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        consoleInfo.address = console.getInetAddress().getHostAddress();
                        System.out.println("控制台SOS端口：" + consoleInfo.sosPort);
                        System.out.println("控制台IP：" + consoleInfo.address);
                        DataOutputStream dos = new DataOutputStream(console.getOutputStream());
                        dos.writeUTF("服务器已经知晓控制台相关信息");
                        console.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static class ConsoleInfo {
        public String address;
        public int    sosPort;
    }

    public boolean publishSos(final SosRequest request) {
        try {
            Socket socket = new Socket(consoleInfo.address, consoleInfo.sosPort);
            try {
                StringWriter writer = new StringWriter();
                mapper.writeValue(writer, request);
                new DataOutputStream(socket.getOutputStream()).writeUTF(writer.toString());
                String response = new DataInputStream(socket.getInputStream()).readUTF();
                if (response.equals("已处理")) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
