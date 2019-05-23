package org.kodekuality.fixmock.fix.mapping;

import org.kodekuality.fixmock.fix.mapping.steps.FixStep;
import org.kodekuality.fixmock.fix.mapping.steps.SendMessageFixStep;
import org.kodekuality.fixmock.fix.mapping.steps.WaitFixStep;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class StepExecutorService {
    public void execute(Socket socket, List<FixStep> steps) {
        for (FixStep step : steps) {
            if (step instanceof SendMessageFixStep) {
                try {
                    ((SendMessageFixStep) step).getMessage().serialise(socket.getOutputStream());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (step instanceof WaitFixStep) {
                try {
                    Thread.sleep(((WaitFixStep) step).getUnit().toMillis(((WaitFixStep) step).getInterval()));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                throw new IllegalArgumentException(String.format("Unknown step %s", step.getClass()));
            }
        }
    }
}
