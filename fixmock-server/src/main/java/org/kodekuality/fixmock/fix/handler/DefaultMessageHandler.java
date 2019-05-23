package org.kodekuality.fixmock.fix.handler;

import org.kodekuality.fixmock.fix.mapping.MappingService;
import org.kodekuality.fixmock.fix.session.RawFixMessage;

import java.net.Socket;

public class DefaultMessageHandler implements MessageHandler {
    private final Socket socket;
    private final MappingService mappingService;

    public DefaultMessageHandler(Socket socket, MappingService mappingService) {
        this.socket = socket;
        this.mappingService = mappingService;
    }

    @Override
    public void handle(RawFixMessage message) {
        if (!mappingService.handle(socket, message)) {
            System.err.println(String.format("No mapping found for message:%n%s", message.asString()));
        }
    }
}
