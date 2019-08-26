package com.zhl.concurrency.chapter6;

//import sun.misc.Request;

/**
 * 支持关闭操作的Web服务器.
 */
public class LifecycleWebServer {

//    private final ExecutorService exec = Executors.newFixedThreadPool(10);
//
//    public void start() throws IOException {
//        ServerSocket socket = new ServerSocket(80);
//        while (!exec.isShutdown()) {
//            try {
//                final Socket conn = socket.accept();
//                exec.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        handleRequest(conn);
//                    }
//                });
//            } catch (RejectedExecutionException e) {
//                if (!exec.isShutdown()) {
//                    System.out.println("task submission rejected");
//                }
//            }
//        }
//    }
//
//    public void stop() {
//        exec.shutdown();
//    }
//
//    void handleRequest(Socket connection) {
//        if (isShutdownRequest(connection)) {
//            stop();
//        } else {
//            // .....
//        }
//    }
//
//    boolean isShutdownRequest(Socket connection) {
//        // .....
//        return true;
//    }
}
