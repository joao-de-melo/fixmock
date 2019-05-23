package org.kodekuality.fixmock;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.kodekuality.fixmock.admin.resource.FixSessionResource;
import org.kodekuality.fixmock.fix.handler.MessageHandlerFactory;
import org.kodekuality.fixmock.fix.session.FixSessionFactory;
import org.kodekuality.fixmock.fix.session.FixSessionSocketHandlerFactory;
import org.kodekuality.fixmock.fix.session.SessionService;

import java.util.ArrayList;

public class FixmockApplication extends Application<FixmockConfiguration> {
    public static void main(String[] args) throws Exception {
        new FixmockApplication().run(args);
    }

    @Override
    public void run(FixmockConfiguration configuration, Environment environment) throws Exception {
        SessionService sessionService = SessionService.sessionService(new ArrayList<>());
        FixSessionResource fixSessionResource = new FixSessionResource(sessionService);
        environment.jersey().register(fixSessionResource);
    }
}
