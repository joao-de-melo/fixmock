package org.kodekuality.fixmock.fix.mapping;

import org.kodekuality.fixmock.fix.session.RawFixMessage;

import java.net.Socket;
import java.util.List;

public class MappingService {
    private final List<FixMapping> fixMappings;
    private final StepExecutorService stepExecutorService;

    public MappingService(List<FixMapping> fixMappings, StepExecutorService stepExecutorService) {
        this.fixMappings = fixMappings;
        this.stepExecutorService = stepExecutorService;
    }

    public boolean handle(Socket socket, RawFixMessage message) {
        for (FixMapping fixMapping : fixMappings) {
            if (fixMapping.getPredicate().test(message)) {
                stepExecutorService.execute(socket, fixMapping.getSteps());
                return true;
            }
        }
        return false;
    }
}
