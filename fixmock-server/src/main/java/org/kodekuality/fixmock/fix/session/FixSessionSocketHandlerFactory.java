package org.kodekuality.fixmock.fix.session;

import org.kodekuality.fixmock.fix.handler.MessageHandler;
import org.kodekuality.fixmock.fix.handler.MessageHandlerFactory;

import java.net.Socket;

public class FixSessionSocketHandlerFactory {
    private final MessageHandlerFactory messageHandlerFactory;

    public FixSessionSocketHandlerFactory(MessageHandlerFactory messageHandlerFactory) {
        this.messageHandlerFactory = messageHandlerFactory;
    }

    public FixSessionSocketHandler create(Socket accept) {
        return new FixSessionSocketHandler(accept, new FixMessageParser(messageHandlerFactory.create(accept)));
    }
}
