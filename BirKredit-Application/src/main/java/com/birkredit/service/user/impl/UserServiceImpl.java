package com.birkredit.service.user.impl;

import com.birkredit.controller.user.dto.UserResponse;
import com.birkredit.entity.User;
import com.birkredit.entity.UserRole;
import com.birkredit.exception.response.ResponseMessage;
import com.birkredit.mapper.UserMapper;
import com.birkredit.repository.user.UserRepository;
import com.birkredit.service.user.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private PasswordEncoder encoder;
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(PasswordEncoder encoder, UserRepository userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s)
                .orElseThrow(() ->
                        new UsernameNotFoundException(ResponseMessage.ERROR_USER_NOT_FOUND));
    }

    @Override
    public UserResponse insertUser(User user) {
        String defaultPassword = RandomStringUtils.random(10, true, true);
        user.setPassword(encoder.encode(defaultPassword));
        user.setRole(UserRole.ROLE_USER);

        User savedUser = userRepository.save(user);
        return UserMapper.INSTANCE.userToUserResponse(savedUser);
    }
}
