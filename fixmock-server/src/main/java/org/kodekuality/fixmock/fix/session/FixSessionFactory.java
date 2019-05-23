package org.kodekuality.fixmock.fix.session;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.UUID;

public class FixSessionFactory {
    private final FixSessionSocketHandlerFactory socketHandlerFactory;

    public FixSessionFactory(FixSessionSocketHandlerFactory socketHandlerFactory) {
        this.socketHandlerFactory = socketHandlerFactory;
    }

    public ThreadFixSession create (FixSessionConfiguration configuration) {
        try {
            ServerSocket serverSocket = new ServerSocket(
                    configuration.getPort().orElse(0),
                    -1,
                    configuration.getAddress().orElse(InetAddress.getLoopbackAddress())
            );

            ThreadFixSession fixSession = new ThreadFixSession(
                    UUID.randomUUID().toString(), serverSocket, socketHandlerFactory
            );
            fixSession.start();
            return fixSession;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
