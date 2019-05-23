package org.kodekuality.fixmock.fix.handler;

import org.kodekuality.fixmock.fix.mapping.MappingService;

import java.net.Socket;

public class MessageHandlerFactory {
    private final MappingService mappingService;

    public MessageHandlerFactory(MappingService mappingService) {
        this.mappingService = mappingService;
    }

    public MessageHandler create(Socket accept) {
        return new DefaultMessageHandler(accept, mappingService);
    }
}
