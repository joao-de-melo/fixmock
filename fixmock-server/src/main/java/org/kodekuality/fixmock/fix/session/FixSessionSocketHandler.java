package org.kodekuality.fixmock.fix.session;

import java.io.IOException;
import java.net.Socket;

public class FixSessionSocketHandler implements Runnable {
    private final Socket accept;
    private final FixMessageParser parser;

    public FixSessionSocketHandler(Socket accept, FixMessageParser parser) {
        this.accept = accept;
        this.parser = parser;
    }

    @Override
    public void run() {
        try {
            parser.parse(accept.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
