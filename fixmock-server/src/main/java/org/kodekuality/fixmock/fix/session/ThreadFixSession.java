package org.kodekuality.fixmock.fix.session;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadFixSession extends Thread implements FixSession {
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final String id;
    private final ServerSocket serverSocket;
    private final FixSessionSocketHandlerFactory socketHandlerFactory;

    public ThreadFixSession(String id, ServerSocket serverSocket, FixSessionSocketHandlerFactory socketHandlerFactory) {
        this.id = id;
        this.socketHandlerFactory = socketHandlerFactory;
        this.serverSocket = serverSocket;
        setDaemon(true);
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(String.format("Waiting for next connection on %s:%d...", serverSocket.getInetAddress(), serverSocket.getLocalPort()));
                FixSessionSocketHandler handler = socketHandlerFactory.create(serverSocket.accept());
                executorService.submit(handler);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void terminate() {
        executorService.shutdown();
    }

    @Override
    public String getSessionId() {
        return id;
    }

    @Override
    public int getPort() {
        return serverSocket.getLocalPort();
    }
}
