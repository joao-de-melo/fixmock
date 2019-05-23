package org.kodekuality.fixmock.admin.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.kodekuality.fixmock.fix.session.FixSession;
import org.kodekuality.fixmock.fix.session.FixSessionConfiguration;
import org.kodekuality.fixmock.fix.session.SessionService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/session")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FixSessionResource {
    private final SessionService sessionService;

    public FixSessionResource(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @POST
    public NewSessionResponse newSession(NewSessionRequest request) {
        FixSession session = sessionService.create(new FixSessionConfiguration(
                request.getPort(),
                Optional.empty()
        ));
        return new NewSessionResponse(
                session.getSessionId(),
                session.getPort()
        );
    }

    @DELETE
    @Path("/{id}")
    public void terminateSession (@PathParam("id") String id) {

    }

    public static class NewSessionRequest {
        private final Optional<Integer> port;

        public NewSessionRequest(
                @JsonProperty("port") Optional<Integer> port) {
            this.port = port;
        }

        public Optional<Integer> getPort() {
            return port;
        }
    }

    public static class NewSessionResponse {
        private final String id;
        private final Integer port;

        public NewSessionResponse(@JsonProperty("id") String id,
                                  @JsonProperty("port") Integer port) {
            this.id = id;
            this.port = port;
        }

        public String getId() {
            return id;
        }

        public Integer getPort() {
            return port;
        }
    }
}
