package org.kodekuality.fixmock.fix.mapping.steps;

import org.kodekuality.fixmock.fix.session.RawFixMessage;

public class SendMessageFixStep implements FixStep {
    private final RawFixMessage message;

    public SendMessageFixStep(RawFixMessage message) {
        this.message = message;
    }

    public RawFixMessage getMessage() {
        return message;
    }
}
