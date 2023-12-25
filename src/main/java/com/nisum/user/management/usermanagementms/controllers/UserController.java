package com.nisum.user.management.usermanagementms.controllers;

import com.nisum.user.management.usermanagementms.api.request.NewUserRequest;
import com.nisum.user.management.usermanagementms.api.response.BaseResponse;
import com.nisum.user.management.usermanagementms.api.response.CreateNewUserResponse;
import com.nisum.user.management.usermanagementms.api.response.FindAllUsersResponse;
import com.nisum.user.management.usermanagementms.api.response.GetUserByIdResponse;
import com.nisum.user.management.usermanagementms.exceptions.UserNotFoundException;
import com.nisum.user.management.usermanagementms.model.User;
import com.nisum.user.management.usermanagementms.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Users", description = "User Service")
@RestController
@RequestMapping("/api/user")
@Validated
public class UserController
{
    @Autowired
    private UserService userService;

    @GetMapping(value = "/users", produces="application/json")
    public ResponseEntity<FindAllUsersResponse> findAll()
    {
        List<User> users = userService.findAll();

        FindAllUsersResponse response = FindAllUsersResponse.builder()
                .mensaje("Usuario Creado satisfactoriamente")
                .users(users)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value ="/users/{id}", produces="application/json")
    public GetUserByIdResponse getById(@PathVariable String id)
    {
        Optional<User> optionalUser = Optional.ofNullable(userService.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id)));

        return GetUserByIdResponse.builder()
                .mensaje("Usuario Recuperado satisfactoriamente")
                .user(optionalUser)
                .build();
    }

    @PostMapping(value = "/users" , consumes="application/json", produces="application/json")
    public ResponseEntity<CreateNewUserResponse> createNew(@Valid @RequestBody NewUserRequest newUser)
    {
        User userCreated = userService.save(newUser);
        CreateNewUserResponse response = CreateNewUserResponse.builder()
                .mensaje("Usuario Creado satisfactoriamente")
                .user(userCreated)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/users/{id}", produces="application/json")
    public ResponseEntity<?> delete(@PathVariable("id") String id)
    {
        Optional.ofNullable(userService.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id)));

        userService.deleteById(id);

        BaseResponse response = BaseResponse.builder()
                .mensaje("Usuario Eliminado satisfactoriamente")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value="/users/{id}", consumes="application/json", produces="application/json")
    public ResponseEntity<CreateNewUserResponse> updateOrCreate(@RequestBody NewUserRequest newUser, @PathVariable String id)
    {
        User userCreated = userService.save(newUser, id);
        CreateNewUserResponse response = CreateNewUserResponse.builder()
                .mensaje("Usuario Creado o Actualizado satisfactoriamente")
                .user(userCreated)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
