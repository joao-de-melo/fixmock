package org.kodekuality.fixmock.fix.session;

import java.net.InetAddress;
import java.util.Optional;

public class FixSessionConfiguration {
    private final Optional<Integer> port;
    private final Optional<InetAddress> address;

    public FixSessionConfiguration(Optional<Integer> port, Optional<InetAddress> address) {
        this.port = port;
        this.address = address;
    }

    public Optional<Integer> getPort() {
        return port;
    }

    public Optional<InetAddress> getAddress() {
        return address;
    }
}
