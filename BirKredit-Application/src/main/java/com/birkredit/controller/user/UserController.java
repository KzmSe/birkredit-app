package com.birkredit.controller.user;

import com.birkredit.controller.dto.UserCreationRequest;
import com.birkredit.controller.dto.UserResponse;
import com.birkredit.entity.User;
import com.birkredit.mapper.UserMapper;
import com.birkredit.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
@Api(value = "Operations related to users in BirKredit Application")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @ApiOperation(value = "Create a new user", response = UserResponse.class)
    public UserResponse register(@RequestBody UserCreationRequest request) {
        User user = UserMapper.INSTANCE.userCreationRequestToUser(request);
        return userService.insertUser(user);
    }
}
