package org.kodekuality.fixmock.fix.session;

import java.io.IOException;
import java.io.OutputStream;
import java.util.StringJoiner;

public class RawFixField {
    public static final char VALUE_SPLIT = '=';

    public static RawFixField parse(String string) {
        int position = string.indexOf(VALUE_SPLIT);
        if (position < 0) return new RawFixField(Integer.parseInt(string), "");
        else {
            if (position + 1 >= string.length()) {
                return new RawFixField(
                        Integer.parseInt(string.substring(0, position)),
                        ""
                );
            } else {
                return new RawFixField(
                        Integer.parseInt(string.substring(0, position)),
                        string.substring(position+1)
                );
            }
        }
    }

    private final int tag;
    private final String value;

    public RawFixField(int tag, String value) {
        this.tag = tag;
        this.value = value;
    }

    public int getTag() {
        return tag;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RawFixField.class.getSimpleName() + "[", "]")
                .add("tag=" + tag)
                .add("value='" + value + "'")
                .toString();
    }

    public void serialise(OutputStream output) throws IOException {
        output.write(String.format("%d=%s", tag, value).getBytes());
    }
}
