package dev.ambryn.discordtest.errors.mappers;

import dev.ambryn.discordtest.enums.EError;
import dev.ambryn.discordtest.errors.Error;
import dev.ambryn.discordtest.responses.ErrorResponse;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class JsonParseExceptionMapper implements ExceptionMapper<ProcessingException> {
    @Override
    public Response toResponse(ProcessingException exception) {
        String message = exception.getCause().getMessage();

        Error.Builder builder = new Error.Builder();
        builder.setCode(EError.BadArgument);
        builder.setMessage(message);
        Error error = builder.build();
        return Response
                .status(Response.Status.BAD_REQUEST)
                .header("Content-Type", "application/json")
                .entity(new ErrorResponse(error))
                .build();
    }
}
