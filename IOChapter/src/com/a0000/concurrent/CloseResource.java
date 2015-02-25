package com.a0000.concurrent;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.a0000.io.utils.Print.*;

/**
 * Created by Administrator on 2015/2/21.
 * Interrupting a blocked task by closing
 * the underlying resource
 * {RunByHand}
 */
public class CloseResource {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        int port = 8080;
        ServerSocket server = new ServerSocket(port);
        InputStream socketInput = new Socket("localhost", port).getInputStream();
        exec.execute(new IOBlocked(socketInput));
        exec.execute(new IOBlocked(System.in));
        TimeUnit.MILLISECONDS.sleep(100);
        print("Shutting down all threads");
        exec.shutdownNow();
        TimeUnit.SECONDS.sleep(1);
        print("Closing " + socketInput.getClass().getSimpleName());
        socketInput.close(); // Releases blocked thread.
        TimeUnit.SECONDS.sleep(1);
        print("Closing " + System.in.getClass().getSimpleName());
        System.in.close(); // Releases blocked thread.
    }
}
