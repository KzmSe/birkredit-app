package com.birkredit.service.user;

import com.birkredit.controller.dto.UserResponse;
import com.birkredit.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserResponse insertUser(User user);
}
