package dev.ambryn.discordtest.controllers;

import dev.ambryn.discordtest.beans.User;
import dev.ambryn.discordtest.dto.UserGetDTO;
import dev.ambryn.discordtest.dto.UserPostDTO;
import dev.ambryn.discordtest.mappers.dto.UserMapper;
import dev.ambryn.discordtest.repositories.UserRepository;
import jakarta.inject.Inject;
import jakarta.persistence.NoResultException;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/users")
public class UserController {

    @Inject
    UserRepository userRepository;

    @Inject
    UserMapper userMapper;

    @GET
    @Produces(value = "application/json")
    public List<UserGetDTO> getUsers() {
        List<UserGetDTO> usersDTO = new ArrayList<>();
        for (User user : userRepository.getUsers()) {
            usersDTO.add(userMapper.toDto(user));
        }
        return usersDTO;
    }

    @GET
    @Path("/{id: [0-9]+}")
    @Produces(APPLICATION_JSON)
    public Response getUser(@PathParam("id") Long id) {
        try {
            User user = userRepository.getUser(id);
            UserGetDTO userGetDTO = userMapper.toDto(user);
            return Response.ok(userGetDTO).build();
        } catch (NoResultException ex) {
            return Response.status(404).build();
        }
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response postUser(@Valid UserPostDTO userDTO) {
        User user = userMapper.toUser(userDTO);
        System.out.println(user);
        try {
            userRepository.addUser(user);

        } catch (RuntimeException e) {
            e.printStackTrace();
            return Response.status(400).build();
        }
        UserGetDTO userGetDTO = userMapper.toDto(user);

        return Response.ok(userGetDTO).build();
    }
}
