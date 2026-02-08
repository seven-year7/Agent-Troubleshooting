package com.shanyangcode.infinitechat.ContactService.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanyangcode.infinitechat.ContactService.mapper.UserMapper;
import com.shanyangcode.infinitechat.ContactService.model.entity.User;
import com.shanyangcode.infinitechat.ContactService.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

}