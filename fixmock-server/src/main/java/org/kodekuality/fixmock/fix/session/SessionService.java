package org.kodekuality.fixmock.fix.session;

import org.kodekuality.fixmock.fix.handler.MessageHandlerFactory;
import org.kodekuality.fixmock.fix.mapping.FixMapping;
import org.kodekuality.fixmock.fix.mapping.MappingService;
import org.kodekuality.fixmock.fix.mapping.StepExecutorService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class SessionService {
    public static SessionService sessionService (List<FixMapping> mappings) {
        return new SessionService(
                new FixSessionFactory(
                        new FixSessionSocketHandlerFactory(
                                new MessageHandlerFactory(
                                        new MappingService(
                                                mappings,
                                                new StepExecutorService()
                                        )
                                )
                        )
                )
        );
    }

    private final Map<String, ThreadFixSession> activeSession = new ConcurrentHashMap<>();
    private final FixSessionFactory fixSessionFactory;

    public SessionService(FixSessionFactory fixSessionFactory) {
        this.fixSessionFactory = fixSessionFactory;
    }

    public FixSession create (FixSessionConfiguration configuration) {
        ThreadFixSession fixSession = fixSessionFactory.create(configuration);

        activeSession.put(fixSession.getSessionId(), fixSession);

        return fixSession;
    }

    public Optional<FixSession> get (String id) {
        return Optional.ofNullable(activeSession.get(id));
    }

    public void terminate (String id) {
        Optional.ofNullable(activeSession.remove(id))
                .ifPresent(ThreadFixSession::terminate);
    }
}
