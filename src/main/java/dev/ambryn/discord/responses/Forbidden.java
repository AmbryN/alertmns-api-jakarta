package dev.ambryn.discord.responses;

import dev.ambryn.discord.enums.EError;
import dev.ambryn.discord.errors.Error;
import jakarta.ws.rs.core.Response;

public class Forbidden {
    public static Response build() {
        Error.Builder builder = new Error.Builder();
        builder.setCode(EError.Forbidden);
        builder.setMessage("You don't have permissions to perform this action");
        Error error = builder.build();
        return Response
                .status(Response.Status.FORBIDDEN)
                .entity(new ErrorResponse(error))
                .build();
    }
}
