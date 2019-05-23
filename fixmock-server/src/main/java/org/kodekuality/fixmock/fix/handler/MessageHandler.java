package org.kodekuality.fixmock.fix.handler;

import org.kodekuality.fixmock.fix.session.RawFixMessage;

public interface MessageHandler {
    void handle (RawFixMessage message);
}
