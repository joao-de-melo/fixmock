package org.kodekuality.fixmock.fix.session;

import org.kodekuality.fixmock.fix.handler.MessageHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class FixMessageParser {
    private final FixMessageSequencer sequencer;

    public FixMessageParser(MessageHandler messageConsumer) {
        this.sequencer = new FixMessageSequencer(messageConsumer);
    }

    public void parse(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[128];
        int read = inputStream.read(buffer);
        while (read != -1) {
            for (int i = 0; i < read; i++) {
                sequencer.accept(buffer[i]);
            }
            read = inputStream.read(buffer);
        }
    }

    public static class FixMessageSequencer {
        private final MessageHandler rawMessageConsumer;
        private final AtomicReference<List<RawFixField>> fields = new AtomicReference<>(new ArrayList<>());
        private final AtomicReference<StringBuffer> fieldBuilder = new AtomicReference<>(new StringBuffer());

        public FixMessageSequencer(MessageHandler rawMessageConsumer) {
            this.rawMessageConsumer = rawMessageConsumer;
        }

        public void accept(byte character) {
            if (character == RawFixMessage.FIELD_SPLIT) {
                RawFixField field = RawFixField.parse(fieldBuilder.get().toString());
                fieldBuilder.set(new StringBuffer());
                fields.get().add(field);

                if (field.getTag() == 10) {
                    rawMessageConsumer.handle(new RawFixMessage(fields.get()));
                    fields.set(new ArrayList<>());
                }
            } else {
                fieldBuilder.get().append((char) character);
            }
        }
    }

}
