package org.kodekuality.fixmock.fix.session;

import edu.emory.mathcs.backport.java.util.Arrays;
import edu.emory.mathcs.backport.java.util.Collections;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.kodekuality.fixmock.fix.mapping.FixMapping;
import org.kodekuality.fixmock.fix.mapping.predicates.FieldExistsPredicate;
import org.kodekuality.fixmock.fix.mapping.predicates.FieldTagPredicate;
import org.kodekuality.fixmock.fix.mapping.predicates.FieldValuePredicate;
import org.kodekuality.fixmock.fix.mapping.predicates.Predicates;
import org.kodekuality.fixmock.fix.mapping.steps.FixStep;
import org.kodekuality.fixmock.fix.mapping.steps.SendMessageFixStep;
import quickfix.*;
import quickfix.fix44.MessageFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Optional;

public class SessionServiceTest {
    @Test
    public void name() throws ConfigError, IOException, InterruptedException {
        SessionService sessionService = SessionService.sessionService(Collections.singletonList(
                new FixMapping(
                        new FieldExistsPredicate(
                                Predicates.allOf(
                                        new FieldTagPredicate(x -> x.equals(35)),
                                        new FieldValuePredicate(x -> x.equals("A"))
                                )
                        ),
                        Collections.singletonList(
                                new SendMessageFixStep(RawFixMessage.parse("8=FIX.4.4\u00019=51\u000135=A\u000134=1\u000149=EXECUTOR\u000156=CLIENT1\u000198=0\u0001108=30\u0001141=Y\u000110=044\u0001"))
                        )
                )
        ));
        FixSession session = sessionService.create(new FixSessionConfiguration(Optional.of(31012), Optional.empty()));

        SessionSettings settings = new SessionSettings(new ByteArrayInputStream(
                String.format(IOUtils.toString(getClass().getClassLoader().getResourceAsStream("fix.cfg"), Charset.defaultCharset()), session.getPort())
                .getBytes()
        ));
        ThreadedSocketInitiator threadedSocketAcceptor = new ThreadedSocketInitiator(new ApplicationAdapter() {
            @Override
            public void onLogon(SessionID sessionId) {
                super.onLogon(sessionId);
            }
        },
                new CachedFileStoreFactory(settings),
                settings,
                new MessageFactory()
        );
        threadedSocketAcceptor.start();

        Thread.sleep(10000);
    }
}