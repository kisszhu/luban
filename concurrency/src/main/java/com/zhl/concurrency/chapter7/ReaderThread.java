package com.zhl.concurrency.chapter7;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * ReaderThread管理了一个套接字连接，它采用同步方式从该套接字中读取数据，并将接收到的数据
 * 传递给processBuffer。为了结束某个用户的连接或者关闭服务器，ReaderThread改写了interrupt方法，
 * 使其既能处理标准的中断，也能关闭底层的套接字。
 */
public class ReaderThread extends Thread {
    private final Socket socket;
    private final InputStream in;

    public ReaderThread(Socket socket) throws IOException {
        this.socket = socket;
        this.in = socket.getInputStream();
    }

    @Override
    public void interrupt() {
        try {
            socket.close();
        } catch (IOException ignored) {

        } finally {
            super.interrupt();
        }
    }

    @Override
    public void run() {
        try {
            byte[] buf = new byte[1024];
            while (true) {
                int count = in.read(buf);
                if (count < 0) {
                    break;
                } else if (count > 0) {
                    processBuffer(buf, count);
                }
            }
        } catch (IOException e) {
            /** 允许线程退出 */
        }
    }

    public void processBuffer(byte[] buf, int count) {

    }
}
