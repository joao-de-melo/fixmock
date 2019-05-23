package org.kodekuality.fixmock.fix.session;

import java.io.*;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class RawFixMessage {
    public static char FIELD_SPLIT = '\u0001';

    public static RawFixMessage parse (InputStream inputStream) throws IOException {
        AtomicReference<RawFixMessage> store = new AtomicReference<>();
        new FixMessageParser(store::set).parse(inputStream);
        return store.get();
    }

    public static RawFixMessage parse (String input) {
        try {
            return parse(new ByteArrayInputStream(input.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final List<RawFixField> fields;

    public RawFixMessage(List<RawFixField> fields) {
        this.fields = fields;
    }

    public List<RawFixField> getFields() {
        return fields;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RawFixMessage.class.getSimpleName() + "[", "]")
                .add("fields=" + fields)
                .toString();
    }

    public void serialise (OutputStream output) throws IOException {
        for (RawFixField field : fields) {
            field.serialise(output);
            output.write(FIELD_SPLIT);
        }
        output.flush();
    }

    public String asString() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            serialise(output);
            return output.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
